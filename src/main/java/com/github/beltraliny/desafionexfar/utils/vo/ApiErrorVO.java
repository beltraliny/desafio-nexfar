package com.github.beltraliny.desafionexfar.utils.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorVO {

    private HttpStatus httpStatus;
    private String message;
}
