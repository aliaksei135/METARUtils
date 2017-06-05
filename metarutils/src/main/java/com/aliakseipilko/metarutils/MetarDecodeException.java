package com.aliakseipilko.metarutils;


public class MetarDecodeException extends Exception {

    public MetarDecodeException(String message) {
        super(message);
    }

    public MetarDecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetarDecodeException(Throwable cause) {
        super(cause);
    }
}
