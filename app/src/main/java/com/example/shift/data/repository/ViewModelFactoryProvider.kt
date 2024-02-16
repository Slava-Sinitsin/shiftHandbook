package com.example.shift.data.repository

import com.example.shift.ui.viewmodels.PeopleListScreenViewModel
import com.example.shift.ui.viewmodels.PersonInfoScreenViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun peopleListScreenViewModelFactory(): PeopleListScreenViewModel.Factory
    fun personInfoScreenViewModelFactory(): PersonInfoScreenViewModel.Factory
}