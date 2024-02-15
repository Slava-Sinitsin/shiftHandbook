package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("street") val street: Street? = Street(),
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("postcode") val postcode: String? = null,
    @SerializedName("coordinates") val coordinates: Coordinates? = Coordinates(),
    @SerializedName("timezone") val timezone: Timezone? = Timezone()
)