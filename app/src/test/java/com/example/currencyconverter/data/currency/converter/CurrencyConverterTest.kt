package com.example.currencyconverter.data.currency.converter

import com.example.currencyconverter.data.currency.converter.CurrencyConverter
import com.example.currencyconverter.data.currency.converter.DefaultCurrencyConverter
import com.example.currencyconverter.data.currency.model.Currency
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CurrencyConverterTest {

    private lateinit var currencyConverter: CurrencyConverter

    @Before
    fun setup() {
        currencyConverter = DefaultCurrencyConverter()
    }

    @Test
    fun convertINRToPKR() {

        val pkr = Currency("PKR", 282.393459, 12, "USD")
        val inr = Currency("INR", 82.791193, 12, "USD")
        val amount = 1.0

        val convertedAmount = currencyConverter.convert(amount, inr, pkr)

        assertEquals(convertedAmount.toInt(), (amount * 3.41).toInt())
    }

    @Test
    fun convertPKRToINR() {

        val pkr = Currency("PKR", 282.393459, 12, "USD")
        val inr = Currency("INR", 82.791193, 12, "USD")
        val amount = 1.0

        val convertedAmount = currencyConverter.convert(amount, pkr, inr)

        assertEquals(convertedAmount.toInt(), (amount * 0.29).toInt())
    }

}