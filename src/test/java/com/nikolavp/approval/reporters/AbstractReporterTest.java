package com.nikolavp.approval.reporters;

import org.junit.Test;

import java.io.File;

/**
 * User: nikolavp (Nikola Petrov) Date: 14-7-23 Time: 17:18
 */
public class AbstractReporterTest {
    @Test
    public void shouldHaveADefultCtor() throws Exception {
        new AbstractReporter() {

            @Override
            public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {

            }

            @Override
            public boolean approveNew(byte[] value, File fileForApproval, File fileForVerification) {
                return false;
            }

            @Override
            public boolean canApprove(File fileForApproval) {
                return false;
            }
        };
    }
}
