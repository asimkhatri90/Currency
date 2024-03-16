package com.example.currencyconverter.data.currency

import android.util.Log
import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.data.currency.converter.CurrencyConverter
import com.example.currencyconverter.data.currency.model.Currency
import com.example.currencyconverter.data.currency.model.CurrencyWithAmount
import com.example.currencyconverter.utils.Result
import com.example.currencyconverter.utils.USD_CURRENCY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CurrencyRepository {

    fun observeCurrencyList(): Flow<List<Currency>>

    suspend fun getLatestCurrencyList()

    fun convertCurrencyList(
        currencyList: List<Currency>,
        amount: Double,
        currency: Currency
    ): Flow<Result<List<CurrencyWithAmount>>>
}

class DefaultCurrencyRepository @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val localDataSource: CurrencyDao,
    private val converter: CurrencyConverter,
    private val coroutineDispatcher: CoroutineDispatcher
) : CurrencyRepository {

    override fun observeCurrencyList(): Flow<List<Currency>> = localDataSource.getAll()

    override suspend fun getLatestCurrencyList(): Unit = withContext(coroutineDispatcher) {
        try {
            val currencyResponse = currencyApi.getLatestCurrencyRates(BuildConfig.app_id, USD_CURRENCY)
            val latestCurrencyList = currencyResponse.rates.map { entry ->
                Currency(
                    entry.key,
                    entry.value,
                    currencyResponse.timestamp * 1000, // property indicates the time (UNIX) that the rates were published.
                    currencyResponse.base
                )
            }

            localDataSource.upsertAll(latestCurrencyList)
        } catch (e: Exception) {
            Log.e("Repository", "Unable to get latest", e)
        }
    }

    override fun convertCurrencyList(
        currencyList: List<Currency>,
        amount: Double,
        currency: Currency
    ): Flow<Result<List<CurrencyWithAmount>>> = flow {
        emit(Result.Loading)
        val convertedList = currencyList.map {
            CurrencyWithAmount(
                it,
                converter.convert(amount, currency, it)
            )
        }
        emit(Result.Success(convertedList))

    }.catch { e -> emit(Result.Error(Exception(e))) }.flowOn(coroutineDispatcher)
}
