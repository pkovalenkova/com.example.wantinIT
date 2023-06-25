package com.example.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

/*
@Serializable
data class Speaker(
    var id: Int? = 0,
    var eventid: Int? = 0,
    val imgpath: String?,
    val firstname: String,
    val secondname: String,
    val fathername: String?,
    val orgname:String
)

class SpeakerService(private val database: Database) {
    object Speakers : Table(name = "speakers") {
        val id = integer("id").autoIncrement()
        val eventid = integer("eventid")
        val imgpath = text("imgpath")
        val firstname = varchar("firstname", length = 20)
        val secondname = varchar("secondname", length = 30)
        val fathername = varchar("fathername", length = 20)
        val orgname = varchar("orgname", length = 100)

        override val primaryKey = PrimaryKey(id)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readEventSpeakers(id: Int): MutableList<Speaker> {
        return dbQuery {
            SpeakerService.Speakers.select {  SpeakerService.Speakers.eventid eq id }
                .map { Speaker(
                    it[SpeakerService.Speakers.id],
                    it[SpeakerService.Speakers.eventid],
                    it[SpeakerService.Speakers.imgpath],
                    it[SpeakerService.Speakers.firstname],
                    it[SpeakerService.Speakers.secondname],
                    it[SpeakerService.Speakers.fathername],
                    it[SpeakerService.Speakers.orgname],
                ) }
                .toMutableList()
        }
    }
}
*/
