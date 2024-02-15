package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("title") val title: String? = null,
    @SerializedName("first") val first: String? = null,
    @SerializedName("last") val last: String? = null
)