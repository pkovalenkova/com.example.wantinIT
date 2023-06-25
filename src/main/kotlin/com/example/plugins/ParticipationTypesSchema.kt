package com.example.plugins

import com.example.plugins.EventFormatService.EventFormats.autoIncrement
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table

@Serializable
data class ParticipationType(val id: Int,val participationtype: String)

class ParticipationTypeService(private val database: Database) {
    object ParticipationTypes : Table(name = "participationtypes") {
        val id = integer("id").autoIncrement()
        val participationtype = varchar("participationtype", length = 20)

        override val primaryKey = PrimaryKey(id)
    }
}