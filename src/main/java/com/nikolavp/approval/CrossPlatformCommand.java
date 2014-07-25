package com.nikolavp.approval;

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

import java.io.IOException;

/**
 * User: nikolavp (Nikola Petrov) Date: 14-7-25 Time: 18:08
 */
public abstract class CrossPlatformCommand<T> {
    private static String OS = System.getProperty("os.name").toLowerCase();

    protected abstract T onWindows();
    protected abstract T onUnix();
    protected abstract T onMac();
    protected abstract T onSolaris();

    public T execute() {
        if(isWindows()) {
            return onWindows();
        } else if (isMac()) {
            return onMac();
        } else if (isUnix()) {
            return onUnix();
        } else if (isSolaris()) {
            return onSolaris();
        } else {
            throw new IllegalStateException("Invalid operating system " + OS);
        }
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    public static boolean isSolaris() {
        return OS.contains("sunos");
    }

    public static void main(String[] args) {

        final String command = "tropa";

        final Boolean execute = new CrossPlatformCommand<Boolean>() {

            @Override
            protected Boolean onWindows() {
                try {
                    return Runtime.getRuntime().exec("where " + command).waitFor() == 0;
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException("Couldn't execute 'where " + command + "'");
                }
            }

            @Override
            protected Boolean onUnix() {
                try {
                    return Runtime.getRuntime().exec("which " + command).waitFor() == 0;
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException("Couldn't execute 'which " + command + "'");
                }
            }

            @Override
            protected Boolean onMac() {
                return onUnix();
            }

            @Override
            protected Boolean onSolaris() {
                return onUnix();
            }
        }.execute();
        System.out.println(execute);
    }
}
