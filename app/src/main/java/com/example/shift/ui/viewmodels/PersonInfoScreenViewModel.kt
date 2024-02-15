package com.example.shift.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.shift.data.mappers.Results
import com.example.shift.data.repository.UserAPIRepositoryImpl
import kotlinx.coroutines.launch

class PersonInfoScreenViewModel(
    private val personIndex: Int,
    private val navController: NavHostController
) : ViewModel() {
    var person by mutableStateOf(Results())
        private set

    init {
        viewModelScope.launch {
            person = UserAPIRepositoryImpl.peopleList.results[personIndex]
        }
    }

    fun onBackButtonClick() {
        navController.popBackStack()
    }

    fun formatDate(dateString: String): String {
        val parts = dateString.split("T")[0].split("-")
        return "${parts[0]}.${parts[1]}.${parts[2]}"
    }
}