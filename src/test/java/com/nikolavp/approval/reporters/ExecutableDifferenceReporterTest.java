package com.nikolavp.approval.reporters;

import com.nikolavp.approval.TestTempFile;
import com.nikolavp.approval.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static com.nikolavp.approval.TestUtils.RAW_VALUE;
import static com.nikolavp.approval.TestUtils.forApproval;
import static org.mockito.Mockito.*;

/**
 * Created by nikolavp on 2/2/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExecutableDifferenceReporterTest {

    public static final int ERROR_EXIT_CODE = 2;
    public static final int OK_CODE = 0;
    @Rule
    public TestTempFile testFile = new TestTempFile();
    private ExecutableDifferenceReporter executableDifferenceReporter;

    @Before
    public void setupMocks() {
        executableDifferenceReporter = Mockito.spy(new ExecutableDifferenceReporter("gvim", "gvimdiff"));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfThereIsErrorWhileExecuting() throws Exception {
        doThrow(new IOException("error in exec")).when(executableDifferenceReporter).startProcess(Mockito.<String>anyVararg());

        executableDifferenceReporter.approveNew(RAW_VALUE, forApproval(testFile), testFile.file());
    }

    @Test
    public void shouldReturnFalseIfCommandThatWasExecutedReturnedBadExitCode() throws Exception {
        for(int exitCode : new int[] {ERROR_EXIT_CODE, -ERROR_EXIT_CODE}) {
            Process process = Mockito.mock(Process.class);
            when(process.waitFor()).thenReturn(exitCode);
            doReturn(process).when(executableDifferenceReporter).startProcess("gvim", forApproval(testFile).getAbsolutePath());

            boolean approved = executableDifferenceReporter.approveNew("test content".getBytes(), forApproval(testFile), testFile.file());
            Assert.assertThat(approved, CoreMatchers.is(false));
        }
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionErrorOnInterruptionAlthoughThatShouldntHappen() throws Exception {
        //assign
        Process process = Mockito.mock(Process.class);
        when(process.waitFor()).thenThrow(new InterruptedException("test exception"));

        doReturn(process).when(executableDifferenceReporter).startProcess("gvim", forApproval(testFile).getAbsolutePath());

        //act
        executableDifferenceReporter.approveNew(RAW_VALUE, forApproval(testFile), testFile.file());
    }

    @Test
    public void shouldReturnTrueIfCommandThatWasExecutedExitedWithNonErrorValue() throws Exception {
        Process process = Mockito.mock(Process.class);
        when(process.exitValue()).thenReturn(OK_CODE);
        doReturn(process).when(executableDifferenceReporter).startProcess("gvim", forApproval(testFile).getAbsolutePath());

        boolean approved = executableDifferenceReporter.approveNew("test content".getBytes(), forApproval(testFile), testFile.file());
        Assert.assertThat(approved, CoreMatchers.is(true));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfThereIsErrorWhileExecutingNotSame() throws Exception {
        doThrow(new IOException("error in exec")).when(executableDifferenceReporter).startProcess(Mockito.<String>anyVararg());
        executableDifferenceReporter.notTheSame(RAW_VALUE, testFile.file(), (TestUtils.VALUE + " difference ").getBytes(), forApproval(testFile));
    }

    @Test
    public void shouldProperlyExecuteNotSameCommand() throws Exception {
        Process process = Mockito.mock(Process.class);
        when(process.exitValue()).thenReturn(OK_CODE);
        doReturn(process).when(executableDifferenceReporter).startProcess(Mockito.<String>anyVararg());
        executableDifferenceReporter.notTheSame(RAW_VALUE, testFile.file(), (TestUtils.VALUE + " difference ").getBytes(), forApproval(testFile));

        verify(executableDifferenceReporter).startProcess("gvimdiff", forApproval(testFile).getAbsolutePath(), testFile.file().getAbsolutePath());
    }
}
