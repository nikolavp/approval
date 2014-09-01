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

/**
 * Reporters that use macOS specific binaries, i.e. not cross platform programs.
 * <p/>
 * If you are looking for something cross platform like gvim, emacs, you better look in {@link com.nikolavp.approval.reporters.Reporters}.
 */
public final class MacOSReporters {
    private MacOSReporters() {

    }

    private static final ExecutableDifferenceReporter KSDIFF = new ExecutableDifferenceReporter("ksdiff", "ksdiff", "ksdiff");
    private static final ExecutableDifferenceReporter DIFF_MERGE = new ExecutableDifferenceReporter("DiffMerge", "DiffMerge", "DiffMerge");
    private static final ExecutableDifferenceReporter P4_MERGE = new ExecutableDifferenceReporter("p4merge", "p4merge", "p4merge");
    private static final ExecutableDifferenceReporter TK_DIFF = new ExecutableDifferenceReporter("tkdiff", "tkdiff", "tkdiff");

    /**
     * A reporter that calls <a href="https://sourcegear.com/diffmerge/">diffmerge</a> to show you the results.
     *
     * @return a reporter that calls diffmerge
     */
    public static Reporter diffMerge() {
        return DIFF_MERGE;
    }

    /**
     * A reporter that calls <a href="http://www.kaleidoscopeapp.com/ksdiff2">ksdiff</a> to show you the results.
     *
     * @return a reporter that calls ksdiff
     */
    public static Reporter ksdiff() {
        return KSDIFF;
    }

    /**
     * A reporter that calls <a href="http://www.perforce.com/product/components/perforce-visual-merge-and-diff-tools">p4merge</a> to show you the results.
     *
     * @return a reporter that calls p4merge
     */
    public static Reporter p4merge() {
        return P4_MERGE;
    }

    /**
     * A reporter that calls <a href="http://sourceforge.net/projects/tkdiff/">tkdiff</a> to show you the results.
     *
     * @return a reporter that calls tkdiff
     */
    public static Reporter tkdiff() {
        return TK_DIFF;
    }
}
