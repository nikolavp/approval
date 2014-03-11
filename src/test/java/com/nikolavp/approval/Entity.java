package com.nikolavp.approval;

/**
 * Just an example entity to test different converters and generics API safety.
 * User: nikolavp
 * Date: 28/02/14
 * Time: 14:46
 */
public class Entity {

    private String name;
    private int age;

    public Entity(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
