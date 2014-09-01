package com.nikolavp.approval.utils;

/*
 * #%L
 * com.github.nikolavp:approval-core
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

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

/**
 * User: nikolavp (Nikola Petrov) Date: 14-9-1 Time: 8:39
 */
public class ExecutableExistsOnPathTest {
    @Test
    public void shouldProperlyExecuteOnWindows() throws Exception {
        CrossPlatformCommand.setOS("Windows NT");
        Assert.assertThat(new ExecutableExistsOnPath("non-existing-executable").execute(), CoreMatchers.equalTo(false));
    }

    @Test
    public void shouldProperlyExecuteOnUnix() throws Exception {
        CrossPlatformCommand.setOS("Linux");
        Assert.assertThat(new ExecutableExistsOnPath("non-existing-executable").execute(), CoreMatchers.equalTo(false));

        Assume.assumeTrue(CrossPlatformCommand.isUnix());
        Assert.assertThat(new ExecutableExistsOnPath("vim").execute(), CoreMatchers.equalTo(true));
    }
}
