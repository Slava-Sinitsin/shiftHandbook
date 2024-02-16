package com.example.shift.data.repository

import android.app.Application
import androidx.room.Room
import com.example.shift.data.maindb.MainDB
import com.example.shift.data.network.PeopleAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePeopleDB(app: Application): MainDB {
        return Room.databaseBuilder(
            context = app,
            klass = MainDB::class.java,
            name = "People.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePeopleAPI(): PeopleAPI {
        val interceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(PeopleAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePeopleRepository(
        mainDB: MainDB,
        peopleAPI: PeopleAPI
    ): PeopleAPIRepositoryImpl {
        return PeopleAPIRepositoryImpl(mainDB = mainDB, peopleAPI = peopleAPI)
    }
}