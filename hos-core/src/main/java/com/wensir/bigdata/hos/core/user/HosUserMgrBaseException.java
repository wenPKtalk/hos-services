package com.wensir.bigdata.hos.core.user;

import com.wensir.bigdata.hos.core.HosBaseException;

public class HosUserMgrBaseException extends HosBaseException {

    private int code;
    private String message;
    public HosUserMgrBaseException(int code ,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public HosUserMgrBaseException(int code, String message) {
        super(message, null);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int errorCode() {
        return this.code;
    }
}
