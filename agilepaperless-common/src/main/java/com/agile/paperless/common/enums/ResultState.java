package com.agile.paperless.common.enums;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2017/11/5 22:04
 * @Modified By:
 */
public enum ResultState {

    RESULT_ERROR(1, "系统未知错误"),
    RESULT_FALLBACKERROR(1001, "断路器启动调用 FALLBACK"),
    RESULT_LOGIN_ERROR(2000, "用户名或密码错误!"),
    RESULT_SUCCESS(20000, "成功"),
    RESULT_UNLOGIN(22222, "user is not login!");


    private Integer code;

    private String message;

    ResultState(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }
}
