package com.zup.mercado.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatarNumero {

    public static BigDecimal formatarDouble(double d) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(2, RoundingMode.HALF_DOWN);
    }
}
