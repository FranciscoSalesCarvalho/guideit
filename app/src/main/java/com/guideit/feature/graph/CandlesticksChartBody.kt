package com.guideit.feature.graph

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.guideit.domain.model.QuoteSymbol
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.guideit.domain.model.Timeframe
import com.guideit.feature.graph.extensions.CHART_COLORS
import com.guideit.feature.graph.extensions.prepareChart

@Composable
fun CandlesticksChartBody(
    data: List<QuoteSymbol>,
    timeframe: Timeframe = Timeframe.Month,
    selectedStockIndex: Int = 0,
) {
    AndroidView(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxSize(),

        factory = { context ->
            createCandleSticksChart(context, data, selectedStockIndex)
        },
        update = { chart ->
            chart.data = prepareCandlesticksData(data, selectedStockIndex)
            chart.description.text = "${timeframe.name}ly timeframe"
            (chart as CandleStickChart).updateTimeframe(data, timeframe)
            chart.invalidate()
        }
    )
}

fun createCandleSticksChart(
    context: Context,
    data: List<QuoteSymbol>,
    selectedStockIndex: Int,
): BarLineChartBase<CandleData> {

    val chart = CandleStickChart(context)
    chart.prepareChart()
    chart.data = prepareCandlesticksData(data, selectedStockIndex)
    return chart
}

private fun CandleStickChart.updateTimeframe(data: List<QuoteSymbol>, timeframe: Timeframe) {
    //todo change between stocks
    val timestamps = data[0].timestamps
    val xAxisFormatter: ValueFormatter = DefaultAxisValueFormatter(3)
    //todo try to apply correct timestamps
    //        if (timeframe == Timeframe.Week) CandlestickWeekAxisValueFormatter(timestamps)
    //        else CandlestickMonthAxisValueFormatter(timestamps)
    this.xAxis.valueFormatter = xAxisFormatter
}

private fun prepareCandlesticksData(
    data: List<QuoteSymbol>,
    selectedStockIndex: Int
): CandleData {
    val quoteSymbol = data[selectedStockIndex]
    val candleEntries: List<CandleEntry> =
        quoteSymbol.timestamps.mapIndexed { index, _ ->
            val high = quoteSymbol.high[index]
            val low = quoteSymbol.low[index]
            val open = quoteSymbol.open[index]
            val close = quoteSymbol.close[index]
            CandleEntry(
                index.toFloat(),
                high.toFloat(), low.toFloat(),
                open.toFloat(), close.toFloat()
            )
        }
    val dataSet = CandleDataSet(candleEntries, quoteSymbol.symbol)
    val color: Int = CHART_COLORS[selectedStockIndex % CHART_COLORS.size]
    dataSet.color = color
    dataSet.setDrawIcons(false)
    dataSet.axisDependency = YAxis.AxisDependency.LEFT
    dataSet.shadowColor = Color.DKGRAY
    dataSet.shadowWidth = 0.7f
    dataSet.decreasingColor = CHART_COLORS[2]
    dataSet.decreasingPaintStyle = Paint.Style.FILL
    dataSet.increasingColor = CHART_COLORS[0]
    dataSet.increasingPaintStyle = Paint.Style.FILL
    dataSet.neutralColor = Color.BLUE
    return CandleData(dataSet)
}