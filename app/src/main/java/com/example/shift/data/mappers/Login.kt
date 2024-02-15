package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("salt") val salt: String? = null,
    @SerializedName("md5") val md5: String? = null,
    @SerializedName("sha1") val sha1: String? = null,
    @SerializedName("sha256") val sha256: String? = null
)