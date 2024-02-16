package com.example.shift.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shift.data.mappers.Person
import com.example.shift.data.mappers.PersonCardInfo
import com.example.shift.data.repository.UserAPIRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PeopleListScreenViewModel @AssistedInject constructor(
    val repository: UserAPIRepositoryImpl,
    @Assisted
    val onPersonClick: (personIndex: Int) -> Unit
) :
    ViewModel() {
    private var peopleList by mutableStateOf(emptyList<Person>())
    var peopleCardInfo by mutableStateOf<List<PersonCardInfo>>(emptyList())
        private set

    @AssistedFactory
    interface Factory {
        fun create(
            onPersonClick: (personIndex: Int) -> Unit
        ): PeopleListScreenViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun providePeopleListScreenViewModel(
            factory: Factory,
            onPersonClick: (personIndex: Int) -> Unit
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(onPersonClick) as T
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            peopleList = repository.getPeople()
            updatePeopleCardInfo()
        }
    }

    private fun updatePeopleCardInfo() {
        peopleCardInfo = peopleList.map { result ->
            PersonCardInfo(
                name = result.name,
                picture = result.picture,
                location = result.location,
                phone = result.phone
            )
        }
    }

    fun getPersonCardInfo(index: Int): PersonCardInfo {
        return peopleCardInfo[index]
    }

    fun refreshPeopleList() {
        viewModelScope.launch {
            peopleList = repository.getPeopleFromSource()
            updatePeopleCardInfo()
        }
    }

    fun navigateToPerson(personIndex: Int) {
        onPersonClick(personIndex)
    }
}