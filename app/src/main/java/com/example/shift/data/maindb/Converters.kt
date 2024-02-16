package com.example.shift.data.maindb

import androidx.room.TypeConverter
import com.example.shift.data.mappers.Coordinates
import com.example.shift.data.mappers.Dob
import com.example.shift.data.mappers.Id
import com.example.shift.data.mappers.Info
import com.example.shift.data.mappers.Location
import com.example.shift.data.mappers.Login
import com.example.shift.data.mappers.Name
import com.example.shift.data.mappers.Picture
import com.example.shift.data.mappers.Registered
import com.example.shift.data.mappers.Street
import com.example.shift.data.mappers.Timezone
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCoordinates(coordinates: Coordinates?): String? {
        return gson.toJson(coordinates)
    }

    @TypeConverter
    fun toCoordinates(coordinatesString: String?): Coordinates? {
        return gson.fromJson(coordinatesString, Coordinates::class.java)
    }

    @TypeConverter
    fun fromDob(dob: Dob?): String? {
        return gson.toJson(dob)
    }

    @TypeConverter
    fun toDob(dobString: String?): Dob? {
        return gson.fromJson(dobString, Dob::class.java)
    }

    @TypeConverter
    fun fromId(id: Id?): String? {
        return gson.toJson(id)
    }

    @TypeConverter
    fun toId(idString: String?): Id? {
        return gson.fromJson(idString, Id::class.java)
    }

    @TypeConverter
    fun fromInfo(info: Info?): String? {
        return gson.toJson(info)
    }

    @TypeConverter
    fun toInfo(infoString: String?): Info? {
        return gson.fromJson(infoString, Info::class.java)
    }

    @TypeConverter
    fun fromLocation(location: Location?): String? {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(locationString: String?): Location? {
        return gson.fromJson(locationString, Location::class.java)
    }

    @TypeConverter
    fun fromLogin(login: Login?): String? {
        return gson.toJson(login)
    }

    @TypeConverter
    fun toLogin(loginString: String?): Login? {
        return gson.fromJson(loginString, Login::class.java)
    }

    @TypeConverter
    fun fromName(name: Name?): String? {
        return gson.toJson(name)
    }

    @TypeConverter
    fun toName(nameString: String?): Name? {
        return gson.fromJson(nameString, Name::class.java)
    }

    @TypeConverter
    fun fromPicture(picture: Picture?): String? {
        return gson.toJson(picture)
    }

    @TypeConverter
    fun toPicture(pictureString: String?): Picture? {
        return gson.fromJson(pictureString, Picture::class.java)
    }

    @TypeConverter
    fun fromRegistered(registered: Registered?): String? {
        return gson.toJson(registered)
    }

    @TypeConverter
    fun toRegistered(registeredString: String?): Registered? {
        return gson.fromJson(registeredString, Registered::class.java)
    }

    @TypeConverter
    fun fromStreet(street: Street?): String? {
        return gson.toJson(street)
    }

    @TypeConverter
    fun toStreet(streetString: String?): Street? {
        return gson.fromJson(streetString, Street::class.java)
    }

    @TypeConverter
    fun fromTimezone(timezone: Timezone?): String? {
        return gson.toJson(timezone)
    }

    @TypeConverter
    fun toTimezone(timezoneString: String?): Timezone? {
        return gson.fromJson(timezoneString, Timezone::class.java)
    }
}