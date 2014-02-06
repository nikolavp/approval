package com.nikolavp.approval.reporters;

import com.nikolavp.approval.Reporter;

import java.io.File;
import java.io.IOException;

/**
 * A reporter that will shell out to an executable that is presented on the user's machine to verify the test output. Note that the approval command and the difference commands can be the same.
 * <ul>
 *     <li>approval command will be used for the first approval</li>
 *     <li>the difference command will be used when there is already a verified file but it is not the same as the value from the user</li>
 * </ul>
 *
 */
public class ExecutableDifferenceReporter implements Reporter {
    private final String diffCommand;
    private final Runtime runtime;
    private final String approvalCommand;

    /**
     * Main constructor for the executable reporter.
     * @param approvalCommand the approval command
     * @param diffCommand the difference command
     */
    public ExecutableDifferenceReporter(String approvalCommand, String diffCommand) {
        this(approvalCommand, diffCommand, Runtime.getRuntime());
    }


    ExecutableDifferenceReporter(String approvalCommand, String diffCommand, Runtime runtime) {
        this.runtime = runtime;
        this.approvalCommand = approvalCommand;
        this.diffCommand = diffCommand;
    }

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
        try {
            this.runtime.exec(new String[]{diffCommand, fileForApproval.getAbsolutePath(), fileForVerification.getAbsolutePath()});
        } catch (IOException e) {
            throw new AssertionError("There was a problem while executing %s", e);
        }
    }

    @Override
    public boolean approveNew(byte[] value, File approvalDestination, File fileForVerification) {
        try {
            Process process = runtime.exec(new String[]{approvalCommand, approvalDestination.getAbsolutePath()});
            if (process.exitValue() == 0) {
                return true;
            }
        } catch (IOException e) {
            throw new AssertionError("There was a problem while executing %s", e);
        }
        return false;
    }
}
