package com.nikolavp.approval;

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
