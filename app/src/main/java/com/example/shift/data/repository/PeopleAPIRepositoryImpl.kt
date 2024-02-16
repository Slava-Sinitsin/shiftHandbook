package com.example.shift.data.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shift.data.maindb.MainDB
import com.example.shift.data.mappers.PeopleResponse
import com.example.shift.data.mappers.Person
import com.example.shift.data.network.PeopleAPI
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.random.Random

class PeopleAPIRepositoryImpl(val mainDB: MainDB, val peopleAPI: PeopleAPI) : PeopleAPI {
    var peopleResponse by mutableStateOf(PeopleResponse())
    var peopleList by mutableStateOf(emptyList<Person>())

    override suspend fun getPeopleSource(url: String): Response<PeopleResponse> {
        return try {
            peopleAPI.getPeopleSource(url)
        } catch (e: SocketTimeoutException) {
            Response.error(408, "Timeout".toResponseBody(null))
        } catch (e: ConnectException) {
            Response.error(503, "Check internet connection".toResponseBody(null))
        } catch (e: UnknownHostException) {
            Response.error(503, "Check internet connection".toResponseBody(null))
        } catch (e: Exception) {
            Response.error(400, "Error occurred".toResponseBody(null))
        }
    }

    private suspend fun getPeopleFromStore(): List<Person>? {
        return mainDB.peopleDAO.getPeople()
    }

    private fun generatePathWithRandomCount(): String {
        val count = Random.nextInt(20, 31)
        return "?results=$count"
    }

    private suspend fun handleResponse(response: Response<PeopleResponse>) {
        response.body()?.let {
            mainDB.peopleDAO.deleteAllPeople()
            mainDB.peopleDAO.insertPeople(it.results)
            peopleList = getPeopleFromStore() ?: emptyList()
            peopleResponse = peopleResponse.copy(message = "OK")
        }
        response.errorBody()?.let {
            peopleResponse = peopleResponse.copy(message = it.string())
            peopleList = emptyList()
        }
    }

    suspend fun getPeople(): List<Person> {
        peopleList = getPeopleFromStore() ?: emptyList()
        return peopleList.ifEmpty {
            val response = getPeopleSource(generatePathWithRandomCount())
            handleResponse(response)
            peopleList
        }
    }

    suspend fun refreshPeople(): List<Person> {
        val response = getPeopleSource(generatePathWithRandomCount())
        handleResponse(response)
        peopleList = getPeopleFromStore() ?: emptyList()
        return peopleList
    }
}