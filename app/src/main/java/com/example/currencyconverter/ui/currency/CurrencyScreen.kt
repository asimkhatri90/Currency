package com.example.currencyconverter.ui.currency

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.data.currency.model.Currency
import com.example.currencyconverter.data.currency.model.CurrencyWithAmount
import com.example.currencyconverter.utils.Result

@Composable
fun CurrencyScreen(
    viewModel: CurrencyViewModel
) {
    val currencyDropDownList by viewModel.currencyList.collectAsState()
    val convertedList by viewModel.convertedCurrencyList.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val selectedCurrency by viewModel.selectedCurrency.collectAsState()
    val fetchTime by viewModel.fetchTime.collectAsState()

    val result = convertedList

    val currencyList = if (result is Result.Success) result.data else listOf()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Text(
            "Currency rates updated $fetchTime",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = amount, onValueChange = { newValue ->
                    viewModel.changeAmount(newValue)
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(10.dp))
            CurrencyDropDown(currencyList = currencyDropDownList,
                selectedCurrency = selectedCurrency,
                onCurrencySelected = {
                    viewModel.changeSelectedCurrency(it)
                })

        }
        Spacer(modifier = Modifier.height(12.dp))
        ConvertedCurrenciesGrid(currencyList, selectedCurrency)
    }

}

@Composable
fun CurrencyDropDown(
    currencyList: List<Currency>, selectedCurrency: Currency, onCurrencySelected: (Currency) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selCurrency by remember { mutableStateOf(selectedCurrency) }
    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopStart)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(20))
                .background(color = Color.White, RoundedCornerShape(20))
                .clickable(onClick = { expanded = true }),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(selectedCurrency.code)
            Icon(
                Icons.Default.ArrowDropDown, contentDescription = "Test"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(
                Color.LightGray
            )
        ) {
            currencyList.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selCurrency = s
                    expanded = false
                    onCurrencySelected(s)
                }) {
                    Text(
                        text = s.code,
                        fontWeight = if (s.code == selectedCurrency.code)
                            FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun ConvertedCurrenciesGrid(currencyList: List<CurrencyWithAmount>, selectedCurrency: Currency) {

    if (currencyList.isEmpty()) return

    LazyVerticalGrid(columns = GridCells.Adaptive(98.dp),
        // content padding
        contentPadding = PaddingValues(
            start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp
        ), content = {
            items(currencyList.size) { index ->
                val currency = currencyList[index]
                val selected = currency.currency.code == selectedCurrency.code
                CurrencyGridItem(currency, selected)
            }
        })
}

@Composable
private fun CurrencyGridItem(
    currency: CurrencyWithAmount,
    selected: Boolean
) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 5.dp)
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        elevation = 4.dp,
        border = BorderStroke(1.dp, if (selected) Color.Black else Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currency.amount.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = currency.currency.code,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            )
        }
    }
}


