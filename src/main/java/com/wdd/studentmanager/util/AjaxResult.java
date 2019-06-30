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
    private String imgurl;
    private String type;

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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
