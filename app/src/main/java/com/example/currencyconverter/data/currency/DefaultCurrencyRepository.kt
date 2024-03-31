package com.example.currencyconverter.data.currency

import android.util.Log
import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.data.currency.model.Currency
import com.example.currencyconverter.utils.USD_CURRENCY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

interface CurrencyRepository {
    fun getCurrencyList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (Exception?) -> Unit,
    ): Flow<List<Currency>>
}

class DefaultCurrencyRepository @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val localDataSource: CurrencyDao,
    private val dispatcher: CoroutineDispatcher
) : CurrencyRepository {
    override fun getCurrencyList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (Exception?) -> Unit,
    ): Flow<List<Currency>> =
        flow {
            try {
                val currencyResponse =
                    currencyApi.getLatestCurrencyRates(BuildConfig.app_id, USD_CURRENCY)
                val latestCurrencyList =
                    currencyResponse.rates.map { entry ->
                        Currency(
                            entry.key,
                            entry.value,
                            currencyResponse.timestamp * 1000, // property indicates the time (UNIX) that the rates were published.
                            currencyResponse.base,
                        )
                    }

                localDataSource.upsertAll(latestCurrencyList)
                emit(localDataSource.getAll())
            } catch (e: Exception) {
                Log.e("Repository", "Unable to get latest", e)
                var exp = e
                try {
                    val currencyList = localDataSource.getAll()
                    emit(currencyList)
                } catch (db: Exception) {
                    exp = db
                } finally {
                    onError(exp)
                }
            }
        }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(dispatcher)
}
