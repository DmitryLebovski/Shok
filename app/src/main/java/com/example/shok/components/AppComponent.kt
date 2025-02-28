package com.example.shok.components

import android.app.Application
import com.example.auth.AuthRepository
import com.example.auth.AuthRepositoryModule
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

    fun userSubcomponent(): UserSubcomponent.Factory
    fun notificationSubComponent(): NotificationSubcomponent.Factory

    fun authRepository(): AuthRepository
}


