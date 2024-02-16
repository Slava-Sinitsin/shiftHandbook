package com.example.shift.data.repository

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shift.data.maindb.MainDB
import com.example.shift.data.mappers.PeopleInfo
import com.example.shift.data.mappers.Person
import com.example.shift.data.network.ApiService
import com.example.shift.data.network.UserAPI
import retrofit2.Response

class UserAPIRepositoryImpl(val mainDB: MainDB) : UserAPI {
    var peopleList by mutableStateOf(emptyList<Person>())

    override suspend fun getPeopleSource(): Response<PeopleInfo> {
        return ApiService.userAPI.getPeopleSource()
    }

    private suspend fun getPeopleFromStore(): List<Person>? {
        return mainDB.peopleDAO.getPeople()
    }

    suspend fun getPeopleFromSource(): List<Person> {
        mainDB.peopleDAO.deleteAllPeople()
        mainDB.peopleDAO.insertPeople(getPeopleSource().body()?.results ?: emptyList())
        peopleList = getPeopleFromStore() ?: emptyList()
        return peopleList
    }

    suspend fun getPeople(): List<Person> {
        peopleList = getPeopleFromStore() ?: emptyList()
        Log.e("peopleList", peopleList.toString())
        return peopleList.ifEmpty {
            mainDB.peopleDAO.insertPeople(getPeopleFromSource())
            peopleList = getPeopleFromStore() ?: emptyList()
            peopleList
        }
    }
}