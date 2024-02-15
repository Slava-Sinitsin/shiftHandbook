package com.example.shift.data.network

import com.example.shift.data.mappers.PeopleInfo
import retrofit2.Response
import retrofit2.http.GET

interface UserAPI {
    @GET("?results=30")
    suspend fun getPeople(): Response<PeopleInfo>
}