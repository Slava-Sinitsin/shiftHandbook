package com.example.shift.data.maindb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shift.data.mappers.Id

@Database(
    entities = [Id::class],
    version = 1
)
abstract class MainDB : RoomDatabase() {
    abstract val peopleDAO: PeopleDAO
}