package com.example.currencyconverter.data.currency

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.currencyconverter.data.currency.model.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getAll(): List<Currency>

    @Upsert
    suspend fun upsertAll(currencies: List<Currency>)

    @Delete
    suspend fun delete(currency: Currency)
}