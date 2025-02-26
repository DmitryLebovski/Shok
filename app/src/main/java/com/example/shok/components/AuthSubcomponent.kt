package com.example.shok.components

import com.example.auth.ProviderAuthUtils
import dagger.Subcomponent

@Subcomponent
interface AuthSubcomponent: ProviderAuthUtils {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthSubcomponent
    }
}
