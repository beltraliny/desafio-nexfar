package com.github.beltraliny.desafionexfar.utils.exception;

import com.github.beltraliny.desafionexfar.utils.vo.ApiErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiErrorVO> defaultHandler(Exception exception) {
        ApiErrorVO apiErrorVO = new ApiErrorVO(HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error occurred.");
        return ResponseEntity.status(apiErrorVO.getHttpStatus()).body(apiErrorVO);
    }

    @ExceptionHandler(ReportIntegrityException.class)
    private ResponseEntity<ApiErrorVO> reportIntegrityExceptionHandler(ReportIntegrityException reportIntegrityException) {
        ApiErrorVO apiErrorVO = new ApiErrorVO(HttpStatus.INTERNAL_SERVER_ERROR, reportIntegrityException.getMessage());
        return ResponseEntity.status(apiErrorVO.getHttpStatus()).body(apiErrorVO);
    }
}
