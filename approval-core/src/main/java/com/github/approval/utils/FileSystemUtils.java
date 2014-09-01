package com.github.approval.utils;

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
import java.nio.file.Path;

/**
 * This class is mostly used for indirection in the tests. We just don't like static utility classes.
 * Created by ontotext on 2/2/14.
 */
public interface FileSystemUtils {
    /**
     * Write the byte value to the specified path.
     * @param path the path
     * @param value the value
     * @throws IOException if there was an error while writing the content
     */
    void write(Path path, byte[] value) throws IOException;

    /**
     * Read the specified path as byte array.
     * @param path the path to read
     * @return the path content
     * @throws IOException if there was an error while reading the content
     */
    byte[] readFully(Path path) throws IOException;

    /**
     * Move a path to another path.
     * @param path the source
     * @param filePath the destination
     * @throws IOException if there was an error while moving the paths
     */
    void move(Path path, Path filePath) throws IOException;

    /**
     * Create a directory and their parent directories as needed.
     *
     * @param directory the directory to create
     * @throws IOException if there was an error while creating the directories
     */
    void createDirectories(File directory) throws IOException;

    /**
     * Creates the specified path with empty content.
     *
     * @param pathToCreate the path to create
     * @throws IOException if there was error while creating the path
     */
    void touch(Path pathToCreate) throws IOException;
}
