package com.example.shift.data.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shift.data.maindb.MainDB
import com.example.shift.data.mappers.PeopleInfo
import com.example.shift.data.network.ApiService
import com.example.shift.data.network.UserAPI
import retrofit2.Response

class UserAPIRepositoryImpl(val mainDB: MainDB) : UserAPI {
    var peopleList by mutableStateOf(PeopleInfo())

    override suspend fun getPeopleSource(): Response<PeopleInfo> {
        return ApiService.userAPI.getPeopleSource()
    }

    suspend fun getPeopleInfoList(): PeopleInfo {
        peopleList = getPeopleSource().body() ?: PeopleInfo()
        return peopleList
    }
}