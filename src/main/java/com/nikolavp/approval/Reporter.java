package com.nikolavp.approval;

import java.io.File;

/**
 * Created by nikolavp on 1/30/14.
 */
public interface Reporter {
    /**
     * Called by an {@link com.nikolavp.approval.Approval} object when values don't match in the approval process.
     *
     *
     *
     * @param oldValue the old value that was found in fileForVerification from old runs
     * @param newValue the new value that was passed for verification
     * @param fileForVerification the file for this approval value
     */
    void notTheSame(byte[] oldValue, byte[] newValue, File fileForVerification);

    /**
     * Called by an {@link com.nikolavp.approval.Approval} object when a value for verification is produced but no old.
     * @param value the new value that came from the verification
     * @param fileForVerification the file for the this new approval value
     * @return true if the new value is approved and false otherwise
     */
    boolean approveNew(byte[] value, File fileForVerification);
}
