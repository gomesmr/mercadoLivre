package com.zup.mercado.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatarData {
    public static String dataFormatada(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
