package com.nikolavp.approval;

/*
 * #%L
 * approval
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

import java.io.File;

/**
 * Created by nikolavp on 1/30/14.
 */
public interface Reporter {
    /**
     * Called by an {@link com.nikolavp.approval.Approval} object when values don't match in the approval process.
     *
     * @param oldValue the old value that was found in fileForVerification from old runs
     * @param newValue the new value that was passed for verification
     * @param fileForVerification the file for this approval value
     * @param fileForApproval the file for the new content
     */
    void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval);

    /**
     * Called by an {@link com.nikolavp.approval.Approval} object when a value for verification is produced but no old.
     *
     * @param value the new value that came from the verification
     * @param fileForApproval the approval file(this contains the value that was passed in)
     * @param fileForVerification the file for the this new approval value  @return true if the new value is approved and false otherwise
     */
    void approveNew(byte[] value, File fileForApproval, File fileForVerification);

    /**
     * A method to check if this reporter is supported for the following file type or environment! Reporters are
     * different for different platforms and file types and this in conjuction with {@link
     * com.nikolavp.approval.reporters.Reporters#firstWorking} will allow you to plug different reporters for different
     * environments(CI, Windows, Linux, MacOS, etc).
     *
     * @param fileForApproval the file that we want to approve
     * @return true if we can approve the file and false otherwise
     */
    boolean canApprove(File fileForApproval);
}
