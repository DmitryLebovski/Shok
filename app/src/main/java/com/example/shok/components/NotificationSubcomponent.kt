package com.example.shok.components

import com.example.features.notification_screen.ProviderNotificationViewModel
import com.example.shok.di.UserViewModelModule
import dagger.Subcomponent

@Subcomponent(modules = [UserViewModelModule::class])
interface NotificationSubcomponent: ProviderNotificationViewModel {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NotificationSubcomponent
    }
}
