package com.team1060.golf.product.exception;

import lombok.Getter;

public enum ExceptionCode {
    PAY_CANCEL(400, "결제 취소"),
    PAY_FAILED(400, "결제 실패");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
