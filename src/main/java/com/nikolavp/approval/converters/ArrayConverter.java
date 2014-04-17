package com.nikolavp.approval.converters;

/**
 * An array converter that uses another converter for it's items.
 * This allows this converter to be composed with another one and allow you to convert your types even if they are in an array.
 * User: nikolavp
 * Date: 20/03/14
 * Time: 19:34
 * @param <T> The type of the items in the list that this converter accepts
 */
public class ArrayConverter<T> implements Converter<T[]> {
    private final Converter<T> typeConverter;


    /**
     * Creates an array converter that will use the other converter for it's items and just make array structure human readable.
     *
     * @param typeConverter the converters for the items in the array
     */
    public ArrayConverter(Converter<T> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public byte[] getRawForm(T[] values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            builder.append("[" + i + "] = " + new String(typeConverter.getRawForm(values[i])) + "\n");
        }
        return builder.toString().getBytes();
    }
}
