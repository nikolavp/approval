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
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class StringEqualsJunitReporterTest {

    public static final StringEqualsJunitReporter REPORTER = new StringEqualsJunitReporter();
    public static final String EXPECTED = "test";
    public static final String ACTUAL = "other";

    @Test
    public void shouldProperlyThrowAJunitComparisonError() throws Exception {
        REPORTER.notTheSame(EXPECTED.getBytes(StandardCharsets.UTF_8), new File("test"), EXPECTED.getBytes(StandardCharsets.UTF_8), new File("other"));
        try {
            REPORTER.notTheSame(EXPECTED.getBytes(StandardCharsets.UTF_8), new File("test"), ACTUAL.getBytes(StandardCharsets.UTF_8), new File("other"));
            Assert.fail("Should fail");
        } catch (ComparisonFailure e) {
            Assert.assertEquals(e.getExpected(), EXPECTED);
            Assert.assertEquals(e.getActual(), ACTUAL);
        }
    }

    @Test
    public void shouldProperlyThrowAnExceptionIfThisIsANewValue() throws Exception {
        REPORTER.approveNew("".getBytes(StandardCharsets.UTF_8), new File("test"), new File("other"));
        try {
            REPORTER.approveNew(ACTUAL.getBytes(StandardCharsets.UTF_8), new File("test"), new File("other"));
            Assert.fail("Should fail");
        } catch (ComparisonFailure e) {
            Assert.assertEquals(e.getExpected(), "");
            Assert.assertEquals(e.getActual(), ACTUAL);
        }
    }

    @Test
    public void shouldAlwaysBeAbleToApprove() throws Exception {
        Assert.assertTrue(REPORTER.canApprove(new File("test")));
    }
}