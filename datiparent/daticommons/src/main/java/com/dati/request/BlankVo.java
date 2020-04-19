package com.dati.request;

import java.util.List;
public class BlankVo {
    private String group_topic_id;
    private String title;
    private List<String> answerBlank;
    private Integer title_type;
    private String user_id;
    private Integer topics_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getTopics_id() {
        return topics_id;
    }

    public void setTopics_id(Integer topics_id) {
        this.topics_id = topics_id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAnswerBlank() {
        return answerBlank;
    }

    public void setAnswerBlank(List<String> answerBlank) {
        this.answerBlank = answerBlank;
    }
}
