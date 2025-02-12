package com.example.shok.components

import com.example.features.auth_screen.ProviderAuthViewModel
import com.example.shok.di.AuthViewModelModule
import dagger.Subcomponent

@Subcomponent(modules = [AuthViewModelModule::class])
interface AuthSubcomponent: ProviderAuthViewModel {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthSubcomponent
    }
}
