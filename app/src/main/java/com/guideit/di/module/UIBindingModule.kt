package com.guideit.di.module

import com.guideit.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}
