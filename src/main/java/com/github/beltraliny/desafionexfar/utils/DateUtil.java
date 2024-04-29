package com.github.beltraliny.desafionexfar.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {}

    public static Instant buildInstantFromString(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(text, formatter);

        return localDateTime.atZone(ZoneId.of("UTC")).toInstant();
    }

    public static String buildStringFromInstant(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        OffsetDateTime createdAtOffsetDateTime = instant.atOffset(ZoneOffset.UTC);

        return formatter.format(createdAtOffsetDateTime);
    }
}
