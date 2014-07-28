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
import com.nikolavp.approval.utils.DefaultFileSystemUtils;
import com.nikolavp.approval.utils.FileSystemUtils;

import javax.swing.JOptionPane;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * A reporter that can wrap another reporter and give you a prompt to approve the new result value.
 *
 * <p>This is useful for
 * reporters that cannot give you a clear way to create the result file. If for example you are using a reporter that
 * only shows you the resulting value but you cannot move it to the proper result file for the approval.
 * </p>
 * If you say OK/YES on the prompt then the result will be written to the proper file for the next approval time.
 */
public class SwingInteractiveReporter implements Reporter {
    private final Reporter other;
    private FileSystemUtils fileSystemReadWriter;

    SwingInteractiveReporter(Reporter other, FileSystemUtils fileSystemReadWriter) {
        this.other = other;
        this.fileSystemReadWriter = fileSystemReadWriter;
    }

    /**
     * Wrap another reporter.
     * @param reporter the other reporter
     * @return a new reporter that call the other reporter and then propmts the user
     */
    public static SwingInteractiveReporter wrap(Reporter reporter) {
        return new SwingInteractiveReporter(reporter, new DefaultFileSystemUtils());
    }

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
        other.notTheSame(oldValue, fileForVerification, newValue, fileForApproval);
        interactWithUser(fileForApproval.toPath(), fileForVerification.toPath());
    }

    @Override
    public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {
        other.approveNew(value, fileForApproval, fileForVerification);
        interactWithUser(fileForApproval.toPath(), fileForVerification.toPath());
    }

    private void interactWithUser(Path approvalPath, Path filePath) {
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }
        final int wasTheResultOK = promptUser();
        if (wasTheResultOK == 0) {
            try {
                fileSystemReadWriter.move(approvalPath, filePath);
            } catch (IOException e) {
                String errorMessage = String.format("Couldn't move file for approval[%s] to the destination [%s]", approvalPath.toAbsolutePath(), filePath.toAbsolutePath());
                throw new AssertionError(errorMessage);
            }
        } else {
            throw new AssertionError("Result was NOT OK");
        }

    }

    int promptUser() {
        Object[] options = {"OK", "NOT OK"};
        return JOptionPane.showOptionDialog(null, "Was the result correct", "Was the result correct",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
    }

    @Override
    public boolean canApprove(File fileForApproval) {
        return other.canApprove(fileForApproval);
    }
}
