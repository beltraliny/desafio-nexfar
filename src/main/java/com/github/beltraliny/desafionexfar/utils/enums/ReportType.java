package com.github.beltraliny.desafionexfar.utils.enums;

public enum ReportType {

    ORDER_DETAILED,
    ORDER_SIMPLE;

    public static ReportType convert(String reportType) {
        try {
            return ReportType.valueOf(reportType);
        } catch (Exception exception) {
            return null;
        }
    }

    public boolean isDetailed() {
        return this == ReportType.ORDER_DETAILED;
    }

    public boolean isSimple() {
        return this == ReportType.ORDER_SIMPLE;
    }
}
