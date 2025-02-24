package com.example.shok.components

import com.example.notifications.ProviderNotificationUseCase
import com.example.notifications.NotificationRepositoryModule
import dagger.Subcomponent

@Subcomponent(modules = [NotificationRepositoryModule::class])
interface NotificationSubcomponent: ProviderNotificationUseCase {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NotificationSubcomponent
    }
}
