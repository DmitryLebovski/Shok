package com.example.shok.components

import android.app.Application
import com.example.shok.di.AppModule
import com.example.auth.AuthRepositoryModule
import com.example.notifications.NotificationRepositoryModule
import com.example.user.UserRepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun authSubcomponent(): AuthSubcomponent.Factory
    fun userSubcomponent(): UserSubcomponent.Factory
    fun notificationSubComponent(): NotificationSubcomponent.Factory
}


