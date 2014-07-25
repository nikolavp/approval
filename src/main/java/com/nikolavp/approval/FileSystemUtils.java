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
import java.nio.file.Path;

/**
 * This class is mostly used for indirection in the tests. We just don't like static utility classes.
 * Created by ontotext on 2/2/14.
 */
interface FileSystemUtils {
    void write(Path path, byte[] value) throws IOException;

    byte[] readFully(Path path) throws IOException;

    void move(Path path, Path filePath) throws IOException;

    void createDirectories(File parentPathDirectory) throws IOException;

}
