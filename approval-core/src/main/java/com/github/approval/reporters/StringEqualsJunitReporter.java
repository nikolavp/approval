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


import org.junit.Assert;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * A reporter that uses assertEquals to report differences. This will fire the {@link org.junit.ComparisonFailure} so
 * will most probably give you a proper diff in your IDE.
 */
public class StringEqualsJunitReporter extends AbstractReporter {

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
        Assert.assertEquals(new String(oldValue, StandardCharsets.UTF_8), new String(newValue, StandardCharsets.UTF_8));
    }

    @Override
    public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {
        Assert.assertEquals("", new String(value, StandardCharsets.UTF_8));
    }

    @Override
    public boolean canApprove(File fileForApproval) {
        return true;
    }
}
