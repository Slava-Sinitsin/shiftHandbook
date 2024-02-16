package com.example.shift.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shift.data.mappers.Person
import com.example.shift.data.mappers.PersonCardInfo
import com.example.shift.data.repository.PeopleAPIRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleListScreenViewModel @Inject constructor(val repository: PeopleAPIRepositoryImpl) :
    ViewModel() {
    private var peopleList by mutableStateOf(emptyList<Person>())
    var peopleCardInfo by mutableStateOf<List<PersonCardInfo>>(emptyList())
        private set
    var responseMessage by mutableStateOf("")
        private set
    var isError by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            peopleList = repository.getPeople()
            responseMessage = repository.peopleResponse.message ?: ""
            isError = responseMessage != "OK" && responseMessage != ""
            updatePeopleCardInfo()
        }
    }

    fun setIsError(isError: Boolean) {
        this.isError = isError
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
            peopleList = repository.refreshPeople()
            responseMessage = repository.peopleResponse.message ?: ""
            isError = responseMessage != "OK" && responseMessage != ""
            updatePeopleCardInfo()
        }
    }
}