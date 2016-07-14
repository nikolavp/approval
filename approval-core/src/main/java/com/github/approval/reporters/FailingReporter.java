package com.github.approval.reporters;

/*
 * #%L
 * com.github.nikolavp:approval-core
 * %%
 * Copyright (C) 2014 - 2016 Nikolavp
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
 * A reporter that always fails and reports differences files in the assertion message.
 * Note that this is particularly useful when used with {@link SystemPropertyReporter}.
 */
public class FailingReporter implements Reporter {

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
        throw new AssertionError("Different values in " + fileForApproval + " and " + fileForVerification);
    }

    @Override
    public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {
        throw new AssertionError("New value in " + fileForApproval);
    }

    @Override
    public boolean canApprove(File fileForApproval) {
        return true;
    }
}