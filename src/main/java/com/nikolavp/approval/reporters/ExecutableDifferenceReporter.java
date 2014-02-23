package com.nikolavp.approval.reporters;

import com.nikolavp.approval.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * A reporter that will shell out to an executable that is presented on the user's machine to verify the test output. Note that the approval command and the difference commands can be the same.
 * <ul>
 *     <li>approval command will be used for the first approval</li>
 *     <li>the difference command will be used when there is already a verified file but it is not the same as the value from the user</li>
 * </ul>
 *
 */
public class ExecutableDifferenceReporter implements Reporter {
    private final String diffCommand;
    private final Runtime runtime;
    private final String approvalCommand;

    /**
     * Main constructor for the executable reporter.
     * @param approvalCommand the approval command
     * @param diffCommand the difference command
     */
    public ExecutableDifferenceReporter(String approvalCommand, String diffCommand) {
        this(approvalCommand, diffCommand, Runtime.getRuntime());
    }


    ExecutableDifferenceReporter(String approvalCommand, String diffCommand, Runtime runtime) {
        this.runtime = runtime;
        this.approvalCommand = approvalCommand;
        this.diffCommand = diffCommand;
    }

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
        try {
            Process process = this.runtime.exec(diffCommand + " " + fileForApproval.getAbsolutePath() + " " + fileForVerification.getAbsolutePath());
            execute(process);
        } catch (IOException e) {
            throw new AssertionError("There was a problem while executing %s", e);
        }
    }

    /* Copy pasted from Files.copy because they don't expose that method. So much fun!*/
    private static final int BUFFER_SIZE = 8192;
    private static long copy(InputStream source, OutputStream sink) throws IOException {
        long nread = 0L;
        byte[] buf = new byte[BUFFER_SIZE];
        int n;
        while ((n = source.read(buf)) > 0) {
            sink.write(buf, 0, n);
            nread += n;
        }
        return nread;
    }


    @Override
    public boolean approveNew(byte[] value, File approvalDestination, File fileForVerification) {
        try {
            Process process = runtime.exec(approvalCommand + " " + approvalDestination.getAbsolutePath());
            int processExit = execute(process);
            if (processExit == 0) {
                return true;
            }

        } catch (IOException e) {
            throw new AssertionError(String.format("There was a problem while executing %s", approvalCommand), e);
        }
        return false;
    }

    private int execute(Process process) throws IOException{
        copy(process.getInputStream(), System.out);
        copy(process.getErrorStream(), System.err);
        try {
            return process.waitFor();
        } catch (InterruptedException e) {
            throw new AssertionError("Thread was interrupted while waiting for process to finish", e);
        }
    }
}
