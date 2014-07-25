package com.nikolavp.approval;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A default implementation for {@link FileSystemUtils}.
 *
 * This one just delegates to methods in {@link Files}.
 * User: nikolavp
 * Date: 27/02/14
 * Time: 12:26
 */
class DefaultFileSystemUtils implements FileSystemUtils {
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
        if (!directory.mkdirs()) {
            throw new IOException("Couldn't create directory " + directory);
        }
    }

}
