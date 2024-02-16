package com.example.shift.data.network

import com.example.shift.data.mappers.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PeopleAPI {
    @GET
    suspend fun getPeopleSource(@Url url: String): Response<PeopleResponse>
}