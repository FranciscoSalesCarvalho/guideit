package com.guideit.domain.repository

import com.guideit.domain.model.QuoteSymbol

interface FinanceRepository {

    suspend fun getStock(stock: String): QuoteSymbol
}
