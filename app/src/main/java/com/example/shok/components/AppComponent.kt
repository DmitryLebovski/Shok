package com.example.shok.components

import android.app.Application
import com.example.auth.AuthRepository
import com.example.shok.di.AuthRepositoryModule
import com.example.shok.di.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AuthRepositoryModule::class
])

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun combinedSubComponent(): CombinedSubcomponent.Factory
    fun authRepository(): AuthRepository
}


