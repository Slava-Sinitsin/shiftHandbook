package com.example.shift.data.repository

import com.example.shift.data.mappers.PeopleInfo
import com.example.shift.data.network.ApiService
import com.example.shift.data.network.UserAPI
import retrofit2.Response

object UserAPIRepositoryImpl : UserAPI {
    override suspend fun getPeople(): Response<PeopleInfo> {
        return ApiService.userAPI.getPeople()
    }
}