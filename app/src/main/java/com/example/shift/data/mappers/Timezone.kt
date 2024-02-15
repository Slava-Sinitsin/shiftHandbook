package com.example.shift.data.mappers

import com.google.gson.annotations.SerializedName

data class Timezone(
    @SerializedName("offset") val offset: String? = null,
    @SerializedName("description") val description: String? = null
)