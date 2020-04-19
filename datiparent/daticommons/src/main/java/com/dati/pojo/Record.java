package com.dati.pojo;

public class Record {
    private String id;
    private String record_title;
    private String record_answer;
    private String user_answer;
    private Integer correct;
    private String user_id;
    private String group_topic_id;
    private Integer topics_id;
    private Integer title_type;
    private GroupTopic groupTopic;

    public GroupTopic getGroupTopic() {
        return groupTopic;
    }

    public void setGroupTopic(GroupTopic groupTopic) {
        this.groupTopic = groupTopic;
    }

    public Integer getTitle_type() {
        return title_type;
    }

    public void setTitle_type(Integer title_type) {
        this.title_type = title_type;
    }

    public Integer getTopics_id() {
        return topics_id;
    }

    public void setTopics_id(Integer topics_id) {
        this.topics_id = topics_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecord_title() {
        return record_title;
    }

    public void setRecord_title(String record_title) {
        this.record_title = record_title;
    }

    public String getRecord_answer() {
        return record_answer;
    }

    public void setRecord_answer(String record_answer) {
        this.record_answer = record_answer;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_topic_id() {
        return group_topic_id;
    }

    public void setGroup_topic_id(String group_topic_id) {
        this.group_topic_id = group_topic_id;
    }
}
