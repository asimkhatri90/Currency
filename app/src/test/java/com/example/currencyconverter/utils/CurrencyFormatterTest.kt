package com.example.currencyconverter.utils

import com.example.currencyconverter.data.currency.model.Currency
import org.junit.Assert
import org.junit.Test

class CurrencyFormatterTest {

    @Test
    fun checkAmountIsThousand() {

        val amount = 2918.78123123123

        val converted = CurrencyFormatter.format(amount)

        Assert.assertEquals(converted, "2.92 K" )
    }

    @Test
    fun checkAmountIsMillion() {

        val amount = 29182121.78123123123

        val converted = CurrencyFormatter.format(amount)

        Assert.assertEquals(converted, "29.18 M" )
    }

}