package com.study.model;

import androidx.annotation.NonNull;

/**
 * 说明：$d$
 * <p>
 * date: 2019/8/27 9:17
 *
 * @author syd
 * @version 1.0
 */
public class User {
    private String name;
    private int age;
    private float height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @NonNull
    @Override
    public String toString() {
        return "name:"+getName();
    }
}
