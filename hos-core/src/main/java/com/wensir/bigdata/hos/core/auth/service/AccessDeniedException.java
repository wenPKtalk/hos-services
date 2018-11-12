package com.wensir.bigdata.hos.core.auth.service;

import com.wensir.bigdata.hos.core.ErrorCodes;
import com.wensir.bigdata.hos.core.HosBaseException;

public class AccessDeniedException extends HosBaseException {

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(String resPath, long userId, String accessType) {
        super(String.format("access denied:%d->%s,%s", userId, resPath, accessType), null);
    }

    public AccessDeniedException(String resPath, long userId) {
        super(String.format("access denied:%d->%s not owner", userId, resPath), null);
    }

    @Override
    public int errorCode() {
        return ErrorCodes.ERROR_PERMISSION_DENIED;
    }

}
