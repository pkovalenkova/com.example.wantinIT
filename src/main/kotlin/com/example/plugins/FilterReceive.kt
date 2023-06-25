package com.example.plugins

import kotlinx.serialization.Serializable

@Serializable
data class FilterReceive(
    val name: String?,
    val city: String?,
    val category: String?,
    val timestart: String?,
    val timeend: String?,
    val type: String?,
    val online: Boolean?=false,
    val regOpened: Boolean?=false
)


@Serializable
data class FilterReceiveProfileEvents(
    val userEmail: String,
    val participationtype: String?,
)