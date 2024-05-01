package com.example.currencyconverter.ui.currency

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.currencyconverter.data.currency.model.Currency
import com.example.currencyconverter.data.currency.model.CurrencyInfo
import com.example.currencyconverter.data.currency.model.CurrencyWithAmount
import com.example.currencyconverter.utils.CurrencyFormatter

@Composable
fun CurrencyScreen(
    viewModel: CurrencyViewModel
) {
    val currencyDropDownList: List<Currency> by viewModel.currencyList.collectAsState(initial = emptyList())
    val currencyList: List<CurrencyWithAmount> by viewModel.displayCurrencyList.collectAsState(
        initial = emptyList()
    )
    val amount: String by viewModel.amount.collectAsState()
    val query: String by viewModel.query.collectAsState()
    val selectedCurrency: Currency by viewModel.selectedCurrency.collectAsState()
    val fetchTime: String by viewModel.fetchTime.collectAsState(initial = "")
    val isLoading: Boolean by viewModel.isLoading
    val toastMessage: String? by viewModel.toastMessage
    val currencyInfoMap: Map<String, CurrencyInfo> by viewModel.currencyInfoMap

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
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            OutlinedTextField(
                label = {Text(text = "Amount")},
                maxLines = 1,
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f),
                value = amount,
                onValueChange = { newValue: String ->
                    viewModel.changeAmount(newValue)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(10.dp))
            CurrencyDropDown(
                modifier = Modifier.weight(1f),
                currencyList = currencyDropDownList,
                selectedCurrency = selectedCurrency,
                currencyInfoList = currencyInfoMap,
                onCurrencySelected = {
                    viewModel.changeSelectedCurrency(it)
                })

        }
        SearchBar(
            query = query,
            onValueChanged = viewModel::searchCurrency,
        )

        ConvertedCurrenciesGrid(
            currencyList,
            selectedCurrency,
            currencyInfoMap
        )
    }

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(modifier = Modifier.size(64.dp))
        }
    }

}

@Composable
fun CurrencyDropDown(
    modifier: Modifier,
    currencyList: List<Currency>,
    selectedCurrency: Currency,
    currencyInfoList: Map<String, CurrencyInfo>,
    onCurrencySelected: (Currency) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selCurrency by remember { mutableStateOf(selectedCurrency) }
    Column(
        modifier = modifier.wrapContentSize(Alignment.TopStart)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(20))
                .background(color = Color.White, RoundedCornerShape(20))
                .clickable(onClick = { expanded = true }),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            val currencyInfo = currencyInfoList[selectedCurrency.code] ?: CurrencyInfo.DEFAULT
            AsyncImage(
                model = currencyInfo.countryFlagUrl,
                contentDescription = "CountryFlag"
            )
            Text(selectedCurrency.code)
            Icon(
                Icons.Default.ArrowDropDown, contentDescription = "Test"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .height(500.dp)
                .background(Color.LightGray)
        ) {
            LazyColumn(modifier = Modifier.size(width = 300.dp, height = 500.dp)) {
                items(currencyList) { s ->
                    val currencyInfo = currencyInfoList[s.code] ?: CurrencyInfo.DEFAULT
                    DropdownMenuItem(onClick = {
                        selCurrency = s
                        expanded = false
                        onCurrencySelected(s)
                    }) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = currencyInfo.countryFlagUrl,
                                contentDescription = "CountryFlag"
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = "${s.code} - ${currencyInfo.countryName}",
                                fontWeight = if (s.code == selectedCurrency.code)
                                    FontWeight.Bold else FontWeight.Normal,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConvertedCurrenciesGrid(
    currencyList: List<CurrencyWithAmount>,
    selectedCurrency: Currency,
    currencyInfoMap: Map<String, CurrencyInfo>
) {

    if (currencyList.isEmpty()) return

    LazyVerticalGrid(columns = GridCells.Adaptive(110.dp),
        // content padding
        content = {
            items(currencyList.size) { index ->
                val currency = currencyList[index].currency
                val code  = currency.code
                val amount = currencyList[index].amount
                val selected = code == selectedCurrency.code
                val currencyInfo = currencyInfoMap[code] ?: CurrencyInfo.DEFAULT
                CurrencyGridItem(code, CurrencyFormatter.format(amount), currencyInfo.countryFlagUrl, selected)
            }
        })
}

@Composable
private fun CurrencyGridItem(
    code: String,
    amount: String,
    flagUrl: String,
    selected: Boolean
) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        border = BorderStroke(1.dp, if (selected) Color.Black else Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = amount,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp),
                maxLines = 1
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // https://flagsapi.com/BE/flat/64.png
                Text(
                    text = code,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
                AsyncImage(
                    model = flagUrl,
                    contentDescription = "CountryFlag"
                )
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    showClearIcon: Boolean = false,
    placeHolder: String = "Search",
    onValueChanged: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onValueChanged,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = "Clear icon"
                    )
                }
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = { Text(placeHolder) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
    )
}