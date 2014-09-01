package com.github.approval.reporters;

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

import com.github.approval.Reporter;

import java.io.File;

/**
 * A reporter that will compose other reporters and use the first one that can approve the objects for verification as
 * per {@link com.github.approval.Reporter#canApprove(java.io.File)}.
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
    public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {
        for (Reporter other : others) {
            if (other.canApprove(fileForApproval)) {
                other.approveNew(value, fileForVerification, fileForApproval);
                return;
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
