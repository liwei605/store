package com.cqu.store.service.ex;

public class FavoriteNotFoundException extends ServiceException{
    public FavoriteNotFoundException() {
    }

    public FavoriteNotFoundException(String message) {
        super(message);
    }

    public FavoriteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FavoriteNotFoundException(Throwable cause) {
        super(cause);
    }

    public FavoriteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
