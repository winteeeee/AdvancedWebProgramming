package com.example.demo.exception;

public class NotEnoughStockException extends RuntimeException {
    private static String message = "재고가 부족합니다.";

    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(int stock, int orderNum) {
        super(message + "재고: " + stock + " 주문: " + orderNum);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
