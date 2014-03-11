package com.nikolavp.approval.converters;

import java.util.List;

/**
 * A list converter that uses another converter for it's items.
 * This allows this converter to be composed with another one and allow you to convert your types even if they are in a list.
 * User: nikolavp
 * Date: 28/02/14
 * Time: 17:47
 * @param <T> The type of the items in the list that this converter accepts
 */
public class ListConverter<T> implements Converter<List<T>> {
    private final Converter<T> typeConverter;

    /**
     * Creates a list converter that will use the other converter for it's items and just make list structure human readable.
     * @param typeConverter the converters for the items
     */
    public ListConverter(Converter<T> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public byte[] getRawForm(List<T> values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            builder.append("[" + i + "] = " + new String(typeConverter.getRawForm(values.get(i))) + "\n");
        }
        return builder.toString().getBytes();
    }
}
