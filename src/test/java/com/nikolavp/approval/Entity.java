package com.nikolavp.approval;

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
