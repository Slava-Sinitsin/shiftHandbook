package com.example.shift.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shift.data.mappers.PeopleInfo
import com.example.shift.data.mappers.PersonCardInfo
import com.example.shift.data.repository.UserAPIRepositoryImpl
import kotlinx.coroutines.launch

class PeopleListScreenViewModel(val onPersonClick: (personIndex: Int) -> Unit) : ViewModel() {
    private var peopleList by mutableStateOf(PeopleInfo())
    var peopleCardInfo by mutableStateOf<List<PersonCardInfo>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        peopleList = UserAPIRepositoryImpl.getPeopleInfoList()
        updatePeopleCardInfo()
    }

    private fun updatePeopleCardInfo() {
        peopleCardInfo = peopleList.results.map { result ->
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
            fetchData()
        }
    }

    fun navigateToPerson(personIndex: Int) {
        onPersonClick(personIndex)
    }
}