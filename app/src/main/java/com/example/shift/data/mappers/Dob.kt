package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Dob(
    @SerializedName("date") val date: String? = null,
    @SerializedName("age") val age: Int? = null
)