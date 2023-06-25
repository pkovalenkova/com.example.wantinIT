package com.example.plugins

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceive(
    val email: String,
    val password: String
)