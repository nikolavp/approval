package com.nikolavp.approval.utils;

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

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * User: nikolavp
 * Date: 27/02/14
 * Time: 11:54
 */
public class DefaultFileSystemUtilsTest {

    @Rule
    public TemporaryFolder testFile = new TemporaryFolder();

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
        Path target = Paths.get("target", "directory-with-permissions");
        File directory = target.toFile();
        /* Delete it from the previous test */
        directory.delete();
        new DefaultFileSystemUtils().createDirectories(directory);
        //no exceptions!
    }


    @Test
    public void shouldProperlyMovePaths() throws Exception {
        final Path source = testFile.newFile("path-source").toPath();
        final Path destination = testFile.getRoot().toPath().resolve("path-destination");
        Assert.assertThat(destination.toFile().exists(), equalTo(false));
        new DefaultFileSystemUtils().move(source, destination);
        Assert.assertThat(destination.toFile().exists(), equalTo(true));
    }

    @Test
    public void shouldProperlyCreatePath() throws Exception {
        final File file = testFile.newFile("some-path");
        Assume.assumeTrue(file.delete());
        Assert.assertThat(file.exists(), equalTo(false));
        new DefaultFileSystemUtils().touch(file.toPath());
        Assert.assertThat(file.exists(), equalTo(true));
    }

    @Test
    public void shouldDeleteDestinationIfItExistsOnMove() throws Exception {
        final Path source = testFile.newFile("path-source").toPath();
        final Path destination = testFile.newFile("path-destination").toPath();
        new DefaultFileSystemUtils().move(source, destination);
        Assert.assertThat(destination.toFile().exists(), equalTo(true));
    }
}
