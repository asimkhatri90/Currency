package com.example.currencyconverter.di

import com.example.currencyconverter.data.currency.CurrencyApi
import com.example.currencyconverter.data.currency.CurrencyDao
import com.example.currencyconverter.data.currency.CurrencyRepository
import com.example.currencyconverter.data.currency.DefaultCurrencyRepository
import com.example.currencyconverter.data.currency.converter.CurrencyConverter
import com.example.currencyconverter.data.currency.converter.DefaultCurrencyConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrencyConverter(): CurrencyConverter {
        return DefaultCurrencyConverter()
    }

    @Provides
    @Singleton
    fun provideCurrencyConverterRepository(
        currencyRemoteDataSource: CurrencyApi,
        currencyLocalDataSource: CurrencyDao,
        dispatcher: CoroutineDispatcher
    ): CurrencyRepository {
        return DefaultCurrencyRepository(
            currencyRemoteDataSource,
            currencyLocalDataSource,
            dispatcher
        )
    }
}