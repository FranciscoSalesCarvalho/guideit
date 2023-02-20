package com.guideit.di.module

import com.guideit.data.api.FinanceApi
import com.guideit.domain.repository.FinanceRepository
import com.guideit.data.repository.FinanceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RepositoryModule {

    @Provides
    @Reusable
    fun provideFinanceRepository(api: FinanceApi): FinanceRepository {
        return FinanceRepositoryImpl(api = api)
    }
}
