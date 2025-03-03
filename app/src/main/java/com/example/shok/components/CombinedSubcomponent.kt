package com.example.shok.components

import com.example.shok.di.CombinedModule
import com.example.shok.di.NotificationRepositoryModule
import com.example.shok.di.UserRepositoryModule
import com.example.user.ProviderCombinedUseCase
import dagger.Subcomponent

@Subcomponent(modules = [UserRepositoryModule::class, NotificationRepositoryModule::class, CombinedModule::class])
interface CombinedSubcomponent : ProviderCombinedUseCase {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CombinedSubcomponent
    }
}