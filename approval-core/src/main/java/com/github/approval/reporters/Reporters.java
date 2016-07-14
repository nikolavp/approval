package com.github.approval.reporters;

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

import com.github.approval.Reporter;
import com.github.approval.utils.CrossPlatformCommand;
import com.github.approval.utils.ExecutableExistsOnPath;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: github
 * Date: 10/02/14
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public final class Reporters {
    private Reporters() {

    }

    private static final Reporter VIM_INSTANCE = new ExecutableDifferenceReporter("gvimdiff -f", "gvimdiff -f", "gvimdiff");
    private static final Reporter GEDIT = SwingInteractiveReporter.wrap(new ExecutableDifferenceReporter("gedit -w", "gedit -w", "gedit") {
        @Override
        protected String[] buildApproveNewCommand(File approvalDestination, File fileForVerification) {
            return new String[] {getApprovalCommand(), approvalDestination.getAbsolutePath()};
        }
    });
    private static final Reporter CONSOLE_REPORTER_INSTANCE = SwingInteractiveReporter.wrap(new ExecutableDifferenceReporter("cat", "diff -u", "cat") {
        @Override
        protected String[] buildApproveNewCommand(File approvalDestination, File fileForVerification) {
            return new String[] {getApprovalCommand(), approvalDestination.getAbsolutePath()};
        }

        @Override
        public boolean canApprove(File fileForApproval) {
            return super.canApprove(fileForApproval) && new ExecutableExistsOnPath("diff").execute();
        }
    });

    /**
     * Creates a convenient gvim reporter.
     * <p>
     * This one will use gvimdiff for difference detection and gvim for approving new files.
     * The proper way to exit vim and not approve the new changes is with ":cq" - just have that in mind!
     * </p>
     *
     * @return a reporter that uses vim under the hood
     */
    public static Reporter gvim() {
        return VIM_INSTANCE;
    }

    /**
     * Creates a simple reporter that will print/report approvals to the console.
     * <p>
     * This reporter will use convenient command line tools under the hood to only report the changes in finds.
     * This is perfect for batch modes or when you run your build in a CI server
     * </p>
     *
     * @return a reporter that uses console unix tools under the hood
     */
    public static Reporter console() {
        return CONSOLE_REPORTER_INSTANCE;
    }

    /**
     * Creates a reporter that uses gedit under the hood for approving.
     *
     * @return a reporter that uses gedit under the hood
     */
    public static Reporter gedit() {
        return GEDIT;
    }

    /**
     * A reporter that launches the file under test. This is useful if you for example are generating an spreadsheet and want to verify it.
     *
     * @return a reporter that launches the file
     */
    public static Reporter fileLauncher() {
        return SwingInteractiveReporter.wrap(fileLauncherWithoutInteraction());
    }


    /**
     * Same as {@link #fileLauncher} but doesn't do the swing interaction after it opens the file. This is mostly used to reuse the logic
     * of file launching without adding the burden for prompting the user after that.
     *
     * @return a reporter that launches the file
     */
    public static Reporter fileLauncherWithoutInteraction() {
        final String cmd = new CrossPlatformCommand<String>() {

            @Override
            protected String onWindows() {
                return "cmd /C start";
            }

            @Override
            protected String onMac() {
                return "open";
            }

            @Override
            protected String onUnix() {
                return "xdg-open";
            }

        }.execute();

        return new ExecutableDifferenceReporter(cmd, cmd, null) {
            @Override
            protected String[] buildApproveNewCommand(File approvalDestination, File fileForVerification) {
                return new String[]{getApprovalCommand(), approvalDestination.getAbsolutePath()};
            }

            @Override
            protected String[] buildNotTheSameCommand(File fileForVerification, File fileForApproval) {
                return new String[]{getDiffCommand(), fileForApproval.getAbsolutePath()};
            }
        };
    }

    /**
     * A reporter that compares images. Currently this uses <a href="http://www.imagemagick.org/script/binary-releases.php">imagemagick</a>
     * for comparison. If you only want to view the new image on first approval and when there is a difference, then you better use the {@link #fileLauncher()}
     * reporter which will do this for you.
     *
     * @return the reporter that uses ImagemMagick for comparison
     */
    public static Reporter imageMagick() {

        return SwingInteractiveReporter.wrap(new Reporter() {

            private final Reporter other = fileLauncher();

            @Override
            public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
                try {
                    final File compareResult = File.createTempFile("compareResult", fileForVerification.getName());
                    final Process compare = ExecutableDifferenceReporter.runProcess("compare", fileForApproval.getCanonicalPath(), fileForVerification.getAbsolutePath(), compareResult.getAbsolutePath());
                    final int result = compare.waitFor();
                    if (result != 0) {
                        throw new IllegalStateException("Couldn't execute compare!");
                    }
                    other.approveNew(/* unused */newValue, /* only used value*/compareResult, /* unused */fileForVerification);
                } catch (IOException | InterruptedException e) {
                    throw new AssertionError("Couldn't create file!", e);
                }
            }

            @Override
            public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {
                other.approveNew(value, fileForApproval, fileForVerification);
            }

            @Override
            public boolean canApprove(File fileForApproval) {
                return other.canApprove(fileForApproval);
            }
        });
    }

    /**
     * Get a reporter that will use the first working reporter as per {@link com.github.approval.Reporter#canApprove}
     * for the reporting.
     *
     * @param others an array/list of reporters that will be used
     * @return the newly created composite reporter
     */
    public static Reporter firstWorking(Reporter... others) {
        return new FirstWorkingReporter(others);
    }


    private static final Reporter NOOP_REPORTER = new Reporter() {
        @Override
        public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
            throw new UnsupportedOperationException("Shouldn't be called");
        }

        @Override
        public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {
            throw new UnsupportedOperationException("Shouldn't be called");
        }

        @Override
        public boolean canApprove(File fileForApproval) {
            return false;
        }
    };

    /**
     * A reporter that always throws an exception and shouldn't be called. This can be used as a safe fallback
     * for composing reporters. Note that it will return false in Reporter#canApprove so it shouldn't be called
     * by the approval infrastructure.
     *
     * @return a reporter that does nothing and return false in can approve
     */
    public static Reporter noop() {
        return NOOP_REPORTER;
    }
}
