package com.guideit.feature.graph.utils

import com.github.mikephil.charting.formatter.ValueFormatter

class PercentageAxisValueFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        val volume = value.toInt()
        return "$volume%"
    }
}