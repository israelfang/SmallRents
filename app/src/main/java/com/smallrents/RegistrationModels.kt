package com.smallrents

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("nome")val nome: String,
    @SerializedName("cep")val cep: Int,
    @SerializedName("logradouro")val logradouro: String,
    @SerializedName("numero")val numero: Int,
    @SerializedName("complemento")val complemento: String,
    @SerializedName("sexo")val sexo: String,
    @SerializedName("data_Nascimento")val data_Nascimento: String,
    @SerializedName("cpf")val cpf: String
)

data class RegistrationResponse(
    @SerializedName("message")val message: String,
    @SerializedName("usuario")val usuario: User
)

data class User(
    @SerializedName("nome")val nome: String,
    @SerializedName("cep")val cep: Int,
    @SerializedName("logradouro")val logradouro: String,
    @SerializedName("numero")val numero: Int,
    @SerializedName("complemento")val complemento: String,
    @SerializedName("sexo")val sexo: String,
    @SerializedName("data_Nascimento")val data_Nascimento: String,
    @SerializedName("cpf")val cpf: String
)