package com.example.plugins

import com.example.plugins.EventDocService.EventDocs.autoIncrement
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class EventPhoto(val id: Int,val eventid: Int, val photopath: String)

class EventPhotoService(private val database: Database) {
    object EventPhotos : Table(name = "eventphotos") {
        val id = integer("id").autoIncrement()
        val eventid = integer("eventid")
        val photopath = text("photopath")

        override val primaryKey = PrimaryKey(id)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readAll(id: Int): MutableList<String> {
        return dbQuery {
            EventPhotoService.EventPhotos.slice(EventPhotoService.EventPhotos.photopath).select { EventPhotos.eventid eq id }
                .map { it[EventPhotoService.EventPhotos.photopath] }.toMutableList()
        }
    }
}