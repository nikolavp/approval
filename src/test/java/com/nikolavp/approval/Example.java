package com.nikolavp.approval;

import com.nikolavp.approval.reporters.Reporters;
import org.junit.Test;

import java.nio.file.FileSystems;

/**
 * Created with IntelliJ IDEA.
 * User: nikolavp
 * Date: 10/02/14
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class Example {
    @Test
    public void shouldVerifyProperly() throws Exception {
        //assign
        String string = "some\n long\n string\n";

        //act
        string = string.replace("\n", "\t");

        //assert
        new Approval(Reporters.console()).verify(string.getBytes(), FileSystems.getDefault().getPath("test.json"));
    }
}
