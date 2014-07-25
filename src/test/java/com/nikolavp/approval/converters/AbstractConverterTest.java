package com.nikolavp.approval.converters;

import org.junit.Test;

import javax.annotation.Nonnull;

/**
 * User: nikolavp (Nikola Petrov) Date: 14-7-23 Time: 17:18
 */
public class AbstractConverterTest {
    @Test
    public void shouldHaveADefaultCtor() throws Exception {
        new AbstractConverter() {
            @Nonnull
            @Override
            public byte[] getRawForm(Object value) {
                return new byte[0];
            }
        };
    }
}
