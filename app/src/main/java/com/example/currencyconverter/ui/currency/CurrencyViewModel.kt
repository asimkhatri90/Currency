package com.example.currencyconverter.ui.currency

import android.text.format.DateUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.currency.CurrencyRepository
import com.example.currencyconverter.data.currency.converter.CurrencyConverter
import com.example.currencyconverter.data.currency.model.Currency
import com.example.currencyconverter.data.currency.model.CurrencyWithAmount
import com.example.currencyconverter.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    private val converter: CurrencyConverter,
) : ViewModel() {

    private val _USD = Currency(
        "USD",
        1.0,
        System.currentTimeMillis(),
        "USD"
    )

    private val _refresh = MutableStateFlow(0)

    private val _amount = MutableStateFlow("1.0")

    val amount: StateFlow<String> = _amount.asStateFlow()

    private val _selectedCurrency = MutableStateFlow(_USD)

    val selectedCurrency: StateFlow<Currency> = _selectedCurrency.asStateFlow()

    private val _currencyList: StateFlow<List<Currency>> =
        repository.observeCurrencyList().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    val currencyList get() = _currencyList

    val fetchTime: StateFlow<String> = _currencyList.mapLatest {
        if (it.isNotEmpty()) {
            DateUtils.getRelativeTimeSpanString(
                it.first().timestamp,
                Calendar.getInstance().timeInMillis,
                DateUtils.MINUTE_IN_MILLIS
            ).toString()
        } else ""
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = ""
    )

    private val _convertedCurrencyList: StateFlow<Result<List<CurrencyWithAmount>>> =
        _refresh.flatMapLatest {
            repository.convertCurrencyList(
                _currencyList.value,
                _amount.value.toDouble(),
                _selectedCurrency.value
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = Result.Loading
        )

    val convertedCurrencyList get() = _convertedCurrencyList

    fun changeSelectedCurrency(newCurrency: Currency) {
        _selectedCurrency.value = newCurrency
        _refresh.value += 1
    }

    fun changeAmount(amount: String) {
        if (amount.isNotEmpty())
            this._amount.value = amount
        else
            this._amount.value = "1.0"

        _refresh.value += 1
    }

    suspend fun refreshCurrencyList() {
        viewModelScope.launch {
            repository.getLatestCurrencyList()
            _refresh.value += 1
        }
    }
}