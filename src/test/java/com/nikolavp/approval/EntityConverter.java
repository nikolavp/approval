package com.nikolavp.approval;

import com.nikolavp.approval.converters.Converter;

import java.nio.charset.StandardCharsets;

public class EntityConverter implements Converter<Entity> {
    @Override
    public byte[] getRawForm(Entity value) {
        return ("Entity is:\n" +
                "age = " + value.getAge() + "\n" +
                "name = " + value.getName() + "\n").getBytes(StandardCharsets.UTF_8);
    }
}
