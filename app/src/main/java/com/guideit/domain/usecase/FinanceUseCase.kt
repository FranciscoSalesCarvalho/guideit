package com.guideit.domain.usecase

import com.guideit.commons.extensions.safeRunDispatcher
import com.guideit.domain.repository.FinanceRepository
import javax.inject.Inject

class FinanceUseCase @Inject constructor(
    private val repository: FinanceRepository
) {

    suspend fun execute(stock: String = "PETR4.SA") = safeRunDispatcher {
        repository.getStock(stock)
    }
}