package com.dam.backend.shared.utils;

import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class FormateDateUtil {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public FormateDateUtil() {
        throw new ClasseNaoInstanciavelException();
    }

    public static String formatarDataZonedDateTime(ZonedDateTime date) {
        if (date == null) return null;
        return date.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"))
                .format(DATE_FORMATTER);
    }

    public static String formatarDataEHoraZonedDateTime(ZonedDateTime date) {
        if (date == null) return null;
        return date.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"))
                .format(DATE_TIME_FORMATTER);
    }
}
