package com.guideit

import com.guideit.di.DaggerGuideItComponent
import com.guideit.di.GuideItComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

const val ENGINE_ID = "engine_id"

class GuideItApplication : DaggerApplication() {
    lateinit var flutterEngine: FlutterEngine

    override fun onCreate() {
        super.onCreate()

        flutterEngine = FlutterEngine(this)

        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: GuideItComponent = DaggerGuideItComponent.builder()
            .application(this)
            .build()

        component.inject(this)

        return component
    }
}