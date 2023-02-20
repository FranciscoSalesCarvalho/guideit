package com.guideit.data.repository

import com.guideit.data.api.FinanceApi
import com.guideit.data.mapper.StockMapper.toQuoteSymbol
import com.guideit.domain.repository.FinanceRepository
import com.guideit.data.model.Stock
import com.guideit.domain.model.QuoteSymbol
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val api: FinanceApi
) : FinanceRepository {

    override suspend fun getStock(stock: String): QuoteSymbol {
        return api.getStock().toQuoteSymbol()
    }
}