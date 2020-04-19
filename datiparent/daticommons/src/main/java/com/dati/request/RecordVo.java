package com.dati.request;

public class RecordVo {
   private String user_id;
   private String title;
   private String answer;
   private String user_answer;
   private String correct;
   private Integer title_type;
   private Integer topics_id;
   private String group_topic_id;
   private String group_name;

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public Integer getTopics_id() {
        return topics_id;
    }

    public void setTopics_id(Integer topics_id) {
        this.topics_id = topics_id;
    }

    public String getGroup_topic_id() {
        return group_topic_id;
    }

    public void setGroup_topic_id(String group_topic_id) {
        this.group_topic_id = group_topic_id;
    }
}
