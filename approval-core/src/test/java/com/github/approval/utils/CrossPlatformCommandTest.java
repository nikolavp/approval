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

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/**
 * User: github (Nikola Petrov) Date: 14-7-30 Time: 10:35
 */
public class CrossPlatformCommandTest {

    private TestClass instance;

    public static class TestClass extends CrossPlatformCommand<Void> {

        @Override
        protected Void onWindows() {
            return null;
        }

        @Override
        protected Void onUnix() {
            return null;
        }
    }

    @Test
    public void shouldCallUnixOnLinux() throws Exception {
        for(String name : new String[]{"Linux", "AIX", "Digital Unix"}) {
            instance = Mockito.spy(new TestClass());
            CrossPlatformCommand.setOS(name);

            instance.execute();

            verify(instance).onUnix();
        }
    }

    @Test
    public void shouldCallOnWindowsProperly() throws Exception {
        for(String name : new String[]{"Windows 95", "Windows 98", "Windows Me", "Windows NT", "Windows 2000", "Windows XP", "Windows 7", "Windows 2003", "Windows 2008"}) {
            instance = Mockito.spy(new TestClass());

            CrossPlatformCommand.setOS(name);

            instance.execute();

            verify(instance).onWindows();
        }
    }

    @Test
    public void shouldCallOnSolarisProperly() throws Exception {
        for(String name : new String[]{"Sun OS", "SunOS"}) {
            instance = Mockito.spy(new TestClass());

            CrossPlatformCommand.setOS(name);

            instance.execute();

            verify(instance).onSolaris();
        }
    }

    @Test
    public void shouldCallOnMacProperly() throws Exception {
        for(String name : new String[]{"Mac OS", "Mac OS X"}) {
            instance = Mockito.spy(new TestClass());

            CrossPlatformCommand.setOS(name);

            instance.execute();

            verify(instance).onMac();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnExceptionIfItCannotFindOS() throws Exception {
        instance = Mockito.spy(new TestClass());

        CrossPlatformCommand.setOS("my new OS name");

        instance.execute();
    }
}
