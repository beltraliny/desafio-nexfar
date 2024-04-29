package com.github.beltraliny.desafionexfar.controller.dto;

public record ReportRequestFilterDTO(
        String key,
        String operation,
        String value1,
        String value2
) {}
