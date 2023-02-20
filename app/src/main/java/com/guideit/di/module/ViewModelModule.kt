package com.guideit.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guideit.commons.viewmodel.ViewModelFactory
import com.guideit.commons.viewmodel.ViewModelKey
import com.guideit.feature.graph.GraphViewModel
import com.guideit.feature.home.HomeViewModel
import com.guideit.feature.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GraphViewModel::class)
    internal abstract fun bindGraphViewModel(viewModel: GraphViewModel): ViewModel
}
