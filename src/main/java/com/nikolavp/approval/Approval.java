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

import com.nikolavp.approval.converters.Converter;
import com.nikolavp.approval.converters.Converters;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * The main entry point class for each approval process. This is the main service class that is doing the hard work - it calls other classes for custom logic based on the object that is approved.
 * Created by nikolavp on 1/29/14.
 *
 * @param <T> the type of the object that will be approved by this {@link Approval}
 */
public class Approval<T> {
    private static final Logger LOG  = Logger.getLogger(Approval.class.getName());
    private static final String FOR_APPROVAL_EXTENSION = ".forapproval";
    private final Reporter reporter;
    private final com.nikolavp.approval.utils.FileSystemUtils fileSystemReadWriter;
    private final Converter<T> converter;
    private PathMapper<T> pathMapper;

    /**
     * Create a new object that will be able to approve "things" for you.
     * @param reporter  a reporter that will be notified as needed for approval events
     * @param converter a converter that will be responsible for converting the type for approval to raw form
     * @param pathMapper the path mapper that will be used
     */
    Approval(Reporter reporter, Converter<T> converter, @Nullable PathMapper<T> pathMapper) {
        this(reporter, converter, pathMapper, new com.nikolavp.approval.utils.DefaultFileSystemUtils());
    }


    /**
     * This ctor is for testing only.
     */
    Approval(Reporter reporter, Converter<T> converter, @Nullable PathMapper<T> pathMapper, com.nikolavp.approval.utils.FileSystemUtils fileSystemReadWriter) {
        this.fileSystemReadWriter = fileSystemReadWriter;
        this.converter = converter;
        this.reporter = reporter;
        this.pathMapper = pathMapper;
    }

    /**
     * Create a new approval builder that will be able to approve objects from the specified class type.
     *
     * @param clazz the class object for the things you will be approving
     * @param <T>   the type of the objects you will be approving
     * @return an approval builder that will be able to construct an {@link Approval} for your objects
     */
    @Nonnull
    public static <T> ApprovalBuilder<T> of(Class<T> clazz) {
        return new ApprovalBuilder<T>(clazz);
    }

    /**
     * Get the path for approval from the original file path.
     *
     * @param filePath the original path to value
     * @return the path for approval
     */
    @Nonnull
    public static Path getApprovalPath(Path filePath) {
        Pre.notNull(filePath, "filePath");

        return FileSystems.getDefault().getPath(filePath.toString() + FOR_APPROVAL_EXTENSION);
    }

