package com.nikolavp.approval.reporters;

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


import com.nikolavp.approval.Reporter;
import com.nikolavp.approval.utils.CrossPlatformCommand;

import java.io.File;

/**
 * Reporters that use windows specific binaries, i.e. the programs that are used are not cross platform.
 * <p/>
 * If you are looking for something cross platform like gvim, emacs, you better look in {@link com.nikolavp.approval.reporters.Reporters}.
 */
public final class WindowsReporters {
    private WindowsReporters() {

    }

    /**
     *  Windows executable reporters should use this class instead of the more general ExecutableDifferenceReporter.
     */
    public static class WindowsExecutableReporter extends ExecutableDifferenceReporter {

        /**
         * Main constructor for the executable reporter.
         *
         * @param approvalCommand the approval command
         * @param diffCommand     the difference command
         * @param  executable     the executable which will be executed
         */
        public WindowsExecutableReporter(String approvalCommand, String diffCommand, String executable) {
            super(approvalCommand, diffCommand, executable);
        }

        @Override
        public boolean canApprove(File fileForApproval) {
            return super.canApprove(fileForApproval) && CrossPlatformCommand.isWindows();
        }
    }

    private static final Reporter NOTEPAD_PLUS_PLUS = SwingInteractiveReporter.wrap(new WindowsExecutableReporter("cmd /C notepad++", "cmd /C notepad++", "notepad++") {
        @Override
        protected String[] buildApproveNewCommand(File approvalDestination, File fileForVerification) {
            return new String[] {getApprovalCommand(), approvalDestination.getAbsolutePath()};
        }
    });
    private static final ExecutableDifferenceReporter BEYOND_COMPARE = new WindowsExecutableReporter("cmd /C BCompare", "cmd /C BCompare", "BCompare");
    private static final ExecutableDifferenceReporter TORTOISE_IMAGE_DIFF = new WindowsExecutableReporter("cmd /C TortoiseIDiff", "cmd /C TortoiseIDiff", "TortoiseIDiff");
    private static final ExecutableDifferenceReporter TORTOISE_TEXT_DIFF = new WindowsExecutableReporter("cmd /C TortoiseMerge", "cmd /C TortoiseMerge", "TortoiseMerge");
    private static final ExecutableDifferenceReporter WIN_MERGE = new WindowsExecutableReporter("cmd /C WinMergeU", "cmd /C WinMergeU", "WinMergeU");

    /**
     * A reporter that calls <a href="http://notepad-plus-plus.org/">notepad++</a> to show you the results.
     *
     * @return a reporter that calls notepad++
     */
    public static Reporter notepadPlusPlus() {
        return NOTEPAD_PLUS_PLUS;
    }

    /**
     * A reporter that calls <a href="http://www.scootersoftware.com/moreinfo.php">Beyond Compare 3</a> to show you the results.
     *
     * @return a reporter that calls beyond compare
     */
    public static Reporter beyondCompare() {
        return BEYOND_COMPARE;
    }

    /**
     * A reporter that calls <a href="http://tortoisesvn.net/TortoiseIDiff.html">TortoiseIDiff</a> to show you the results.
     *
     * @return a reporter that calls tortoise image difference tool
     */
    public static Reporter tortoiseImage() {
        return TORTOISE_IMAGE_DIFF;
    }

    /**
     * A reporter that calls <a href="http://tortoisesvn.net/">TortoiseMerge</a> to show you the results.
     *
     * @return a reporter that calls tortoise difference tool for text
     */
    public static Reporter tortoiseText() {
        return TORTOISE_TEXT_DIFF;
    }

    /**
     * A reporter that calls <a href="http://winmerge.org/about/">WinMerge</a> to show you the results.
     *
     * @return a reporter that calls win merge
     */
    public static Reporter winMerge() {
        return WIN_MERGE;
    }
}
