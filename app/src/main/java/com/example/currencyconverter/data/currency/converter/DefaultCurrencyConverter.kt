package com.example.currencyconverter.data.currency.converter

import com.example.currencyconverter.data.currency.model.Currency

class DefaultCurrencyConverter : CurrencyConverter {

    override fun convert(amount: Double, fromCurrency: Currency, toCurrency: Currency): Double {
        val conversionFactor = toCurrency.rate / fromCurrency.rate
        return String.format("%.4f", (amount * conversionFactor)).toDouble()
    }
}