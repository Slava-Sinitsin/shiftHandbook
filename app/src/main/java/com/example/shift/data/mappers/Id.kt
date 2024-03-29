package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: String? = null
)