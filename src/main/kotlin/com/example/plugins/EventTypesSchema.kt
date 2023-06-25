package com.example.plugins

import com.example.plugins.EventFormatService.EventFormats.autoIncrement
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class EventType(val id: Int, val eventtype: String)

class EventTypeService(private val database: Database) {
    object EventTypes : Table(name = "eventtypes") {
        val id = integer("id").autoIncrement()
        val eventtype = varchar("eventtype", length = 20)

        override val primaryKey = PrimaryKey(id)
    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readAll(): MutableList<EventType> {
        return dbQuery {
            EventTypeService.EventTypes.selectAll().map {EventType(it[EventTypeService.EventTypes.id],it[EventTypeService.EventTypes.eventtype])}.toMutableList()
        }
    }

    suspend fun fetch(type: String): EventType {
        return dbQuery {
            EventTypes.select { EventTypes.eventtype eq type}
                .map {EventType(it[EventTypes.id],it[EventTypes.eventtype])}.single()
        }
    }
}