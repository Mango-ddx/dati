package com.dati.Utils;

import com.dati.responseresult.ResponseResult;

public enum  ResultUtils implements ResponseResult {
    QUERY_WAS_SUCCESS(200, "查询成功", true),
    QUERY_WAS_ERROR(500, "查询失败", false),
    ROLE_NULL(503, "已没有可用角色。。", false),
    ROLE_NOT(504, "没有任何角色了。。", false),
    ROLE_HAVE(500, "请不要重复添加..",false),
    TOPIC_NULL(501, "当前分类没有可答的题目了。", false),
    USERNAME_NO(500, "用户不存在", false),
    TOPIC_HAVE(500, "分类不能删除，分类下面有题目。", false),
    PASSWORD_ERROR(500, "密码错误", false),
    SUCCESS(200, "操作成功", true),
    ERROR(500, "操作失败", false),
    ADD_TOPIC_ERROR(500, "添加题目失败", false),
    ADD_TOPIC_REPEAT(500, "当前分类中已经有相同的题目了。", false),
    TIMEOUT_ERROR(500, "网络开小差了，请稍后再试。", false),
    UPDATE_ERROR(500, "改变失败", false),
    UPDATE_HAVE(500, "已经有这个分类了", false),
    QUERY_CLASS_NULL(500, "找不到班级", false),
    ADD_USER_SUCCESS(200, "注册成功", true),
    ADD_GROUP_SUCCESS(200, "添加成功", true),
    ADD_USER_ERROR(500, "注册失败，发生未知错误", false),
    ADD_GROUP_ERROR(500, "添加分类失败，发生未知错误", false),
    USERNAME_NOLL(500, "用户名为空", false),
    PASSWORD_NOLL(500, "密码不能为空", false),
    GROUP_ERROR(500, "分类名称不能重复", false),
    ACCOUNT_NOLL(500, "账号不能为空", false),
    CLASS_NOLL(500, "你还没有选择班级", false),
    GROUP_NOLL(500, "分类名称为空", false),
    ACCOUNT_USERNAME(500, "账号或者名称重复", false);
    private int code;
    private String message;
    private boolean success;

    ResultUtils(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public boolean success() {
        return success;
    }
}
