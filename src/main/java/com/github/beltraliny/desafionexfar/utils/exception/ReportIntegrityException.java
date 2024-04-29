package com.github.beltraliny.desafionexfar.utils.exception;

public class ReportIntegrityException extends RuntimeException {

    public ReportIntegrityException() {
        super("A unknown error occurred while building the report.");
    }
}
