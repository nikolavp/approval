package com.github.approval.utils;

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

import com.github.approval.reporters.ExecutableDifferenceReporter;

import java.io.IOException;

/**
 * An utility class that checks if a specified executable exists in your PATH environment variable.
 */
public class ExecutableExistsOnPath extends CrossPlatformCommand<Boolean> {
    private final String executable;

    /**
     * Main constructor that acceptance the executable to check for.
     *
     * @param executable the executable name
     */
    public ExecutableExistsOnPath(String executable) {
        this.executable = executable;
    }

    @Override
    protected Boolean onWindows() {
        try {
            return ExecutableDifferenceReporter.runProcess("where " + executable)
                    .waitFor() == 0;
        } catch (InterruptedException e) {
            return Boolean.FALSE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    @Override
    protected Boolean onUnix() {
        try {
            //Type is the mostl portable way to check this. Some shells that implement POSIX don't implement the where or which builtin
            //http://stackoverflow.com/questions/4781772/how-to-test-if-an-executable-exists-in-the-path-from-a-windows-batch-file
            return ExecutableDifferenceReporter.runProcess("/bin/sh", "-c", "type " + executable)
                    .waitFor() == 0;
        } catch (InterruptedException e) {
            return Boolean.FALSE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }
}
