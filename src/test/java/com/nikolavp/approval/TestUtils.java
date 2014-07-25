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
import java.nio.charset.StandardCharsets;

/**
 * Created by ontotext on 2/2/14.
 */
public class TestUtils {
    public static final String VALUE = "some\nmultiline\nnsimple\nstring";
    public static final byte[] RAW_VALUE = VALUE.getBytes(StandardCharsets.UTF_8);

    public static File forApproval(TestTempFile tmpTestFile) {
        return Approval.getApprovalPath(tmpTestFile.file().toPath()).toFile();
    }
}
