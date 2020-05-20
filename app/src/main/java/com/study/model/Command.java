package com.study.model;

/**
 * 说明：命令对象
 * 用于包含要启动的 Activity
 * <p>
 * date: 2020/4/21 13:47
 *
 * @author syd
 * @version 1.0
 */
public class Command {
    private String desc;
    private Class cls;
    private int category;
    private String action;

    private Command(String desc, Class cls) {
        this.desc = desc;
        this.cls = cls;
        category = 0;
    }

    private Command(String desc, Class cls, int category) {
        this.desc = desc;
        this.cls = cls;
        this.category = category;
    }

    public static Command getInstance(String desc, Class cls) {
        return new Command(desc, cls);
    }

    public static Command getInstance(String desc, Class cls, int category) {
        return new Command(desc, cls, category);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }


}
