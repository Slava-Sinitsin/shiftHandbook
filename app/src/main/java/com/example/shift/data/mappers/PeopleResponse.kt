package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("message") val message: String? = null,
    @SerializedName("results") val results: ArrayList<Person> = arrayListOf(),
    @SerializedName("info") val info: Info? = Info()
)