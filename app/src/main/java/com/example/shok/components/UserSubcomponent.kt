package com.example.shok.components

import com.example.shok.di.UserRepositoryModule
import com.example.user.ProviderUserUseCase
import dagger.Subcomponent

@Subcomponent(modules = [UserRepositoryModule::class])
interface UserSubcomponent: ProviderUserUseCase {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserSubcomponent
    }
}
