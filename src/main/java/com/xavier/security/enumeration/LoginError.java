package com.xavier.security.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Xavier.wu
 * @CreateTime: 2023-04-25  16:17
 * @Description: TODO
 * @Version: 1.0
 */
public enum LoginError {

    FAILURE(0, "登录失败！"),

    BAD_CREDENTIALS(1, "用户名密码错误！"),

    LOCKED(2, "用户已被锁定，无法登陆！"),

    ACCOUNT_EXPIRED(3, "用户失效，无法登录！"),

    USERNAME_NOT_FOUND(4, "用户名不存在！");


    private Integer code;
    private String message;

    LoginError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static LoginError analysis(String code) {
        Map<Integer, LoginError> errorMap = Arrays.stream(LoginError.values()).collect(Collectors.toMap(LoginError::getCode, Function.identity(), (o1, o2) -> o1));
        return errorMap.getOrDefault(Integer.valueOf(code), LoginError.FAILURE);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
