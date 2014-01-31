package com.nikolavp.approval;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Created by nikolavp on 1/29/14.
 */
public class Approval {

    private static final String FOR_APPROVAL_EXTENSION = ".forapproval";


    private final Reporter reporter;
    private final FileSystemUtils fileSystemReadWriter;

    /**
     * Create a new object that will be able to approve "things" for you.
     * @param reporter a reporter that will be notified as needed for approval events
     */
    public Approval(Reporter reporter) {
        this(reporter, FileSystemUtils.DEFAULT);
    }

    /** This ctor is for testing only. */
    Approval(Reporter reporter, FileSystemUtils fileSystemReadWriter) {
        this.fileSystemReadWriter = fileSystemReadWriter;
        this.reporter = reporter;
    }

    /**
     * Verify the value that was passed in.
     * @param value the value object to be approved
     * @param filePath the path where the value will be kept for further approval
     */
    public void verify(byte[] value, Path filePath) {
        File file = filePath.toFile();

        if (!file.exists()) {
            Path path = getApprovalPath(filePath);
            try {
                fileSystemReadWriter.write(path, value);
            } catch (IOException e) {
                throw new AssertionError("Couldn't write file for approval " + file, e);
            }
            if (reporter.approveNew(value, file)) {
                try {
                    fileSystemReadWriter.move(path, filePath);
                } catch (IOException e) {
                    String errorMessage = String.format("Couldn't move file for approval[%s] to the destination [%s]", path.toAbsolutePath(), filePath.toAbsolutePath());
                    throw new AssertionError(errorMessage);
                }
            }
            return;
        }
        try {
            byte[] fileContent = fileSystemReadWriter.readFully(file.toPath());
            if (!Arrays.equals(fileContent, value)) {
                reporter.notTheSame(fileContent, value, file);
            }
        } catch (IOException e) {
            throw new AssertionError("Couldn't read the previous content in file " + file, e);
        }

        //value approved
    }

    /**
     * Get the path for approval from the original file path.
     * @param filePath the original path to value
     * @return the path for approval
     */
    public static Path getApprovalPath(Path filePath) {
        return FileSystems.getDefault().getPath(filePath.toString() + FOR_APPROVAL_EXTENSION);
    }

}
