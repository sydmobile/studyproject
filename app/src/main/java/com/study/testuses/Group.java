package com.study.testuses;

import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2020/3/30 15:05
 *
 * @author syd
 * @version 1.0
 */
public class Group {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getCategory() {
        return category;
    }

    public void setCategory(List<Item> category) {
        this.category = category;
    }

    List<Item> category;

}
