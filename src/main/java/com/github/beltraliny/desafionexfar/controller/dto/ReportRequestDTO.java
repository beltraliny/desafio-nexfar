package com.github.beltraliny.desafionexfar.controller.dto;

import java.util.List;

public record ReportRequestDTO(
        String key,
        String format,
        List<ReportRequestFilterDTO> filters
) {}