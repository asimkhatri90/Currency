package com.example.currencyconverter.utils

import java.util.Locale

object CurrencyFormatter {

    /**
     * Convert the amount into string representation of thousands, millions, billions and trillions
     */
    fun format(amount: Double): String {
        val thousand = 1_000L
        val million = 1_000_000L
        val billion = 1_000_000_000L
        val trillion = 1_000_000_000_000L

        return when {
            amount >= trillion -> "%.2f T".format(Locale.US, amount / trillion)
            amount >= billion -> "%.2f B".format(Locale.US, amount / billion)
            amount >= million -> "%.2f M".format(Locale.US, amount / million)
            amount >= thousand -> "%.2f K".format(Locale.US, amount / thousand)
            else -> "%.3f".format(Locale.US, amount)
        }
    }
}