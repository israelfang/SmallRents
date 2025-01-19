package com.smallrents

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

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

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

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


    fun formatToISO8601(dateString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") // Input format
        val localDate = LocalDate.parse(dateString, inputFormatter)

        val localDateTime = localDate.atStartOfDay()
        return localDateTime.atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)
    }


    fun register() {

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(registrationStatus = RegistrationStatus.Loading)

            val formattedDate = formatToISO8601(_uiState.value.birthDate)

            Log.i("Retrofit", "name: ${_uiState.value.name}")
            Log.i("Retrofit", "gender: ${_uiState.value.gender}")
            Log.i("Retrofit", "birthDate: ${formattedDate}")
            Log.i("Retrofit", "cpf: ${_uiState.value.cpf}")
            Log.i("Retrofit", "cep: ${_uiState.value.cep}")
            Log.i("Retrofit", "street: ${_uiState.value.street}")
            Log.i("Retrofit", "number: ${_uiState.value.number}")
            Log.i("Retrofit", "complement: ${_uiState.value.complement}")

            val request = RegistrationRequest(
                nome = _uiState.value.name,
                sexo = _uiState.value.gender,
                data_Nascimento = formattedDate,
                cpf = _uiState.value.cpf,
                cep = _uiState.value.cep.toInt(),
                logradouro = _uiState.value.street,
                numero = _uiState.value.number.toInt(),
                complemento = _uiState.value.complement
            )

            val gson = Gson()
            val requestJson = gson.toJson(request)
            Log.d("Retrofit", "JSON: $requestJson")

            val apiService = RetrofitClient.instance

            apiService.userRegistration(request).enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        Log.d("Retrofit", "Success response body: $responseBody")

                    } else {
                        Log.e("Retrofit", "Error: ${response.code()}")
                        Log.e("Retrofit", "Error: $response")
                    }
                }

                override fun onFailure(p0: Call<RegistrationResponse>, p1: Throwable) {
                    Log.e("Retrofit", "Failure to fetch users: ${p1.message}")
                }
            })
        }
    }
}