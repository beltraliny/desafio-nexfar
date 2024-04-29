package com.github.beltraliny.desafionexfar.utils.enums;

public enum ReportFormat {

    CSV,
    XLS;

    public static ReportFormat convert(String reportFormat) {
        try {
            return ReportFormat.valueOf(reportFormat);
        } catch (Exception exception) {
            return null;
        }
    }

    public boolean isCsv() {
        return this == ReportFormat.CSV;
    }

    public boolean isXls() {
        return this == ReportFormat.XLS;
    }
}
