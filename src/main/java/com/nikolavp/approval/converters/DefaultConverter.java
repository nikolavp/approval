package com.nikolavp.approval.converters;

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
