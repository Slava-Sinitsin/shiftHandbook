package com.example.shift.data.maindb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shift.data.mappers.Person

@Database(
    entities = [Person::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MainDB : RoomDatabase() {
    abstract val peopleDAO: PeopleDAO
}