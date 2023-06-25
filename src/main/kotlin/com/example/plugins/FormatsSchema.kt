package com.example.plugins

import com.example.plugins.EventCategoryService.EventCategories.autoIncrement
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table

@Serializable
data class EventFormat(val id: Int, val format: String)

class EventFormatService(private val database: Database) {
    object EventFormats : Table(name = "eventformats") {
        val id = integer("id").autoIncrement()
        val format = varchar("format", length = 20)

        override val primaryKey = PrimaryKey(id)
    }
}