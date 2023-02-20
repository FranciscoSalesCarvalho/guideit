package com.guideit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stock(
    val chart: Chart? = null
) : Parcelable

@Parcelize
data class Chart(
    val result: List<Result>? = null,
    val error: String? = null
) : Parcelable

@Parcelize
data class Result(
    val meta: Meta? = null,
    val timestamp: List<Int>? = null,
    val indicators: Indicators? = null
) : Parcelable

@Parcelize
data class Meta(
    val currency: String? = null,
    val symbol: String? = null,
    val exchangeName: String? = null,
    val instrumentType: String? = null,
    val firstTradeDate: Int? = null,
    var regularMarketTime: Int? = null,
    var gmtoffset: Int? = null,
    var timezone: String? = null,
    var exchangeTimezoneName: String? = null,
    var regularMarketPrice: Double? = null,
    var chartPreviousClose: Double? = null,
    var priceHint: Int? = null,
    var currentTradingPeriod: CurrentTradingPeriod? = null,
    var dataGranularity: String? = null,
    var range: String? = null,
    var validRanges: List<String> = listOf()

) : Parcelable

@Parcelize
data class CurrentTradingPeriod(
    val pre: Pre? = null,
    val regular: Regular? = null,
    val post: Post? = null
) : Parcelable

@Parcelize
data class Pre(
    val timezone: String? = null,
    val end: Int? = null,
    val start: Int? = null,
    val gmtoffset: Int? = null
) : Parcelable

@Parcelize
data class Regular(
    val timezone: String? = null,
    val end: Int? = null,
    val start: Int? = null,
    val gmtoffsetval: Int? = null
) : Parcelable

@Parcelize
data class Post(
    val timezone: String? = null,
    val end: Int? = null,
    val start: Int? = null,
    val gmtoffsetval: Int? = null
) : Parcelable

@Parcelize
data class Indicators(
    val quote: List<Quote>? = null,
    val adjclose: List<Adjclose>? = null
) : Parcelable

@Parcelize
data class Quote(
    val volume: List<Int>? = null,
    val open: List<Double>? = null,
    val close: List<Double>? = null,
    val low: List<Double>? = null,
    val high: List<Double>? = null,
) : Parcelable

@Parcelize
data class Adjclose(
    val adjclose: List<Double>? = null
) : Parcelable
