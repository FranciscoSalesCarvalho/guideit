package com.guideit.domain.model

enum class Timeframe {
    Month
}

data class QuoteSymbol(
    val close: List<Double>,
    val high: List<Double>,
    val low: List<Double>,
    val open: List<Double>,
    val symbol: String,
    val timestamps: List<Int>,
    val volumes: List<Int>
) {

    companion object {
        fun mock() = QuoteSymbol(
            close = listOf(),
            high = listOf(),
            low = listOf(),
            open = listOf(),
            symbol = "",
            timestamps = listOf(),
            volumes = listOf()
        )
    }

}