package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Street(
    @SerializedName("number") val number: Int? = null,
    @SerializedName("name") val name: String? = null
)