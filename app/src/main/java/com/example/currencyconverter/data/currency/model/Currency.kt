package com.example.currencyconverter.data.currency.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "rate") val rate: Double,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "base") val base: String
)

data class CurrencyWithAmount(
    val currency: Currency,
    val amount: Double
)