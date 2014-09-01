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

import java.util.Locale;

/**
 * A command that when run will execute the proper method for the specified operating system.
 * This is especially useful when you are trying to create for handling different platforms. Here is an example usage of the class:
 *
 * <pre>{@code
 * final Boolean result = new CrossPlatformCommand<Boolean>() {
 *     &#064;Override protected Boolean onWindows() {
 *     //do your logic for windows
 *     }
 *
 *     &#064;Override protected Boolean onUnix() {
 *     //do your logic for unix
 *     }
 *
 *     &#064;Override protected Boolean onMac() {
 *     //do your logic for mac
 *     }
 *
 *     &#064;Override protected Boolean onSolaris() {
 *     //do your logic for solaris
 *     }
 * }.execute();
 *
 * }</pre>
 *
 * @param <T> the result from the command.
 */
public abstract class CrossPlatformCommand<T> {
    private static String os = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

    static void setOS(String newOs) {
        CrossPlatformCommand.os = newOs.toLowerCase(Locale.ENGLISH);
    }

    /**
     * What to execute on windows.
     *
     * @return the result
     */
    protected abstract T onWindows();

    /**
     * What to execute on windows.
     *
     * @return the result
     */
    protected abstract T onUnix();

    /**
     * What to execute on macOS.
     *
     * @return the result
     */
    protected T onMac() {
        return onUnix();
    }

    /**
     * What to execute on solaris.
     *
     * @return the result
     */
    protected T onSolaris() {
        return onUnix();
    }

    /**
     * Main method that should be executed. This will return the proper result depending on your platform.
     *
     * @return the result
     */
    public T execute() {
        if (isWindows()) {
            return onWindows();
        } else if (isMac()) {
            return onMac();
        } else if (isUnix()) {
            return onUnix();
        } else if (isSolaris()) {
            return onSolaris();
        } else {
            throw new IllegalStateException("Invalid operating system " + os);
        }
    }

    /**
     * Check if the current OS is windows.
     *
     * @return true if windows and false otherwise
     */
    public static boolean isWindows() {
        return os.contains("win");
    }

    /**
     * Check if the current OS is MacOS.
     *
     * @return true if macOS and false otherwise
     */
    public static boolean isMac() {
        return os.contains("mac");
    }

    /**
     * Check if the current OS is some sort of unix.
     * @return true if unix and false otherwise
     */
    public static boolean isUnix() {
        return os.contains("nix") || os.contains("nux") || os.contains("aix");
    }

    /**
     * Check if the current OS is Solaris.
     * @return true if solaris and false otherwise
     */
    public static boolean isSolaris() {
        return os.contains("sunos") || os.contains("sun os");
    }
}
