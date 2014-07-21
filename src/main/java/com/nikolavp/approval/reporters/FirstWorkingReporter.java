package com.nikolavp.approval.reporters;

import com.nikolavp.approval.Reporter;

import java.io.File;

/**
 * A reporter that will compose other reportes and use the first one that can approve the objects for verification as per {@link com.nikolavp.approval.Reporter#canApprove(java.io.File)}.
 */
class FirstWorkingReporter implements Reporter {
    private final Reporter[] others;

    FirstWorkingReporter(Reporter... others) {
        this.others = others;
    }

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
        for (Reporter other : others) {
            if (other.canApprove(fileForApproval)) {
                other.notTheSame(oldValue, fileForVerification, newValue, fileForApproval);
                return;
            }
        }
        throw new IllegalStateException("This should never happen. Can approve was not called?!");
    }

    @Override
    public boolean approveNew(byte[] value, File fileForApproval, File fileForVerification) {
        for (Reporter other : others) {
            if (other.canApprove(fileForApproval)) {
                return other.approveNew(value, fileForVerification, fileForApproval);
            }
        }
        throw new IllegalStateException("This should never happen. Can approve was not called?!");
    }

    @Override
    public boolean canApprove(File fileForApproval) {
        for (Reporter other : others) {
            if (other.canApprove(fileForApproval)) {
                return true;
            }
        }
        return false;
    }
}
