package com.github.approval.reporters;

/*
 * #%L
 * com.github.github:approval-core
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

import com.github.approval.Approval;
import com.github.approval.Reporter;
import com.github.approval.pathmappers.ParentPathMapper;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.awt.GraphicsEnvironment;
import java.nio.file.Paths;

@Ignore
public class WindowsReportersIT {

    public static final ParentPathMapper<String> MAPPER = new ParentPathMapper<String>(Paths.get("target", "verifications", WindowsReportersIT.class.getName()));

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testNotePadPlusPlus() throws Exception {
        testReporter(WindowsReporters.notepadPlusPlus());
    }

    @Test
    public void testBeyondCompare() {
        testReporter(WindowsReporters.beyondCompare());
    }

    @Test
    public void testTortoiseText() {
        testReporter(WindowsReporters.tortoiseText());
    }

    @Test
    public void testWinMerge() throws Exception {
        testReporter(WindowsReporters.winMerge());
    }


    private void testReporter(Reporter reporter) {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Approval<String> approval = Approval.of(String.class)
                .withPathMapper(MAPPER)
                .withReporter(reporter).build();
        approval.verify("some test content\n", Paths.get(testName.getMethodName()));
    }

}
