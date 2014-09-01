package com.github.approval.converters;

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

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

/**
 * An array converter that uses another converter for it's items.
 * This allows this converter to be composed with another one and allow you to convert your types even if they are in an array.
 * User: github
 * Date: 20/03/14
 * Time: 19:34
 * @param <T> The type of the items in the list that this converter accepts
 */
public class ArrayConverter<T> extends AbstractStringConverter<T[]> {
    private final Converter<T> typeConverter;


    /**
     * Creates an array converter that will use the other converter for it's items and just make array structure human readable.
     *
     * @param typeConverter the converters for the items in the array
     */
    public ArrayConverter(Converter<T> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Nonnull
    @Override
    protected String getStringForm(T[] values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            builder.append("[")
                    .append(i)
                    .append("] = ")
                    .append(new String(typeConverter.getRawForm(values[i]), StandardCharsets.UTF_8))
                    .append("\n");
        }
        return builder.toString();
    }
}
