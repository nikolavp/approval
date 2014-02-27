package com.nikolavp.approval;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
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

    byte[] getFileContent(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    @Test
    public void shouldCreateFileForApprovalIfNormalFileIsNotLocatedAndNotifyReporter() throws Exception {
        //assign
        File fileForApproval = TestUtils.forApproval(testFile);
        //act
        try {
            new Approval(reporter).verify(TestUtils.VALUE.getBytes(), testFile.file().toPath());
        } catch (AssertionError error) {
            //This is thrown because we didn't approve the file
        }

        //assert
        assertThat(fileForApproval.exists(), is(true));
        assertThat(getFileContent(fileForApproval), equalTo(TestUtils.VALUE.getBytes()));
        verify(reporter).approveNew(TestUtils.VALUE.getBytes(), fileForApproval, testFile.file());
        Mockito.verifyNoMoreInteractions(reporter);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfCannotWriteNewApprovalFile() throws Exception {
        doThrow(new IOException("test error")).when(fileSystemUtils).write(any(Path.class), any(byte[].class));
        new Approval(reporter, fileSystemUtils).verify(TestUtils.VALUE.getBytes(), testFile.file().toPath());
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfCannotMoveApprovalFile() throws Exception {
        //arrange
        doThrow(new IOException("test error moving")).when(fileSystemUtils).move(any(Path.class), any(Path.class));
        when(reporter.approveNew(any(byte[].class), any(File.class), any(File.class))).thenReturn(true);

        new Approval(reporter, fileSystemUtils).verify(TestUtils.VALUE.getBytes(), testFile.file().toPath());
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionErrorIfCannotReadOldFile() throws Exception {
        //arrange
        when(fileSystemUtils.readFully(any(Path.class))).thenThrow(new IOException("read test exception"));
        testFile.file().createNewFile();
        new Approval(reporter, fileSystemUtils).verify(TestUtils.VALUE.getBytes(), testFile.file().toPath());


    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAnAssertionError_IfItCannotWriteTheNewValueToApprovalFile() throws Exception {
        //arrange
        testFile.file().createNewFile();

        doThrow(new IOException("cannot create approval file")).when(fileSystemUtils).write(TestUtils.forApproval(testFile).toPath(), TestUtils.VALUE.getBytes());
        new Approval(reporter, fileSystemUtils).verify(TestUtils.VALUE.getBytes(), testFile.file().toPath());
    }

    @Test
    public void shouldMoveCreatedFileIfReporterAcceptsIt() throws Exception {
        //assign
        when(reporter.approveNew(Mockito.any(byte[].class), any(File.class), Mockito.any(File.class))).thenReturn(true);

        //act
        new Approval(reporter).verify(TestUtils.VALUE.getBytes(), testFile.file().toPath());

        //assert
        assertThat(testFile.file().exists(), is(true));
        File fileForApproval = TestUtils.forApproval(testFile);
        assertThat(fileForApproval.exists(), is(false));
        assertThat(getFileContent(testFile.file()), equalTo(TestUtils.VALUE.getBytes()));
        verify(reporter).approveNew(TestUtils.VALUE.getBytes(), TestUtils.forApproval(testFile), testFile.file());
        Mockito.verifyNoMoreInteractions(reporter);
    }

    @Test
    public void shouldCallTheReporterIfAnOldFileExistsButIsNotTheSame() throws Exception{
        String valueWithDifference = TestUtils.VALUE + "difference";
        Files.write(testFile.file().toPath(), valueWithDifference.getBytes());

        new Approval(reporter).verify(TestUtils.VALUE.getBytes(), testFile.file().toPath());
        verify(reporter).notTheSame(valueWithDifference.getBytes(), testFile.file(), TestUtils.VALUE.getBytes(), TestUtils.forApproval(testFile));
        Mockito.verifyNoMoreInteractions(reporter);
    }

    @Test
    public void shouldNotCallTheReporterIfAnOldFileAndIsTheSame() throws Exception{
        Files.write(testFile.file().toPath(), TestUtils.VALUE.getBytes());

        new Approval(reporter).verify(TestUtils.VALUE.getBytes(), testFile.file().toPath());
        Mockito.verifyNoMoreInteractions(reporter);
    }
    
    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionErrorOnNonVerifiedFile() throws Exception {
        //assign
        when(reporter.approveNew(any(byte[].class), any(File.class), any(File.class))).thenReturn(false);

        //act
        new Approval(reporter).verify(TestUtils.RAW_VALUE, testFile.file().toPath());
    }
    
    @Test(expected = AssertionError.class)
    public void shouldCreateDirectoryAsNeededAndThrowAnExceptionIfItCant() throws Exception {
        //assign
        doThrow(new IOException("test exception")).when(fileSystemUtils).createDirectories(Mockito.any(File.class));

        //act
        new Approval(reporter).verify(TestUtils.RAW_VALUE, testFile.file().toPath());

    }
}
