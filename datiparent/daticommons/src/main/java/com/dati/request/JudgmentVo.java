package com.dati.request;
/*
*
* 判断题body类
* **/
public class JudgmentVo {
    private String error;
    private String correct;
    private String answer;
    private String group_topic_id;
    private String title;
    private Integer title_type;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public Integer getTitle_type() {
        return title_type;
    }

    public void setTitle_type(Integer title_type) {
        this.title_type = title_type;
    }
}
