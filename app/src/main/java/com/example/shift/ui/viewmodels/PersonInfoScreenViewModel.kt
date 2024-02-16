package com.example.shift.ui.viewmodels

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.shift.data.mappers.Location
import com.example.shift.data.mappers.Person
import com.example.shift.data.repository.UserAPIRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PersonInfoScreenViewModel @AssistedInject constructor(
    val repository: UserAPIRepositoryImpl,
    @Assisted
    val personIndex: Int,
    @Assisted
    val navController: NavHostController
) : ViewModel() {
    var person by mutableStateOf(Person())
        private set

    @AssistedFactory
    interface Factory {
        fun create(
            personIndex: Int,
            navController: NavHostController
        ): PersonInfoScreenViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun providePersonInfoScreenViewModel(
            factory: Factory,
            personIndex: Int,
            navController: NavHostController
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(personIndex, navController) as T
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            person = repository.peopleList[personIndex]
        }
    }

    fun onBackButtonClick() {
        navController.popBackStack()
    }

    fun formatDate(dateString: String): String {
        val parts = dateString.split("T")[0].split("-")
        return "${parts[0]}.${parts[1]}.${parts[2]}"
    }

    fun onLocationClick(context: Context) {
        context.openMap(person.location ?: Location())
    }

    fun onEmailClick(context: Context) {
        context.sendMail(address = person.email.toString())
    }

    fun onPhoneNumberClick(context: Context) {
        context.dial(phone = person.phone.toString())
    }

    private fun Context.openMap(location: Location) {
        try {
            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + Uri.encode("${location.street?.number} ${location.street?.name}, ${location.city}, ${location.country}"))
            )
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            Log.e("ActivityNotFoundException", e.toString())
        } catch (t: Throwable) {
            Log.e("Exception", t.toString())
        }
    }

    private fun Context.sendMail(address: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "vnd.android.cursor.item/email"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e("ActivityNotFoundException", e.toString())
        } catch (t: Throwable) {
            Log.e("Exception", t.toString())
        }
    }

    private fun Context.dial(phone: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply { data = Uri.parse("tel:$phone") }
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e("ActivityNotFoundException", e.toString())
        } catch (t: Throwable) {
            Log.e("Exception", t.toString())
        }
    }
}