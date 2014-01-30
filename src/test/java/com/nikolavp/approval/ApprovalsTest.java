package com.nikolavp.approval;

/**
 * Created by nikolavp on 1/29/14.
 */
public class ApprovalsTest {

    @Test
    public void shouldVerifySimpleStringsProperly() {
        Approvals.verify("some\nmultiline\nnsimple\nstring");
    }

}
