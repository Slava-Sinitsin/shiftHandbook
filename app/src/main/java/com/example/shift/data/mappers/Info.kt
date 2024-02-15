package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("seed") val seed: String? = null,
    @SerializedName("results") val results: Int? = null,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("version") val version: String? = null
)