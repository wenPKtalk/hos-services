package com.wensir.bigdata.hos.core;


/**
 * 异常类基类，后续所有模块的异常类继承该类即可
 */
public abstract class HosBaseException extends RuntimeException {
    protected String errorMessage;

    public HosBaseException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public abstract int errorCode();

    public String errorMessage(){
        return this.errorMessage;
    }
}
