package com.example.currencyconverter.utils

import java.util.Locale

object CurrencyFormatter {

    fun format(number: Double): String {
        val thousand = 1_000L
        val million = 1_000_000L
        val billion = 1_000_000_000L
        val trillion = 1_000_000_000_000L

        val amount = when {
            number >= trillion -> "%.2f T".format(Locale.US, number / trillion)
            number >= billion -> "%.2f B".format(Locale.US, number / billion)
            number >= million -> "%.2f M".format(Locale.US, number / million)
            number >= thousand -> "%.2f K".format(Locale.US, number / thousand)
            else -> number.toString()
        }
//        "%.2f B".format(Locale.US, number / billion)
        return amount
    }
}