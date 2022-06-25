package com.cqu.store.service.ex;

public class OrderNotFound extends ServiceException{
    public OrderNotFound() {
        super();
    }

    public OrderNotFound(String message) {
        super(message);
    }

    public OrderNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFound(Throwable cause) {
        super(cause);
    }

    protected OrderNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
