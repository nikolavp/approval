package com.nikolavp.approval;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by ontotext on 2/2/14.
 */
interface FileSystemUtils {
    FileSystemUtils DEFAULT = new FileSystemUtils() {
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
    };

    void write(Path path, byte[] value) throws IOException;

    byte[] readFully(Path path) throws IOException;

    void move(Path path, Path filePath) throws IOException;
}
