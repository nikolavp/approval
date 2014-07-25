package com.nikolavp.approval;

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
