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
import java.util.List;

/**
 * A list converter that uses another converter for it's items.
 * This allows this converter to be composed with another one and allow you to convert your types even if they are in a list.
 * User: github
 * Date: 28/02/14
 * Time: 17:47
 * @param <T> The type of the items in the list that this converter accepts
 */
public class ListConverter<T> extends AbstractStringConverter<List<T>> {
    private final Converter<T> typeConverter;

    /**
     * Creates a list converter that will use the other converter for it's items and just make list structure human readable.
     * @param typeConverter the converters for the items
     */
    public ListConverter(Converter<T> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Nonnull
    @Override
    protected String getStringForm(List<T> values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            builder.append("[")
                    .append(i)
                    .append("] = ")
                    .append(new String(typeConverter.getRawForm(values.get(i)), StandardCharsets.UTF_8))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
