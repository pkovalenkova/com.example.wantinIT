package com.example.plugins

import com.example.plugins.ParticipationTypeService.ParticipationTypes.autoIncrement
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class EventDoc(val id: Int,val eventid: Int, val docpath: String)

class EventDocService(private val database: Database) {
    object EventDocs : Table(name = "eventdocs") {
        val id = integer("id").autoIncrement()
        val eventid = integer("eventid")
        val docpath = text("docpath")

        override val primaryKey = PrimaryKey(id)
    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readAll(id: Int): MutableList<String> {
        return dbQuery {
            EventDocService.EventDocs.slice(EventDocs.docpath).select { EventDocs.eventid eq id }
                .map { it[EventDocService.EventDocs.docpath] }.toMutableList()
        }
    }

}