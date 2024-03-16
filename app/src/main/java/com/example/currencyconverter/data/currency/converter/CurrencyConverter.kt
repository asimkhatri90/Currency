package com.example.currencyconverter.data.currency.converter

import com.example.currencyconverter.data.currency.model.Currency

interface CurrencyConverter {

    fun convert(amount: Double, fromCurrency: Currency, toCurrency: Currency): Double

}