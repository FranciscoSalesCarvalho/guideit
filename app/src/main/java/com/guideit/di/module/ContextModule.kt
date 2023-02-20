package com.guideit.di.module

import android.content.Context
import com.guideit.GuideItApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule {

    @Binds
    abstract fun provideContext(application: GuideItApplication) : Context
}