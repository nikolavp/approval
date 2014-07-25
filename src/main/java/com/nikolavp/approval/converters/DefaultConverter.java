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
 * Just a simple converter for byte array primitives. We might want to move this into {@link Converters}.
 * User: nikolavp
 * Date: 28/02/14
 * Time: 14:54
 */
public class DefaultConverter implements Converter<byte[]> {
    @Nonnull
    @Override
    public byte[] getRawForm(byte[] value) {
        return value;
    }
}