    @SuppressWarnings("unchecked")
    private static <T> Converter<T> getConverterForPrimitive(Class<T> clazz) {
        if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
            return (Converter<T>) Converters.BYTE;
        } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return (Converter<T>) Converters.INTEGER;
        } else if (clazz.equals(String.class)) {
            return (Converter<T>) Converters.STRING;
        } else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
            return (Converter<T>) Converters.SHORT;
        } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            return (Converter<T>) Converters.LONG;
        } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            return (Converter<T>) Converters.BOOLEAN;
        } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            return (Converter<T>) Converters.FLOAT;
        } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            return (Converter<T>) Converters.DOUBLE;
        } else if (clazz.equals(Character.class) || clazz.equals(char.class)) {
            return (Converter<T>) Converters.CHAR;
        } else if (clazz.equals(byte[].class)) {
            return (Converter<T>) Converters.BYTE_ARRAY;
        } else if (clazz.equals(int[].class)) {
            return (Converter<T>) Converters.INTEGER_ARRAY;
        } else if (clazz.equals(short[].class)) {
            return (Converter<T>) Converters.SHORT_ARRAY;
        } else if (clazz.equals(long[].class)) {
            return (Converter<T>) Converters.LONG_ARRAY;
        } else if (clazz.equals(float[].class)) {
            return (Converter<T>) Converters.FLOAT_ARRAY;
        } else if (clazz.equals(double[].class)) {
            return (Converter<T>) Converters.DOUBLE_ARRAY;
        } else if (clazz.equals(boolean[].class)) {
            return (Converter<T>) Converters.BOOLEAN_ARRAY;
        } else if (clazz.equals(char[].class)) {
            return (Converter<T>) Converters.CHAR_ARRAY;
        } else if (clazz.equals(String[].class)) {
            return (Converter<T>) Converters.STRING_ARRAY;
        }
        throw new IllegalArgumentException(clazz + " is not a primitive type class!");
    }

    /* Expose this to the tests */
    @Nonnull
    Converter<T> getConverter() {
        return converter;
    }

    /* Expose this to the tests */
    @Nonnull
    Reporter getReporter() {
        return reporter;
    }

    /**
     * Verify the value that was passed in.
     *
     * @param value    the value object to be approved
     * @param filePath the path where the value will be kept for further approval
     */
    public void verify(@Nullable T value, Path filePath) {
        Pre.notNull(filePath, "filePath");

        File file = mapFilePath(value, filePath);

        File parentPathDirectory = file.getParentFile();
        if (parentPathDirectory != null && !parentPathDirectory.exists()) {
            try {
                fileSystemReadWriter.createDirectories(parentPathDirectory);
            } catch (IOException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        Path approvalPath = getApprovalPath(file.toPath());
        byte[] rawValue = converter.getRawForm(value);
        if (!file.exists()) {
            LOG.info(file + " didn't exist. You will be asked for approval");
            handleFirstTimeApproval(file, approvalPath, rawValue);
            return;
        }
        try {
            byte[] fileContent = fileSystemReadWriter.readFully(file.toPath());
            if (!Arrays.equals(fileContent, rawValue)) {
                try {
                    LOG.info("Approval in " + file + " is not the same as the last value. You will be asked for approval of the new value.");
                    fileSystemReadWriter.write(approvalPath, rawValue);
                } catch (IOException e) {
                    throw new AssertionError("Couldn't write the new approval file " + file, e);
                }
                reporter.notTheSame(fileContent, file, rawValue, approvalPath.toFile());
            }
        } catch (IOException e) {
            throw new AssertionError("Couldn't read the previous content in file " + file, e);
        }

        //value approved
    }

    private void handleFirstTimeApproval(File file, Path approvalPath, byte[] rawValue) {
        try {
            fileSystemReadWriter.write(approvalPath, rawValue);

            approvalPath.toFile().deleteOnExit();
        } catch (IOException e) {
            throw new AssertionError("Couldn't write path for approval " + approvalPath, e);
        }
        final Path path = file.toPath();
        try {
            fileSystemReadWriter.touch(path);
        } catch (IOException ex) {
            throw new AssertionError("Couldn't create path " + path);
        }
        reporter.approveNew(rawValue, approvalPath.toFile(), file);
    }

    private File mapFilePath(@Nullable T value, Path filePath) {
        File file;
        if (pathMapper != null) {
            file = pathMapper.getPath(value, filePath).toFile();
        } else {
            file = filePath.toFile();
        }
        return file;
    }

    PathMapper<T> getPathMapper() {
        return pathMapper;
    }

    /**
     * A builder class for approvals. This is used to conveniently build new approvals for a specific type with custom reporters, converters, etc.
     *
     * @param <T> the type that will be approved by the the resulting approval object
     */
    public static final class ApprovalBuilder<T> {

        private final Class<T> clazz;
        private Converter<T> converter;
        private Reporter reporter;
        private PathMapper<T> pathMapper;

        private ApprovalBuilder(Class<T> clazz) {
            this.clazz = clazz;
        }

        /**
         * Set the converter that will be used when building new approvals with this builder.
         *
         * @param converterToBeUsed the converter that will be used from the approval that will be built
         * @return the same builder for chaining
         * @see Converter
         */
        @Nonnull
        public ApprovalBuilder<T> withConveter(Converter<T> converterToBeUsed) {
            Pre.notNull(converterToBeUsed, "converter");

            this.converter = converterToBeUsed;
            return this;
        }

        /**
         * Set a path mapper that will be used when building the path for approval results.
         * @param pathMapperToBeUsed the path mapper
         * @return the same builder for chaining
         */
        @Nonnull
        public ApprovalBuilder<T> withPathMapper(PathMapper<T> pathMapperToBeUsed) {
            Pre.notNull(pathMapperToBeUsed, "pathMapper");

            this.pathMapper = pathMapperToBeUsed;
            return this;
        }

        /**
         * Creates a new approval with configuration/options(reporters, converters, etc) that were set for this builder.
         *
         * @return a new approval for the specified type with custom configuration if any
         */
        @Nonnull
        public Approval<T> build() {
            if (converter == null) {
                try {
                    converter = getConverterForPrimitive(clazz);
                } catch (IllegalArgumentException ex) {
                    throw new IllegalStateException(String.format("You didn't provide a converter for %s and it is not a primitive type!", clazz));
                }
            }
            if (reporter == null) {
                throw new IllegalStateException("You didn't provide a reporter!");
            }
            return new Approval<T>(reporter, converter, pathMapper);
        }

        /**
         * Set the reporter that will be used when building new approvals with this builder.
         *
         * @param reporterToBeUsed the reporter that will be used from the approval that will be built
         * @return the same builder for chaninig
         * @see Reporter
         */
        public ApprovalBuilder<T> withReporter(Reporter reporterToBeUsed) {
            Pre.notNull(reporterToBeUsed, "reporter");

            this.reporter = reporterToBeUsed;
            return this;
        }

    }
}
