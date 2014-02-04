package com.nikolavp.approval.reporters;

import com.nikolavp.approval.Reporter;

import java.io.File;
import java.io.IOException;

/**
 *
 * Created by nikolavp on 2/2/14.
 */
public class ExecutableDifferenceReporter implements Reporter {
    private final String command;
    private final Runtime runtime;

    public ExecutableDifferenceReporter(String command) {
        this(command, Runtime.getRuntime());
    }


    ExecutableDifferenceReporter(String command, Runtime runtime) {
        this.runtime = runtime;
        this.command = command;
    }

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {

    }

    @Override
    public boolean approveNew(byte[] value, File approvalDestination, File fileForVerification) {
        try {
            runtime.exec(new String[]{command, approvalDestination.getAbsolutePath()});
        } catch (IOException e) {
            throw new AssertionError("There was a problem while executing %s", e);
        }
        return true;
    }
}
