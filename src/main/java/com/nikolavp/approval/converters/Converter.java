package com.nikolavp.approval.converters;

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

/**
 * A converter interface. Converters are the objects in the approval system that convert your object to their raw form that can be written to the files.
 * Note that the raw form is not always a string representation of the object. If for example your object is an image.
 * User: nikolavp
 * Date: 28/02/14
 * Time: 14:47
 * @param <T> the type you are going to convert to raw form
 */
public interface Converter<T> {
    /**
     * Gets the raw representation of the type object. This representation will be written in the files you are going to then use in the approval process.
     *
     * @param value the object that you want to convert
     * @return the raw representation of the object
     */
    @Nonnull
    byte[] getRawForm(T value);
}
