package com.nikolavp.approval;

import com.nikolavp.approval.converters.Converter;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

public class EntityConverter implements Converter<Entity> {
    @Nonnull
    @Override
    public byte[] getRawForm(Entity value) {
        return ("Entity is:\n" +
                "age = " + value.getAge() + "\n" +
                "name = " + value.getName() + "\n").getBytes(StandardCharsets.UTF_8);
    }
}
