package com.nikolavp.approval.reporters;

/*
 * #%L
 * com.github.nikolavp:approval-core
 * %%
 * Copyright (C) 2014 Nikolavp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.nikolavp.approval.Reporter;
import com.nikolavp.approval.TestTempFile;
import com.nikolavp.approval.TestUtils;
import com.nikolavp.approval.utils.FileSystemUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * User: nikolavp (Nikola Petrov) Date: 14-7-28 Time: 10:46
 */
@RunWith(MockitoJUnitRunner.class)
public class SwingInteractiveReporterTest {

    @Mock
    private Reporter reporter;

    @Mock
    private FileSystemUtils fileSystemUtils;

    @Rule
    public TestTempFile testFile = new TestTempFile();

    private SwingInteractiveReporter interactiveReporter;

    @Before
    public void initSwingReporter() {
        interactiveReporter = Mockito.spy(new SwingInteractiveReporter(reporter, fileSystemUtils));
        doReturn(false).when(interactiveReporter).isHeadless();
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionError_IfCannotMoveApprovalFile() throws Exception {
        doThrow(new IOException("test error moving")).when(fileSystemUtils).move(any(Path.class), any(Path.class));
        doReturn(0).when(interactiveReporter).promptUser();

        interactiveReporter.approveNew(com.nikolavp.approval.TestUtils.RAW_VALUE,
                TestUtils.forApproval(testFile),
                testFile.file()
        );
    }

    @Test
    public void shouldMoveCreatedFileIfReporterAcceptsIt() throws Exception {
        //assign
        doReturn(0).when(interactiveReporter).promptUser();

        //act
        interactiveReporter.approveNew(com.nikolavp.approval.TestUtils.RAW_VALUE,
                TestUtils.forApproval(testFile),
                testFile.file()
        );

        //assert
        File fileForApproval = TestUtils.forApproval(testFile);
        verify(fileSystemUtils).move(fileForApproval.toPath(), testFile.path());
        verify(reporter).approveNew(com.nikolavp.approval.TestUtils.RAW_VALUE, fileForApproval, testFile.file());
        verifyNoMoreInteractions(reporter);
    }

    @Test
    public void shouldMoveCreatedFileIfReporterAcceptsItNotSame() throws Exception {
        //assign
        doReturn(0).when(interactiveReporter).promptUser();

        //act
        interactiveReporter.notTheSame(TestUtils.RAW_VALUE,
                testFile.file(),
                TestUtils.RAW_VALUE,
                TestUtils.forApproval(testFile)
        );

        //assert
        File fileForApproval = TestUtils.forApproval(testFile);
        verify(fileSystemUtils).move(fileForApproval.toPath(), testFile.path());
        verify(reporter).notTheSame(TestUtils.RAW_VALUE,
                testFile.file(),
                TestUtils.RAW_VALUE,
                TestUtils.forApproval(testFile));
        verifyNoMoreInteractions(reporter);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowAnExceptionOnNonOKResultFromUser() throws Exception {
        //assign
        doReturn(1).when(interactiveReporter).promptUser();

        //act
        interactiveReporter.approveNew(com.nikolavp.approval.TestUtils.RAW_VALUE,
                TestUtils.forApproval(testFile),
                testFile.file()
        );
    }

    @Test
    public void shouldReturnCanApproveFromOther() throws Exception {
        assertThat(interactiveReporter.canApprove(testFile.file()), equalTo(false));

        when(reporter.canApprove(testFile.file())).thenReturn(true);

        assertThat(interactiveReporter.canApprove(testFile.file()), equalTo(true));
    }

    @Test
    public void shouldNotCallSwingIfInHeadlessMode() throws Exception {
        //assign
        doThrow(new AssertionError("error")).when(interactiveReporter).promptUser();
        doReturn(true).when(interactiveReporter).isHeadless();

        //act
        interactiveReporter.approveNew(com.nikolavp.approval.TestUtils.RAW_VALUE,
                TestUtils.forApproval(testFile),
                testFile.file()
        );
    }

}
