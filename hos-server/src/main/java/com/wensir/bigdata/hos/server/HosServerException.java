package com.wensir.bigdata.hos.server;

import com.wensir.bigdata.hos.core.HosBaseException;

/**
 * <br>
 * 〈HosService异常类〉
 *
 * @author wensir
 * @create 2019/4/8
 * @since 1.0.0
 */
public class HosServerException extends HosBaseException {
    private int code;
    private String message;

    public HosServerException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public HosServerException(int code, String message) {
        super(message, null);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int errorCode() {
        return this.code;
    }


}