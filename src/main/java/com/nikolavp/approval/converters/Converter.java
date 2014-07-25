package com.nikolavp.approval.converters;

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
