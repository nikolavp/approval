package com.nikolavp.approval;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ontotext on 1/29/14.
 */
public class ApprovalTest {

    @Rule
    TestTempFile testFile = new TestTempFile();

    //unused for now... will be needed when we have more complex setup
    public static interface FileLocator {
        File locateFile();
    }

    //unused for now... will be needed when we have more approval classes
    public static interface ApprovalConverter<T> {
        T convert(byte[] content);

        byte[] convert(T content);
    }

    @Test
    public void shouldCreateFileForApprovalIfNormalFileIsNotLocated() {
        Reporter reporter = Mockito.mock(Reporter.class);
        String value = "some\nmultiline\nnsimple\nstring";
        new Approval(reporter).verify(value, testFile.file());

        assertThat(testFile.file().exists(), is(true));
        assertThat(FileUtils.readStringFromFile(testFile.file()), equalTo(value));
        //hmm should it call the reporter here?? maybe we want this test to be ignored i.e. pending?
        //maybe we should call the reporter but with another method?
        Mockito.verify(reporter, Mockito.times(0)).report();
    }

    @Test
    public void shouldNotCallTheReporterIfAnOldFileAndIsTheSame() {
        Reporter reporter = Mockito.mock(Reporter.class);
        String value = "some\nmultiline\nnsimple\nstring";
        FileUtils.writeStringToFile(value, testFile.file());

        new Approval(reporter).verify(value, testFile.file());
        Mockito.verify(reporter, Mockito.times(0)).report();
    }

    @Test
    public void shouldCallTheReporterIfAnOldFileExistsButIsNotTheSame() {
        FileLocator fileLocator = new FileLocator() {
            @Override
            public File locateFile() {
                return testFile.file();
            }
        };
        Reporter reporter = Mockito.mock(Reporter.class);
        String value = "some\nmultiline\nnsimple\nstring" + "difference";
        FileUtils.writeStringToFile(value, testFile.file());

        new Approval(reporter).verify(value, testFile.file());
        Mockito.verify(reporter).report();
    }
}
