package com.example.currencyconverter.utils

import com.example.currencyconverter.data.currency.model.CurrencyInfo
import com.example.currencyconverter.data.currency.model.CurrencyWithAmount

object CurrencyFilter {

    fun filter(
        query: String,
        currencies: List<CurrencyWithAmount>,
        currencyInfoList: List<CurrencyInfo>
    ): List<CurrencyWithAmount> {

        return currencyInfoList
            .filter {currInfo ->
                currInfo.code.contains(query, true) ||
                        currInfo.countryName.contains(query, true)
            }
            .flatMap { filteredCurrInfo ->
                currencies.filter {
                    it.currency.code == filteredCurrInfo.code
                }
            }
    }
}