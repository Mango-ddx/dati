package com.dati.pojo;
/*
* 用户班级表
*
* */
public class UserClass {
    private String id;
    private String class_name;
    private String explanation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getexplanation() {
        return explanation;
    }

    public void setexplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return "UserClass{" +
                "id='" + id + '\'' +
                ", class_name='" + class_name + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
