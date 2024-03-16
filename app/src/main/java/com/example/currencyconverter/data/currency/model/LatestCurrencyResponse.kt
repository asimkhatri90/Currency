package com.example.currencyconverter.data.currency.model

data class LatestCurrencyResponse(
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>
)