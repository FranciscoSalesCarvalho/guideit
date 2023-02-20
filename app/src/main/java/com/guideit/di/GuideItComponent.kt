package com.guideit.di

import android.app.Application
import com.guideit.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        AndroidSupportInjectionModule::class,
        UIBindingModule::class
    ]
)
interface GuideItComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): GuideItComponent
    }
}