package com.nikolavp.approval.converters;

/**
 * Converters for primitive types. Most of these just call toString on the passed object and then get the raw representation of the string result.
 * .
 * User: nikolavp
 * Date: 28/02/14
 * Time: 17:25
 */
public final class Converters {
    /*
     Hack the type system to accept the types and prevent raw type warnings in the client side.
     */
    /**
     * A converter for the primitive or wrapper byte types.
     */
    public static final Converter<Byte> BYTE = of();
    /**
     * A converter for the String object.
     */
    public static final Converter<String> STRING = of();
    /**
     * A converter for the primitive or wrapper int object.
     */
    public static final Converter<Integer> INTEGER = of();
    /**
     * A converter for the primitive or wrapper short object.
     */
    public static final Converter<Short> SHORT = of();
    /**
     * A converter for the primitive or wrapper long object.
     */
    public static final Converter<Long> LONG = of();
    /**
     * A converter for the primitive or wrapper float object.
     */
    public static final Converter<Float> FLOAT = of();
    /**
     * A converter for the primitive or wrapper double object.
     */
    public static final Converter<Double> DOUBLE = of();

    private Converters() {

    }

    private static <T> Converter<T> of() {
        return new Converter<T>() {
            @Override
            public byte[] getRawForm(T value) {
                return value.toString().getBytes();
            }
        };
    }
}
