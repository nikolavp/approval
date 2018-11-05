package com.github.approval.converters;

/*
 * #%L
 * com.github.approval:core
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;

/**
 * A convenient abstract converter to handle object approvals on string representable objects.
 *
 * @param <T> the type you want to convert
 */
public abstract class AbstractStringConverter<T> extends AbstractConverter<T> {

    @Nonnull
    @Override
    public final byte[] getRawForm(@Nullable T value) {
        if (value == null) {
            return fixAtLeastOneBlankLineAtEOF("null").getBytes(StandardCharsets.UTF_8);
        }
        return fixAtLeastOneBlankLineAtEOF(getStringForm(value).trim()).getBytes(StandardCharsets.UTF_8);
    }

    private String fixAtLeastOneBlankLineAtEOF(String value) {
        return value.trim() + System.lineSeparator() + System.lineSeparator();
    }

    /**
     * Gets the string representation of the type object. This representation will be written in the files you are going
     * to then use in the approval process.
     *
     * @param value the object that you want to convert
     * @return the string representation of the object
     */
    @Nonnull
    protected abstract String getStringForm(T value);
}
