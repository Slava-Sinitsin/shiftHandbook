package com.example.shift.data.mappers

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Id(
    @PrimaryKey var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: String? = null
)