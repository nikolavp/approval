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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * A default implementation for {@link FileSystemUtils}.
 *
 * This one just delegates to methods in {@link Files}.
 * User: nikolavp
 * Date: 27/02/14
 * Time: 12:26
 */
class DefaultFileSystemUtils implements FileSystemUtils {

    private static final Logger LOG  = Logger.getLogger(DefaultFileSystemUtils.class.getName());

    @Override
    public void write(Path path, byte[] value) throws IOException {
        Files.write(path, value);
    }

    @Override
    public byte[] readFully(Path path) throws IOException {
        return Files.readAllBytes(path);
    }

    @Override
    public void move(Path path, Path filePath) throws IOException {
        Files.move(path, filePath);
    }

    @Override
    public void createDirectories(File directory) throws IOException {
        LOG.info("Creating directory " + directory);
        if (!directory.mkdirs()) {
            throw new IOException("Couldn't create directory " + directory);
        }
    }

}
