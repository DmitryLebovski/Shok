package com.example.shok.components

import com.example.user.ProviderUserViewModel
import com.example.shok.di.UserViewModelModule
import dagger.Subcomponent

@Subcomponent(modules = [UserViewModelModule::class])
interface UserSubcomponent: ProviderUserViewModel {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserSubcomponent
    }
}
