package com.nikolavp.approval;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by nikolavp on 1/29/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApprovalTest {

    @Mock
    private Reporter reporter;

    @Mock
    private FileSystemUtils fileSystemUtils;

    @Rule
    public TestTempFile testFile = new TestTempFile();

    private static final String VALUE = "some\nmultiline\nnsimple\nstring";

    byte[] getFileContent(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    @Test
    public void shouldCreateFileForApprovalIfNormalFileIsNotLocatedAndNotifyReporter() throws Exception {
        //assign

        //act
        new Approval(reporter).verify(VALUE.getBytes(), testFile.file().toPath());

        //assert
        File fileForApproval = Approval.getApprovalPath(testFile.file().toPath()).toFile();
        assertThat(fileForApproval.exists(), is(true));
        assertThat(getFileContent(fileForApproval), equalTo(VALUE.getBytes()));
        verify(reporter).approveNew(VALUE.getBytes(), testFile.file());
        Mockito.verifyNoMoreInteractions(reporter);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfCannotWriteNewApprovalFile() throws Exception {
        doThrow(new IOException("test error")).when(fileSystemUtils).write(any(Path.class), any(byte[].class));
        new Approval(reporter, fileSystemUtils).verify(VALUE.getBytes(), testFile.file().toPath());
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfCannotMoveApprovalFile() throws Exception {
        //arrange
        doThrow(new IOException("test error moving")).when(fileSystemUtils).move(any(Path.class), any(Path.class));
        when(reporter.approveNew(any(byte[].class), any(File.class))).thenReturn(true);

        new Approval(reporter, fileSystemUtils).verify(VALUE.getBytes(), testFile.file().toPath());
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionErrorIfCannotReadOldFile() throws Exception {
        //arrange
        when(fileSystemUtils.readFully(any(Path.class))).thenThrow(new IOException("read test exception"));
        testFile.file().createNewFile();
        new Approval(reporter, fileSystemUtils).verify(VALUE.getBytes(), testFile.file().toPath());


    }

    @Test
    public void shouldThrowAnAssertionErrorIfUserDoesntApproveTheNew() {

    }

    @Test
    public void shouldMoveCreatedFileIfReporterAcceptsIt() throws Exception {
        //assign
        when(reporter.approveNew(Mockito.any(byte[].class), Mockito.any(File.class))).thenReturn(true);


        //act
        new Approval(reporter).verify(VALUE.getBytes(), testFile.file().toPath());

        //assert
        assertThat(testFile.file().exists(), is(true));
        File fileForApproval = Approval.getApprovalPath(testFile.file().toPath()).toFile();
        assertThat(fileForApproval.exists(), is(false));
        assertThat(getFileContent(testFile.file()), equalTo(VALUE.getBytes()));
        verify(reporter).approveNew(VALUE.getBytes(), testFile.file());
        Mockito.verifyNoMoreInteractions(reporter);
    }

    @Test
    public void shouldCallTheReporterIfAnOldFileExistsButIsNotTheSame() throws Exception{
        String valueWithDifference = VALUE + "difference";
        Files.write(testFile.file().toPath(), valueWithDifference.getBytes());

        new Approval(reporter).verify(VALUE.getBytes(), testFile.file().toPath());
        verify(reporter).notTheSame(valueWithDifference.getBytes(), VALUE.getBytes(), testFile.file());
        Mockito.verifyNoMoreInteractions(reporter);
    }

    @Test
    public void shouldNotCallTheReporterIfAnOldFileAndIsTheSame() throws Exception{
        Files.write(testFile.file().toPath(), VALUE.getBytes());

        new Approval(reporter).verify(VALUE.getBytes(), testFile.file().toPath());
        Mockito.verifyNoMoreInteractions(reporter);
    }
}
