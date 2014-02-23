package com.nikolavp.approval.reporters;

import com.nikolavp.approval.TestTempFile;
import com.nikolavp.approval.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static com.nikolavp.approval.TestUtils.RAW_VALUE;
import static com.nikolavp.approval.TestUtils.forApproval;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nikolavp on 2/2/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExecutableDifferenceReporterTest {

    public static final int ERROR_EXIT_CODE = 2;
    public static final int OK_CODE = 0;
    @Rule
    public TestTempFile testFile = new TestTempFile();

    @Mock
    Runtime runtime;

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfThereIsErrorWhileExecuting() throws Exception {
        when(runtime.exec(Mockito.any(String.class))).thenThrow(new IOException("error in exec"));
        new ExecutableDifferenceReporter("some command", null, runtime).approveNew(RAW_VALUE, forApproval(testFile), testFile.file());
    }

    @Test
    public void shouldReturnFalseIfCommandThatWasExecutedReturnedBadExitCode() throws Exception {
        for(int exitCode : new int[] {ERROR_EXIT_CODE, -ERROR_EXIT_CODE}) {
            Process process = Mockito.mock(Process.class);
            when(process.waitFor()).thenReturn(exitCode);
            ExecutableDifferenceReporter reporter = new ExecutableDifferenceReporter("gvim", null, runtime);
            when(runtime.exec("gvim " + forApproval(testFile).getAbsolutePath())).thenReturn(process);

            boolean approved = reporter.approveNew("test content".getBytes(), forApproval(testFile), testFile.file());
            Assert.assertThat(approved, CoreMatchers.is(false));
        }
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionErrorOnInterruptionAlthoughThatShouldntHappen() throws Exception {
        //assign
        Process process = Mockito.mock(Process.class);
        when(process.waitFor()).thenThrow(new InterruptedException("test exception"));

        when(runtime.exec("vim " + forApproval(testFile).getAbsolutePath())).thenReturn(process);

        //act
        new ExecutableDifferenceReporter("vim", null, runtime).approveNew(RAW_VALUE, forApproval(testFile), testFile.file());
    }

    @Test
    public void shouldReturnTrueIfCommandThatWasExecutedExitedWithNonErrorValue() throws Exception {
        Process process = Mockito.mock(Process.class);
        when(process.exitValue()).thenReturn(OK_CODE);
        ExecutableDifferenceReporter reporter = new ExecutableDifferenceReporter("gvim", null, runtime);
        when(runtime.exec("gvim " + forApproval(testFile).getAbsolutePath())).thenReturn(process);

        boolean approved = reporter.approveNew("test content".getBytes(), forApproval(testFile), testFile.file());
        Assert.assertThat(approved, CoreMatchers.is(true));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfThereIsErrorWhileExecutingNotSame() throws Exception {
        when(runtime.exec(Mockito.any(String.class))).thenThrow(new IOException("error in exec"));
        new ExecutableDifferenceReporter("some command", null, runtime).notTheSame(RAW_VALUE, testFile.file(), (TestUtils.VALUE + " difference ").getBytes(), forApproval(testFile));
    }

    @Test
    public void shouldProperlyExecuteNotSameCommand() throws Exception {
        new ExecutableDifferenceReporter(null, "vimdiff", runtime).notTheSame(RAW_VALUE, testFile.file(), (TestUtils.VALUE + " difference ").getBytes(), forApproval(testFile));

        verify(runtime).exec("vimdiff " + forApproval(testFile).getAbsolutePath() + " " + testFile.file().getAbsolutePath());
    }
}
