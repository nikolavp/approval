package com.nikolavp.approval.reporters;

import com.nikolavp.approval.Reporter;

/**
 * Created with IntelliJ IDEA.
 * User: nikolavp
 * Date: 10/02/14
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class Reporters {

    private static final ExecutableDifferenceReporter VIM_INSTANCE = new ExecutableDifferenceReporter("gvim -f", "gvimdiff -f");
    private static final ExecutableDifferenceReporter GEDIT = new ExecutableDifferenceReporter("gedit", "gedit");
    private static final ExecutableDifferenceReporter CONSOLE_REPORTER_INSTANCE = new ExecutableDifferenceReporter("cat", "diff -u");

    public static Reporter gvim() {
        return VIM_INSTANCE;
    }

    public static Reporter console() {
        return CONSOLE_REPORTER_INSTANCE;
    }

    public static Reporter gedit() {
        return GEDIT;
    }
}
