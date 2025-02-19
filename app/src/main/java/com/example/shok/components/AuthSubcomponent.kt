package com.example.shok.components

import com.example.auth.ProviderAuthUtils
import com.example.shok.di.AuthViewModelModule
import dagger.Subcomponent

@Subcomponent(modules = [AuthViewModelModule::class])
interface AuthSubcomponent: ProviderAuthUtils {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthSubcomponent
    }
}
