package com.example.currencyconverter.data.currency.model

class CurrencyInfo(
    val countryName: String,
    val code: String,
    val countryFlagUrl: String
) {
    companion object {

        val DEFAULT = CurrencyInfo("Default", "DEF", "https://flagsapi.com/DEF/flat/64.png")

        fun allAsMap(): Map<String, CurrencyInfo> {
            return mapOf(
                "AED" to CurrencyInfo(
                    "United Arab Emirates",
                    "AED",
                    "https://flagsapi.com/AE/flat/64.png"
                ),
                "AFN" to CurrencyInfo("Afghanistan", "AFN", "https://flagsapi.com/AF/flat/64.png"),
                "ALL" to CurrencyInfo("Albania", "ALL", "https://flagsapi.com/AL/flat/64.png"),
                "AMD" to CurrencyInfo("Armenia", "AMD", "https://flagsapi.com/AM/flat/64.png"),
                "ANG" to CurrencyInfo("Sint Maarten", "ANG", "https://flagcdn.com/h40/sx.png"),
                "AOA" to CurrencyInfo("Angola", "AOA", "https://flagsapi.com/AO/flat/64.png"),
                "ARS" to CurrencyInfo("Argentina", "ARS", "https://flagsapi.com/AR/flat/64.png"),
                "AUD" to CurrencyInfo("Australia", "AUD", "https://flagsapi.com/AU/flat/64.png"),
                "AWG" to CurrencyInfo("Aruba", "AWG", "https://flagsapi.com/AW/flat/64.png"),
                "AZN" to CurrencyInfo("Azerbaijan", "AZN", "https://flagsapi.com/AZ/flat/64.png"),
                "BAM" to CurrencyInfo(
                    "Bosnia and Herzegovina",
                    "BAM",
                    "https://flagsapi.com/BA/flat/64.png"
                ),
                "BBD" to CurrencyInfo("Barbados", "BBD", "https://flagsapi.com/BB/flat/64.png"),
                "BDT" to CurrencyInfo("Bangladesh", "BDT", "https://flagsapi.com/BD/flat/64.png"),
                "BGN" to CurrencyInfo("Bulgaria", "BGN", "https://flagsapi.com/BG/flat/64.png"),
                "BHD" to CurrencyInfo("Bahrain", "BHD", "https://flagsapi.com/BH/flat/64.png"),
                "BIF" to CurrencyInfo("Burundi", "BIF", "https://flagsapi.com/BI/flat/64.png"),
                "BMD" to CurrencyInfo("Bermuda", "BMD", "https://flagsapi.com/BM/flat/64.png"),
                "BND" to CurrencyInfo("Brunei", "BND", "https://flagsapi.com/BN/flat/64.png"),
                "BOB" to CurrencyInfo("Bolivia", "BOB", "https://flagsapi.com/BO/flat/64.png"),
                "BRL" to CurrencyInfo("Brazil", "BRL", "https://flagsapi.com/BR/flat/64.png"),
                "BSD" to CurrencyInfo("Bahamas", "BSD", "https://flagsapi.com/BS/flat/64.png"),
                "BTC" to CurrencyInfo("Bitcoin", "BTC", "https://flagsapi.com/BTC/flat/64.png"),
                "BTN" to CurrencyInfo("Bhutan", "BTN", "https://flagsapi.com/BT/flat/64.png"),
                "BWP" to CurrencyInfo("Botswana", "BWP", "https://flagsapi.com/BW/flat/64.png"),
                "BYN" to CurrencyInfo("Belarus", "BYN", "https://flagsapi.com/BY/flat/64.png"),
                "BZD" to CurrencyInfo("Belize", "BZD", "https://flagsapi.com/BZ/flat/64.png"),
                "CAD" to CurrencyInfo("Canada", "CAD", "https://flagsapi.com/CA/flat/64.png"),
                "CDF" to CurrencyInfo(
                    "Congo, Democratic Republic of the",
                    "CDF",
                    "https://flagsapi.com/CD/flat/64.png"
                ),
                "CHF" to CurrencyInfo("Switzerland", "CHF", "https://flagsapi.com/CH/flat/64.png"),
                "CLP" to CurrencyInfo("Chile", "CLP", "https://flagsapi.com/CL/flat/64.png"),
                "CLF" to CurrencyInfo("Chilean Unit of Account (UF)", "CLF", "https://flagsapi.com/CL/flat/64.png"),
                "CNH" to CurrencyInfo("Chinese Yuan (Offshore)", "CNH", "https://flagsapi.com/CN/flat/64.png"),
                "CNY" to CurrencyInfo("China", "CNY", "https://flagsapi.com/CN/flat/64.png"),
                "COP" to CurrencyInfo("Colombia", "COP", "https://flagsapi.com/CO/flat/64.png"),
                "CRC" to CurrencyInfo("Costa Rica", "CRC", "https://flagsapi.com/CR/flat/64.png"),
                "CUC" to CurrencyInfo("Cuban convertible peso", "CUP", "https://flagsapi.com/CU/flat/64.png"),
                "CUP" to CurrencyInfo("Cuba", "CUP", "https://flagsapi.com/CU/flat/64.png"),
                "CVE" to CurrencyInfo("Cabo Verde", "CVE", "https://flagsapi.com/CV/flat/64.png"),
                "CZK" to CurrencyInfo("Czech Republic", "CZK", "https://flagsapi.com/CZ/flat/64.png"),
                "DJF" to CurrencyInfo("Djibouti", "DJF", "https://flagsapi.com/DJ/flat/64.png"),
                "DKK" to CurrencyInfo("Denmark", "DKK", "https://flagsapi.com/DK/flat/64.png"),
                "DOP" to CurrencyInfo(
                    "Dominican Republic",
                    "DOP",
                    "https://flagsapi.com/DO/flat/64.png"
                ),
                "DZD" to CurrencyInfo("Algeria", "DZD", "https://flagsapi.com/DZ/flat/64.png"),
                "EGP" to CurrencyInfo("Egypt", "EGP", "https://flagsapi.com/EG/flat/64.png"),
                "ERN" to CurrencyInfo("Eritrea", "ERN", "https://flagsapi.com/ER/flat/64.png"),
                "ETB" to CurrencyInfo("Ethiopia", "ETB", "https://flagsapi.com/ET/flat/64.png"),
                "EUR" to CurrencyInfo("Europe", "EUR", "https://flagcdn.com/h40/eu.png"),
                "FJD" to CurrencyInfo("Fiji", "FJD", "https://flagsapi.com/FJ/flat/64.png"),
                "FKP" to CurrencyInfo("Falkland Islands", "FKP", "https://flagsapi.com/FK/flat/64.png"),
                "GBP" to CurrencyInfo("United Kingdom", "GBP", "https://flagsapi.com/GB/flat/64.png"),
                "GEL" to CurrencyInfo("Georgia", "GEL", "https://flagsapi.com/GE/flat/64.png"),
                "GGP" to CurrencyInfo("Guernsey", "GGP", "https://flagsapi.com/GG/flat/64.png"),
                "GHS" to CurrencyInfo("Ghana", "GHS", "https://flagsapi.com/GH/flat/64.png"),
                "GIP" to CurrencyInfo("Gibraltar", "GIP", "https://flagsapi.com/GI/flat/64.png"),
                "GMD" to CurrencyInfo("Gambia", "GMD", "https://flagsapi.com/GM/flat/64.png"),
                "GNF" to CurrencyInfo("Guinea", "GNF", "https://flagsapi.com/GN/flat/64.png"),
                "GTQ" to CurrencyInfo("Guatemala", "GTQ", "https://flagsapi.com/GT/flat/64.png"),
                "GYD" to CurrencyInfo("Guyana", "GYD", "https://flagsapi.com/GY/flat/64.png"),
                "HKD" to CurrencyInfo("Hong Kong", "HKD", "https://flagsapi.com/HK/flat/64.png"),
                "HNL" to CurrencyInfo("Honduras", "HNL", "https://flagsapi.com/HN/flat/64.png"),
                "HRK" to CurrencyInfo("Croatia", "HRK", "https://flagsapi.com/HR/flat/64.png"),
                "HTG" to CurrencyInfo("Haiti", "HTG", "https://flagsapi.com/HT/flat/64.png"),
                "HUF" to CurrencyInfo("Hungary", "HUF", "https://flagsapi.com/HU/flat/64.png"),
                "IDR" to CurrencyInfo("Indonesia", "IDR", "https://flagsapi.com/ID/flat/64.png"),
                "ILS" to CurrencyInfo("Israel", "ILS", "https://flagsapi.com/IL/flat/64.png"),
                "IMP" to CurrencyInfo("Isle of Man", "IMP", "https://flagsapi.com/IM/flat/64.png"),
                "INR" to CurrencyInfo("India", "INR", "https://flagsapi.com/IN/flat/64.png"),
                "IQD" to CurrencyInfo("Iraq", "IQD", "https://flagsapi.com/IQ/flat/64.png"),
                "IRR" to CurrencyInfo("Iran", "IRR", "https://flagsapi.com/IR/flat/64.png"),
                "ISK" to CurrencyInfo("Iceland", "ISK", "https://flagsapi.com/IS/flat/64.png"),
                "JEP" to CurrencyInfo("Jersey", "JEP", "https://flagsapi.com/JE/flat/64.png"),
                "JMD" to CurrencyInfo("Jamaica", "JMD", "https://flagsapi.com/JM/flat/64.png"),
                "JOD" to CurrencyInfo("Jordan", "JOD", "https://flagsapi.com/JO/flat/64.png"),
                "JPY" to CurrencyInfo("Japan", "JPY", "https://flagsapi.com/JP/flat/64.png"),
                "KES" to CurrencyInfo("Kenya", "KES", "https://flagsapi.com/KE/flat/64.png"),
                "KGS" to CurrencyInfo("Kyrgyzstan", "KGS", "https://flagsapi.com/KG/flat/64.png"),
                "KHR" to CurrencyInfo("Cambodia", "KHR", "https://flagsapi.com/KH/flat/64.png"),
                "KMF" to CurrencyInfo("Comoros", "KMF", "https://flagsapi.com/KM/flat/64.png"),
                "KPW" to CurrencyInfo("Korea, North", "KPW", "https://flagsapi.com/KP/flat/64.png"),
                "KRW" to CurrencyInfo("Korea, South", "KRW", "https://flagsapi.com/KR/flat/64.png"),
                "KWD" to CurrencyInfo("Kuwait", "KWD", "https://flagsapi.com/KW/flat/64.png"),
                "KYD" to CurrencyInfo("Cayman Islands", "KYD", "https://flagsapi.com/KY/flat/64.png"),
                "KZT" to CurrencyInfo("Kazakhstan", "KZT", "https://flagsapi.com/KZ/flat/64.png"),
                "LAK" to CurrencyInfo("Laos", "LAK", "https://flagsapi.com/LA/flat/64.png"),
                "LBP" to CurrencyInfo("Lebanon", "LBP", "https://flagsapi.com/LB/flat/64.png"),
                "LKR" to CurrencyInfo("Sri Lanka", "LKR", "https://flagsapi.com/LK/flat/64.png"),
                "LRD" to CurrencyInfo("Liberia", "LRD", "https://flagsapi.com/LR/flat/64.png"),
                "LSL" to CurrencyInfo("Lesotho", "LSL", "https://flagsapi.com/LS/flat/64.png"),
                "LYD" to CurrencyInfo("Libya", "LYD", "https://flagsapi.com/LY/flat/64.png"),
                "MAD" to CurrencyInfo("Morocco", "MAD", "https://flagsapi.com/MA/flat/64.png"),
                "MDL" to CurrencyInfo("Moldova", "MDL", "https://flagsapi.com/MD/flat/64.png"),
                "MGA" to CurrencyInfo("Madagascar", "MGA", "https://flagsapi.com/MG/flat/64.png"),
                "MKD" to CurrencyInfo(
                    "North Macedonia (formerly Macedonia)",
                    "MKD",
                    "https://flagsapi.com/MK/flat/64.png"
                ),
                "MMK" to CurrencyInfo("Myanmar (Burma)", "MMK", "https://flagsapi.com/MM/flat/64.png"),
                "MNT" to CurrencyInfo("Mongolia", "MNT", "https://flagsapi.com/MN/flat/64.png"),
                "MOP" to CurrencyInfo("Macau", "MOP", "https://flagsapi.com/MO/flat/64.png"),
                "MRU" to CurrencyInfo("Mauritania", "MRU", "https://flagsapi.com/MR/flat/64.png"),
                "MUR" to CurrencyInfo("Mauritius", "MUR", "https://flagsapi.com/MU/flat/64.png"),
                "MVR" to CurrencyInfo("Maldives", "MVR", "https://flagsapi.com/MV/flat/64.png"),
                "MWK" to CurrencyInfo("Malawi", "MWK", "https://flagsapi.com/MW/flat/64.png"),
                "MXN" to CurrencyInfo("Mexico", "MXN", "https://flagsapi.com/MX/flat/64.png"),
                "MYR" to CurrencyInfo("Malaysia", "MYR", "https://flagsapi.com/MY/flat/64.png"),
                "MZN" to CurrencyInfo("Mozambique", "MZN", "https://flagsapi.com/MZ/flat/64.png"),
                "NAD" to CurrencyInfo("Namibia", "NAD", "https://flagsapi.com/NA/flat/64.png"),
                "NGN" to CurrencyInfo("Nigeria", "NGN", "https://flagsapi.com/NG/flat/64.png"),
                "NIO" to CurrencyInfo("Nicaragua", "NIO", "https://flagsapi.com/NI/flat/64.png"),
                "NOK" to CurrencyInfo("Norway", "NOK", "https://flagsapi.com/NO/flat/64.png"),
                "NPR" to CurrencyInfo("Nepal", "NPR", "https://flagsapi.com/NP/flat/64.png"),
                "NZD" to CurrencyInfo("New Zealand", "NZD", "https://flagsapi.com/NZ/flat/64.png"),
                "OMR" to CurrencyInfo("Oman", "OMR", "https://flagsapi.com/OM/flat/64.png"),
                "PAB" to CurrencyInfo("Panama", "PAB", "https://flagsapi.com/PA/flat/64.png"),
                "PEN" to CurrencyInfo("Peru", "PEN", "https://flagsapi.com/PE/flat/64.png"),
                "PGK" to CurrencyInfo(
                    "Papua New Guinea",
                    "PGK",
                    "https://flagsapi.com/PG/flat/64.png"
                ),
                "PHP" to CurrencyInfo("Philippines", "PHP", "https://flagsapi.com/PH/flat/64.png"),
                "PKR" to CurrencyInfo("Pakistan", "PKR", "https://flagsapi.com/PK/flat/64.png"),
                "PLN" to CurrencyInfo("Poland", "PLN", "https://flagsapi.com/PL/flat/64.png"),
                "PYG" to CurrencyInfo("Paraguay", "PYG", "https://flagsapi.com/PY/flat/64.png"),
                "QAR" to CurrencyInfo("Qatar", "QAR", "https://flagsapi.com/QA/flat/64.png"),
                "RON" to CurrencyInfo("Romania", "RON", "https://flagsapi.com/RO/flat/64.png"),
                "RSD" to CurrencyInfo("Serbia", "RSD", "https://flagsapi.com/RS/flat/64.png"),
                "RUB" to CurrencyInfo("Russia", "RUB", "https://flagsapi.com/RU/flat/64.png"),
                "RWF" to CurrencyInfo("Rwanda", "RWF", "https://flagsapi.com/RW/flat/64.png"),
                "SAR" to CurrencyInfo("Saudi Arabia", "SAR", "https://flagsapi.com/SA/flat/64.png"),
                "SBD" to CurrencyInfo("Solomon Islands", "SBD", "https://flagsapi.com/SB/flat/64.png"),
                "SCR" to CurrencyInfo("Seychelles", "SCR", "https://flagsapi.com/SC/flat/64.png"),
                "SDG" to CurrencyInfo("Sudan", "SDG", "https://flagsapi.com/SD/flat/64.png"),
                "SEK" to CurrencyInfo("Sweden", "SEK", "https://flagsapi.com/SE/flat/64.png"),
                "SGD" to CurrencyInfo("Singapore", "SGD", "https://flagsapi.com/SG/flat/64.png"),
                "SHP" to CurrencyInfo("South Georgia", "SHP", "https://flagsapi.com/SH/flat/64.png"),
                "SLL" to CurrencyInfo("Sierra Leone", "SLL", "https://flagsapi.com/SL/flat/64.png"),
                "SOS" to CurrencyInfo("Somalia", "SOS", "https://flagsapi.com/SO/flat/64.png"),
                "SRD" to CurrencyInfo("Suriname", "SRD", "https://flagsapi.com/SR/flat/64.png"),
                "SSP" to CurrencyInfo("South Sudan", "SSP", "https://flagsapi.com/SS/flat/64.png"),
                "STD" to CurrencyInfo("São Tomé and Príncipe Dobra (pre-2018)", "STD", "https://flagsapi.com/ST/flat/64.png"),
                "STN" to CurrencyInfo(
                    "Sao Tome and Principe",
                    "STN",
                    "https://flagsapi.com/ST/flat/64.png"
                ),
                "SVC" to CurrencyInfo("Salvadoran Colón", "SVC", "https://flagsapi.com/SVC/flat/64.png"),
                "SYP" to CurrencyInfo("Syria", "SYP", "https://flagsapi.com/SY/flat/64.png"),
                "SZL" to CurrencyInfo("Eswatini", "SZL", "https://flagsapi.com/SZ/flat/64.png"),
                "THB" to CurrencyInfo("Thailand", "THB", "https://flagsapi.com/TH/flat/64.png"),
                "TJS" to CurrencyInfo("Tajikistan", "TJS", "https://flagsapi.com/TJ/flat/64.png"),
                "TMT" to CurrencyInfo("Turkmenistan", "TMT", "https://flagsapi.com/TM/flat/64.png"),
                "TND" to CurrencyInfo("Tunisia", "TND", "https://flagsapi.com/TN/flat/64.png"),
                "TOP" to CurrencyInfo("Tonga", "TOP", "https://flagsapi.com/TO/flat/64.png"),
                "TRY" to CurrencyInfo("Turkey", "TRY", "https://flagsapi.com/TR/flat/64.png"),
                "TTD" to CurrencyInfo(
                    "Trinidad and Tobago",
                    "TTD",
                    "https://flagsapi.com/TT/flat/64.png"
                ),
                "TWD" to CurrencyInfo("Taiwan", "TWD", "https://flagsapi.com/TW/flat/64.png"),
                "TZS" to CurrencyInfo("Tanzania", "TZS", "https://flagsapi.com/TZ/flat/64.png"),
                "UAH" to CurrencyInfo("Ukraine", "UAH", "https://flagsapi.com/UA/flat/64.png"),
                "UGX" to CurrencyInfo("Uganda", "UGX", "https://flagsapi.com/UG/flat/64.png"),
                "USD" to CurrencyInfo("United States", "USD", "https://flagsapi.com/US/flat/64.png"),
                "UYU" to CurrencyInfo("Uruguay", "UYU", "https://flagsapi.com/UY/flat/64.png"),
                "UZS" to CurrencyInfo("Uzbekistan", "UZS", "https://flagsapi.com/UZ/flat/64.png"),
                "VES" to CurrencyInfo("Venezuela", "VES", "https://flagsapi.com/VE/flat/64.png"),
                "VND" to CurrencyInfo("Vietnam", "VND", "https://flagsapi.com/VN/flat/64.png"),
                "VUV" to CurrencyInfo("Vanuatu", "VUV", "https://flagsapi.com/VU/flat/64.png"),
                "WST" to CurrencyInfo("Samoa", "WST", "https://flagsapi.com/WS/flat/64.png"),
                "XAF" to CurrencyInfo("Cameroon", "XAF", "https://flagsapi.com/XA/flat/64.png"),
                "XAG" to CurrencyInfo("Silver Ounce", "XAG", "https://flagsapi.com/XAG/flat/64.png"),
                "XAU" to CurrencyInfo("Gold Ounce", "XAU", "https://flagsapi.com/XAU/flat/64.png"),
                "XCD" to CurrencyInfo(
                    "Antigua and Barbuda",
                    "XCD",
                    "https://flagsapi.com/XC/flat/64.png"
                ),
                "XDR" to CurrencyInfo("Special Drawing Rights", "XDR", "https://flagsapi.com/XDR/flat/64.png"),
                "XOF" to CurrencyInfo("West African CFA franc", "XOF", "https://flagsapi.com/XO/flat/64.png"),
                "XPD" to CurrencyInfo("Palladium Ounce", "XPD", "https://flagsapi.com/XPD/flat/64.png"),
                "XPF" to CurrencyInfo("CFP Franc", "XPF", "https://flagsapi.com/XPF/flat/64.png"),
                "XPT" to CurrencyInfo("Platinum Ounce", "XPT", "https://flagsapi.com/XPT/flat/64.png"),
                "YER" to CurrencyInfo("Yemen", "YER", "https://flagsapi.com/YE/flat/64.png"),
                "ZAR" to CurrencyInfo("South Africa", "ZAR", "https://flagsapi.com/ZA/flat/64.png"),
                "ZMW" to CurrencyInfo("Zambia", "ZMW", "https://flagsapi.com/ZM/flat/64.png"),
                "ZWL" to CurrencyInfo("Zimbabwe", "ZWL", "https://flagsapi.com/ZW/flat/64.png")
            )
        }
    }
}