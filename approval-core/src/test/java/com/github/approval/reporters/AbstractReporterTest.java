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

import org.junit.Test;

import java.io.File;

/**
 * User: github (Nikola Petrov) Date: 14-7-23 Time: 17:18
 */
public class AbstractReporterTest {
    @Test
    public void shouldHaveADefultCtor() throws Exception {
        new AbstractReporter() {

            @Override
            public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {

            }

            @Override
            public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {
            }

            @Override
            public boolean canApprove(File fileForApproval) {
                return false;
            }
        };
    }
}
