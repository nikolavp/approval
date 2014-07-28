package com.nikolavp.approval.reporters;

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

import com.nikolavp.approval.Approval;
import com.nikolavp.approval.pathmappers.ParentPathMapper;
import org.junit.Assume;
import org.junit.Test;

import java.awt.GraphicsEnvironment;
import java.nio.file.Paths;

/**
 *
 * User: nikolavp
 * Date: 26/02/14
 * Time: 14:28
 */
@org.junit.Ignore
public class ReportersExamplesIT {

    @Test
    public void testGvimApprovalProcess() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Approval<String> approval = Approval.of(String.class)
                .withPathMapper(new ParentPathMapper<String>(Paths.get("target", "verifications")))
                .withReporter(Reporters.gvim()).build();

        approval.verify("some test content\n", Paths.get("target", "verifications", "testGvimApprovalProcess.txt"));
    }

    @Test
    public void testConsoleApprovalProcess() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Approval<String> approval = Approval.of(String.class)
                .withPathMapper(new ParentPathMapper<String>(Paths.get("target", "verifications")))
                .withReporter(Reporters.console())
                .build();
        approval.verify("some test content\n", Paths.get("target", "verifications", "testConsoleApprovalProcess.txt"));
    }

    @Test
    public void testGeditApprovalProcess() throws Exception {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Approval<String> approval = Approval.of(String.class)
                .withPathMapper(new ParentPathMapper<String>(Paths.get("target", "verifications")))
                .withReporter(Reporters.gedit())
                .build();
        approval.verify("some test content\n", Paths.get("target", "verifications", "testGeditApprovalProcess.txt"));
    }
}
