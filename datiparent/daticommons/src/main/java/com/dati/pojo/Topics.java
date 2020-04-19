package com.dati.pojo;

public class Topics {
    private String id;
    private String title;
    private String option_ans;
    private String answer;
    private Integer title_type;
    private String group_topic_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOption_ans() {
        return option_ans;
    }

    public void setOption_ans(String option_ans) {
        this.option_ans = option_ans;
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
