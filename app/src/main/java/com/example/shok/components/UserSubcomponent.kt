package com.example.shok.components

import com.example.user.UserRepositoryModule
import com.example.user.ProviderUserUseCase
import dagger.Subcomponent

@Subcomponent(modules = [UserRepositoryModule::class])
interface UserSubcomponent: ProviderUserUseCase {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserSubcomponent
    }
}
