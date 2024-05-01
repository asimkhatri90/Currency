package com.example.currencyconverter.ui.currency

import android.text.format.DateUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.currency.CurrencyRepository
import com.example.currencyconverter.data.currency.converter.CurrencyConverter
import com.example.currencyconverter.data.currency.model.Currency
import com.example.currencyconverter.data.currency.model.CurrencyInfo
import com.example.currencyconverter.data.currency.model.CurrencyWithAmount
import com.example.currencyconverter.utils.CurrencyFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    repository: CurrencyRepository,
    private val converter: CurrencyConverter,
) : ViewModel() {

    private val _USD = Currency(
        "USD",
        1.0,
        System.currentTimeMillis(),
        "USD"
    )

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _toastMessage: MutableState<String?> = mutableStateOf(null)
    val toastMessage: State<String?> get() = _toastMessage

    private val _refresh = MutableStateFlow(0)

    private val _amount = MutableStateFlow("1.0")

    val amount get() = _amount.asStateFlow()

    private val _selectedCurrency = MutableStateFlow(_USD)

    val selectedCurrency get() = _selectedCurrency.asStateFlow()

    private var _currencyInfoMap : MutableState<Map<String, CurrencyInfo>> = mutableStateOf(CurrencyInfo.allAsMap())
    val currencyInfoMap: State<Map<String, CurrencyInfo>> get() = _currencyInfoMap

    private var currencyInfoList = CurrencyInfo.allAsList()

    private val _currencyList: Flow<List<Currency>> =
        repository.getCurrencyList(onStart = {
            _isLoading.value = true
        }, onComplete = {
            _isLoading.value = false
        }, onError = {
            _toastMessage.value = it?.message
        })

    val currencyList get() = _currencyList

    val fetchTime: Flow<String> = _currencyList.map {
        if (it.isNotEmpty()) {
            DateUtils.getRelativeTimeSpanString(
                it.first().timestamp,
                Calendar.getInstance().timeInMillis,
                DateUtils.MINUTE_IN_MILLIS
            ).toString()
        } else ""
    }

    private val convertedCurrencyList: Flow<List<CurrencyWithAmount>> =
        _refresh.flatMapLatest {
            _currencyList.map {
                it.map { curr ->
                    CurrencyWithAmount(
                        curr,
                        converter.convert(_amount.value.toDouble(), _selectedCurrency.value, curr)
                    )
                }
            }
        }

    private var _query : MutableStateFlow<String> = MutableStateFlow("")
    val query get() = _query.asStateFlow()

    private val filteredConvertedCurrencyList: Flow<List<CurrencyWithAmount>> = _query.flatMapLatest { search ->
        convertedCurrencyList.mapLatest {
            CurrencyFilter.filter(search, it, currencyInfoList)
        }
    }

    val displayCurrencyList get() =
        if (_query.value.isNotEmpty()) filteredConvertedCurrencyList
        else convertedCurrencyList

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

    fun searchCurrency(query: String) {
        _query.value = query
    }
}