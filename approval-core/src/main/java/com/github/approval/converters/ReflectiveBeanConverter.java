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
import java.lang.reflect.Field;

/**
 * A converter that accepts a bean object and uses reflection to introspect the fields of the bean and builds a raw form of them.
 * Note that the fields must have a human readable string representation for this converter to work properly.
 * User: github
 * Date: 28/02/14
 * Time: 15:12
 *
 * @param <T> the type of objects you want convert to it's raw form
 */
public class ReflectiveBeanConverter<T> extends AbstractStringConverter<T> {
    @Nonnull
    @Override
    protected String getStringForm(T value) {
        Field[] fields = value.getClass().getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue;
            try {
                fieldValue = field.get(value);
                if (fieldValue != null) {
                    builder.append(field.getName())
                           .append(" = ")
                           .append(fieldValue)
                           .append(System.lineSeparator());
                }
            } catch (IllegalAccessException e) {
                /* This shouldn't happen because we have set accessible = true */
                throw new IllegalStateException("Couldn't access field " + field.getName());
            }
        }
        return builder.toString();
    }
}
