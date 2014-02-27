package com.nikolavp.approval;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * User: nikolavp
 * Date: 27/02/14
 * Time: 11:54
 */
public class DefaultFileSystemUtilsTest {



    @Test(expected = IOException.class)
    public void shouldProperlyThrowAnExceptionIfItCannotCreateDirectory() throws Exception {
        //assign

        File file = FileSystems.getDefault().getPath("target", "directory-with-no-permissions").toFile();
        /* Delete it from the previous test */
        file.delete();

        if(!file.mkdir()) {
            throw new AssertionError("Couldn't create fixture directory?");
        }
        file.setReadable(false);
        file.setExecutable(false);
        file.setWritable(false);


        new DefaultFileSystemUtils().createDirectories(new File(file, "t"));
    }

    @Test
    public void shouldProperlyCreateDirectory() throws Exception {
        Path target = FileSystems.getDefault().getPath("target", "directory-with-permissions");
        File directory = target.toFile();
        /* Delete it from the previous test */
        directory.delete();
        new DefaultFileSystemUtils().createDirectories(directory);
        //no exceptions!
    }
}
