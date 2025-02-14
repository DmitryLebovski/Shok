package com.example.shok.components

import android.app.Application
import com.example.shok.di.AppModule
import com.example.shok.di.AuthViewModelModule
import com.example.shok.di.NotificationViewModelModule
import com.example.shok.di.UserViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AuthViewModelModule::class,
    UserViewModelModule::class,
    NotificationViewModelModule::class
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