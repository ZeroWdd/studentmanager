package com.wdd.studentmanager.util;

/**
 * @Classname AjaxResult
 * @Description ajax json 返回数据
 * @Date 2019/6/25 10:21
 * @Created by WDD
 */
public class AjaxResult {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
