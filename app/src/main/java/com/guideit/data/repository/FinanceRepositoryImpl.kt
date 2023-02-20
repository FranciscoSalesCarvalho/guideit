package com.guideit.data.repository

import com.guideit.commons.extensions.safeRunDispatcher
import com.guideit.data.api.FinanceApi
import com.guideit.data.mapper.StockMapper.toQuoteSymbol
import com.guideit.domain.repository.FinanceRepository
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val api: FinanceApi
) : FinanceRepository {

    override suspend fun getStock(stock: String) = safeRunDispatcher {
        api.getStock().toQuoteSymbol()
    }
}