package com.smallrents

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class RegistrationUiState(
    val name: String = "",
    val gender: String = "",
    val birthDate: String = "",
    val cpf: String = "",
    val cep: String = "",
    val street: String = "",
    val number: String = "",
    val complement: String = "",
    val registrationStatus: RegistrationStatus = RegistrationStatus.Idle
)


sealed class RegistrationStatus {
    data object Idle : RegistrationStatus()
    data object Loading : RegistrationStatus()
    data object Success : RegistrationStatus()
    data class Error(val message: String) : RegistrationStatus()
}

class RegistrationViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

    // Function to update username
    fun onUsernameChange(newUsername: String) {
        _uiState.value = _uiState.value.copy(name = newUsername)
    }

    fun onGenderChange(newGender: String) {
        _uiState.value = _uiState.value.copy(gender = newGender)
    }

    fun onBirthDateChange(newBirthDate: String) {
        _uiState.value = _uiState.value.copy(birthDate = newBirthDate)
    }

    fun onCpfChange(newCpf: String) {
        _uiState.value = _uiState.value.copy(cpf = newCpf)
    }

    fun onCepChange(newCep: String) {
        _uiState.value = _uiState.value.copy(cep = newCep)
    }

    fun onStreetChange(newStreet: String) {
        _uiState.value = _uiState.value.copy(street = newStreet)
    }

    fun onNumberChange(newNumber: String) {
        _uiState.value = _uiState.value.copy(number = newNumber)
    }

    fun onComplementChange(newComplement: String) {
        _uiState.value = _uiState.value.copy(complement = newComplement)
    }


    // Function to handle registration
    fun register() {

        // Simulate network request
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(registrationStatus = RegistrationStatus.Loading)

            Log.i("DataTest", "name: ${_uiState.value.name}")
            Log.i("DataTest", "gender: ${_uiState.value.gender}")
            Log.i("DataTest", "birthDate: ${_uiState.value.birthDate}")
            Log.i("DataTest", "cpf: ${_uiState.value.cpf}")
            Log.i("DataTest", "cep: ${_uiState.value.cep}")
            Log.i("DataTest", "street: ${_uiState.value.street}")
            Log.i("DataTest", "number: ${_uiState.value.number}")
            Log.i("DataTest", "complement: ${_uiState.value.complement}")


           /* try {
                // Simulate API call (replace with actual API call logic)
                val response = fakeApiCall(_uiState.value.username, _uiState.value.email, _uiState.value.password)
                if (response) {
                    _uiState.value = _uiState.value.copy(registrationStatus = RegistrationStatus.Success)
                } else {
                    _uiState.value = _uiState.value.copy(registrationStatus = RegistrationStatus.Error("Registration failed"))
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(registrationStatus = RegistrationStatus.Error("An error occurred"))
            }*/


            val apiService = RetrofitClient.instance

            // Making a network call
            apiService.getUsers().enqueue(object : Callback<RegistrationModelEntity> {
                override fun onResponse(call: Call<RegistrationModelEntity>, response: Response<RegistrationModelEntity>) {
                    if (response.isSuccessful) {
                        val users = response.body()
                        Log.d("Retrofit", "Fetched Users: $users")
                    } else {
                        Log.e("Retrofit", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(p0: Call<RegistrationModelEntity>, p1: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}