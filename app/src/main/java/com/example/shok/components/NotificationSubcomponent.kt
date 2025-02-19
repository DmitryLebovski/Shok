package com.example.shok.components

import com.example.notifications.ProviderNotificationUseCase
import com.example.shok.di.NotificationViewModelModule
import dagger.Subcomponent

@Subcomponent(modules = [NotificationViewModelModule::class])
interface NotificationSubcomponent: ProviderNotificationUseCase {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NotificationSubcomponent
    }
}
