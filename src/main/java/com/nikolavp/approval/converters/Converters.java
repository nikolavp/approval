package com.nikolavp.approval.converters;

import java.lang.reflect.Array;

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
     * A converter for the primitive or wrapper boolean object.
     */
    public static final Converter<Boolean> BOOLEAN = of();
    /**
     * A converter for the primitive or wrapper double object.
     */
    public static final Converter<Double> DOUBLE = of();
    /**
     * A converter for the primitive or wrapper char object.
     */
    public static final Converter<Character> CHAR = of();

    /**
     * A converter for the primitive int arrays.
     */
    public static final Converter<int[]> INTEGER_ARRAY = ofArray();
    /**
     * A converter for the primitive byte arrays.
     */
    public static final Converter<byte[]> BYTE_ARRAY = ofArray();
    /**
     * A converter for the primitive long arrays.
     */
    public static final Converter<long[]> LONG_ARRAY = ofArray();
    /**
     * A converter for the primitive short arrays.
     */
    public static final Converter<short[]> SHORT_ARRAY = ofArray();
    /**
     * A converter for the primitive double arrays.
     */
    public static final Converter<double[]> DOUBLE_ARRAY = ofArray();
    /**
     * A converter for the primitive boolean arrays.
     */
    public static final Converter<boolean[]> BOOLEAN_ARRAY = ofArray();
    /**
     * A converter for the primitive char arrays.
     */
    public static final Converter<char[]> CHAR_ARRAY = ofArray();
    /**
     * A converter for the primitive float arrays.
     */
    public static final Converter<float[]> FLOAT_ARRAY = ofArray();

    /**
     * A converter for an array of strings.
     */
    public static final Converter<String[]> STRING_ARRAY = ofArray();

    private Converters() {
    }

    static Converter ofArray() {
        return new Converter() {
            @Override
            public byte[] getRawForm(Object value) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < Array.getLength(value); i++) {
                    builder.append("[" + i + "] = " + String.valueOf(Array.get(value, i)) + "\n");
                }
                return builder.toString().getBytes();
            }
        };
    }

    static <T> Converter<T> of() {
        return new Converter<T>() {
            @Override
            public byte[] getRawForm(T value) {
                return value.toString().getBytes();
            }
        };
    }
}
