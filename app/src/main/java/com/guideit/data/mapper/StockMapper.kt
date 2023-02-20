package com.guideit.data.mapper

import com.guideit.data.model.Stock
import com.guideit.domain.model.QuoteSymbol

object StockMapper {

    fun Stock.toQuoteSymbol() =
        QuoteSymbol(
            timestamps = this.chart?.result?.get(0)?.timestamp.orEmpty(),
            symbol = this.chart?.result?.get(0)?.meta?.symbol.orEmpty(),
            close = this.chart?.result?.get(0)?.indicators?.quote?.get(0)?.close.orEmpty(),
            open = this.chart?.result?.get(0)?.indicators?.quote?.get(0)?.open.orEmpty(),
            low = this.chart?.result?.get(0)?.indicators?.quote?.get(0)?.low.orEmpty(),
            high = this.chart?.result?.get(0)?.indicators?.quote?.get(0)?.high.orEmpty(),
            volumes = this.chart?.result?.get(0)?.indicators?.quote?.get(0)?.volume.orEmpty(),
        )
}