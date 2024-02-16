package com.example.shift.data.maindb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shift.data.mappers.Person

@Dao
interface PeopleDAO {
    @Query("SELECT * FROM Person")
    suspend fun getPeople(): List<Person>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: List<Person>)

    @Query("DELETE FROM Person")
    suspend fun deleteAllPeople()
}