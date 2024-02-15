package com.example.shift.data.mappers

data class PersonCardInfo(
    val name: Name? = Name(),
    val picture: Picture? = Picture(),
    val location: Location? = Location(),
    val phone: String? = null
)