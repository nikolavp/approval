package com.nikolavp.approval.reporters;

import com.nikolavp.approval.TestTempFile;
import com.nikolavp.approval.TestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static com.nikolavp.approval.TestUtils.RAW_VALUE;
import static com.nikolavp.approval.TestUtils.forApproval;

/**
 * Created by ontotext on 2/2/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExecutableDifferenceReporterTest {

    @Rule
    public TestTempFile testFile = new TestTempFile();

    @Mock
    Runtime runtime;

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfThereIsErrorWhileExecuting() throws Exception {
        Mockito.when(runtime.exec(Mockito.any(String[].class))).thenThrow(new IOException("error in exec"));
        new ExecutableDifferenceReporter("some command", runtime).approveNew(RAW_VALUE, forApproval(testFile), testFile.file());
    }

    @Test
    public void shouldProperlyExecuteCommandOnNewContent() {

        ExecutableDifferenceReporter reporter = new ExecutableDifferenceReporter("vim", runtime);
        Mockito.when(runtime.exec()).thenReturn();
        reporter.approveNew("test content".getBytes(), path.toFile(), testFile.file());

    }
}
