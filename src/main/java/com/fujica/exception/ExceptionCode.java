package com.fujica.exception;

import lombok.Getter;

/**
 * 成功：0
 * 失败：1
 * 业务相关：固定开头10+2位业务标识+3位错误码，示例：10+01(账号业务)+001(用户名或密码错误)
 */
@Getter
public enum ExceptionCode {
    OK(0, "success"),
    SYS_ERROR(1400001, "system error"),
    COMMON_PARAM_INVALID(1400002, "param invalid"),
    PARSE_DATA_ERROR(1400003, "解析数据错误"),
    ;

    private final int code;
    private final String msg;

    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
