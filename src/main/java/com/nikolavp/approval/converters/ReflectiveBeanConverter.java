package com.nikolavp.approval.converters;

import javax.annotation.Nonnull;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

/**
 * A converter that accepts a bean object and uses reflection to introspect the fields of the bean and builds a raw form of them.
 * Note that the fields must have a human readable string representation for this converter to work properly.
 * User: nikolavp
 * Date: 28/02/14
 * Time: 15:12
 *
 * @param <T> the type of objects you want convert to it's raw form
 */
public class ReflectiveBeanConverter<T> implements Converter<T> {
    @Nonnull
    @Override
    public byte[] getRawForm(T value) {
        Field[] fields = value.getClass().getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue;
            try {
                fieldValue = field.get(value);
                if (fieldValue != null) {
                    builder.append(field.getName() + " = " + fieldValue + "\n");
                }
            } catch (IllegalAccessException e) {
                /* This shouldn't happen because we have set accessible = true */
                throw new IllegalStateException("Couldn't access field " + field.getName());
            }
        }
        try {
            return builder.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            /* UTF is guaranteed to be there on modern systems */
            throw new IllegalStateException("UTF is not supported on your platform!?", e);
        }
    }
}
