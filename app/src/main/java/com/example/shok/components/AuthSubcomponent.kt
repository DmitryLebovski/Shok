package com.example.shok.components

import com.example.auth.ProviderAuthUtils
import com.example.auth.AuthRepositoryModule
import dagger.Subcomponent

@Subcomponent(modules = [AuthRepositoryModule::class])
interface AuthSubcomponent: ProviderAuthUtils {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthSubcomponent
    }
}
