package com.nikolavp.approval.reporters;

import com.nikolavp.approval.Reporter;

/**
 * Created with IntelliJ IDEA.
 * User: nikolavp
 * Date: 10/02/14
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public final class Reporters {
    private Reporters() {

    }

    private static final ExecutableDifferenceReporter VIM_INSTANCE = new ExecutableDifferenceReporter("gvim -f", "gvimdiff -f");
    private static final ExecutableDifferenceReporter GEDIT = new ExecutableDifferenceReporter("gedit", "gedit");
    private static final ExecutableDifferenceReporter CONSOLE_REPORTER_INSTANCE = new ExecutableDifferenceReporter("cat", "diff -u");

    /**
     * Creates a convenient gvim reporter.
     *
     * This one will use gvimdiff for difference detection and gvim for approving new files.
     * The proper way to exit vim and not approve the new changes is with ":cq" - just have that in mind!
     *
     * @return a reporter that uses vim under the hood
     */
    public static Reporter gvim() {
        return VIM_INSTANCE;
    }

    /**
     * Creates a simple reporter that will print/report approvals to the console.
     *
     * This reporter will use convenient command line tools under the hood to only report the changes in finds.
     * This is perfect for batch modes or when you run your build in a CI server
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
}
