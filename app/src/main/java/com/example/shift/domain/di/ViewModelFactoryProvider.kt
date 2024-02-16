package com.example.shift.domain.di

import com.example.shift.ui.viewmodels.PersonInfoScreenViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun personInfoScreenViewModelFactory(): PersonInfoScreenViewModel.Factory
}