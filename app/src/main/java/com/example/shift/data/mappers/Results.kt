package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("name") val name: Name? = Name(),
    @SerializedName("location") val location: Location? = Location(),
    @SerializedName("email") val email: String? = null,
    @SerializedName("login") val login: Login? = Login(),
    @SerializedName("dob") val dob: Dob? = Dob(),
    @SerializedName("registered") val registered: Registered? = Registered(),
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("cell") val cell: String? = null,
    @SerializedName("id") val id: Id? = Id(),
    @SerializedName("picture") val picture: Picture? = Picture(),
    @SerializedName("nat") val nat: String? = null
)