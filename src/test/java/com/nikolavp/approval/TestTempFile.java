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

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by nikolavp on 1/29/14.
 */
public class TestTempFile implements MethodRule {
    private File file;

    public TestTempFile() {

    }
    public File file() {
        return file;
    }

    public Path path() {
        return file.toPath();
    }

    @Override
    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object o) {
        try {
            this.file = File.createTempFile("unit.tests", null);
            // we don't want this to be created but just want a temporary name
            this.file.delete();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't create a temporary file. You are out of disc space or something!?",e);
        }
        return statement;
    }
}
