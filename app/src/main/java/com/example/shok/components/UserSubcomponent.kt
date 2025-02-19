package com.example.shok.components

import com.example.shok.di.UserViewModelModule
import com.example.user.ProviderUserUseCase
import dagger.Subcomponent

@Subcomponent(modules = [UserViewModelModule::class])
interface UserSubcomponent: ProviderUserUseCase {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserSubcomponent
    }
}
