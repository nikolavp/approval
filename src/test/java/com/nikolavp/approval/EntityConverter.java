package com.nikolavp.approval;

import com.nikolavp.approval.converters.Converter;
import sun.management.resources.agent_it;

public class EntityConverter implements Converter<Entity> {
    @Override
    public byte[] getRawForm(Entity value) {
        return ("Entity is:\n" +
                "age = " + value.getAge() + "\n" +
                "name = " + value.getName() + "\n").getBytes();
    }
}
