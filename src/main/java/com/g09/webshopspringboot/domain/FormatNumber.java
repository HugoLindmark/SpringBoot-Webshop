package com.g09.webshopspringboot.domain;

import java.text.NumberFormat;
import java.util.Locale;

public final class FormatNumber {

    public static final String formatCurrency(int number){
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));
        String formatted = nf.format(number);
        return formatted.substring(0, formatted.lastIndexOf(",")) + " SEK";
    }

    public static final String formatQuantity(int number){
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));
        String formatted = nf.format(number);
        return formatted.substring(0, formatted.lastIndexOf(","));
    }
}
