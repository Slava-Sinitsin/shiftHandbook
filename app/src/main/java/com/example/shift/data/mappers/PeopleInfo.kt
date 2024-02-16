package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class PeopleInfo(
    @SerializedName("results") val results: ArrayList<Person> = arrayListOf(),
    @SerializedName("info") val info: Info? = Info()
)