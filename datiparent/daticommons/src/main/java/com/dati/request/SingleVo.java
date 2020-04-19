package com.dati.request;

import java.io.Serializable;

/*
*
*单选题body类
* */
public class SingleVo implements Serializable {
   private String a;
   private String b;
   private String c;
   private String d;
   private String title;
   private String answer;
   private Integer title_type;
   private String group_topic_id;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getTitle_type() {
        return title_type;
    }

    public void setTitle_type(Integer title_type) {
        this.title_type = title_type;
    }

    public String getGroup_topic_id() {
        return group_topic_id;
    }

    public void setGroup_topic_id(String group_topic_id) {
        this.group_topic_id = group_topic_id;
    }
}
