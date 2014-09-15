package com.github.approval.sesame;

/*
 * #%L
 * approval-sesame
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

import com.github.approval.Reporter;
import com.github.approval.reporters.ExecutableDifferenceReporter;
import com.github.approval.reporters.Reporters;
import com.github.approval.reporters.SwingInteractiveReporter;

import java.io.File;

/**
 * <p>
 * A reporter that can be used to verify dot files. It will compile the file to an image and open it through
 * {@link com.github.approval.reporters.Reporters#fileLauncher()}.
 * <p/>
 * <p>
 * Note that this reporter cannot be used for anything else and will give you an error beceause it will
 * try to compile the verification file with the "dot" command.
 * </p>
 */
public final class GraphReporter extends ExecutableDifferenceReporter {

    /**
     * Get an instance of the reporter.
     * @return a graph reporter
     */
    public static Reporter getInstance() {
        return SwingInteractiveReporter.wrap(new GraphReporter());
    }

    /**
     * Main constructor for the executable reporter.
     */
    private GraphReporter() {
        super("dot -T png -O ", "dot -T png -O ", "dot");
    }

    private static File addPng(File file) {
        return new File(file.getAbsoluteFile().getAbsolutePath() + ".png");
    }

    @Override
    public void approveNew(byte[] value, File approvalDestination, File fileForVerification) {
        super.approveNew(value, approvalDestination, fileForVerification);
        Reporters.fileLauncherWithoutInteraction().approveNew(value, addPng(approvalDestination), addPng(fileForVerification));
    }

    @Override
    protected String[] buildApproveNewCommand(File approvalDestination, File fileForVerification) {
        return new String[]{getApprovalCommand(), approvalDestination.getAbsolutePath()};
    }

    @Override
    protected String[] buildNotTheSameCommand(File fileForVerification, File fileForApproval) {
        return new String[]{getDiffCommand(), fileForApproval.getAbsolutePath()};
    }

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
        super.notTheSame(oldValue, fileForVerification, newValue, fileForApproval);
        Reporters.fileLauncherWithoutInteraction().notTheSame(oldValue, addPng(fileForVerification), newValue, addPng(fileForApproval));
    }
}
