package com.example.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class Participation(val id: Int,val userid: Int, val eventid: Int, val participationtypeid: Int)

data class FullParticipation(val id: Int,val userid: Int, val eventid: Int, val participationtype: String)

class ParticipationService(private val database: Database) {
    object Participations : Table(name = "participations") {
        val id = integer("id").autoIncrement()
        val userid = integer("userid")
        val eventid = integer("eventid")
        val participationtypeid = integer("participationtypeid").references(ParticipationTypeService.ParticipationTypes.id)

        override val primaryKey = PrimaryKey(id)
    }

    object FullParticipations : Table(name = "fullparticipations") {
        val id = integer("id").autoIncrement()
        val userid = integer("userid")
        val eventid = integer("eventid")
        val participationtype = varchar("participationtype", length = 20)

        override val primaryKey = PrimaryKey(id)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readAllEqType(userid: Int, filters: FilterReceiveProfileEvents): MutableList<Int> {
        return dbQuery {

            var condition = Op.build { FullParticipations.userid eq userid }

            if (!filters.participationtype.isNullOrEmpty())
                condition = condition.and(FullParticipations.participationtype eq filters.participationtype)

            println("УСЛОВИЕ:" + condition.toString())

            ParticipationService.FullParticipations.select { condition }
                .map { it[ParticipationService.FullParticipations.eventid] }.toMutableList()
        }
    }

    suspend fun readAllEqUser(userid: Int): MutableList<Int> {
        return dbQuery {
            ParticipationService.FullParticipations.select { FullParticipations.userid eq userid }
                .map { it[ParticipationService.FullParticipations.eventid] }.toMutableList()
        }
    }

    suspend fun fetchParticipation(userid: Int, eventid: Int, type: String): Boolean{
        return dbQuery{
            FullParticipations.select { FullParticipations.userid eq userid and(FullParticipations.eventid eq eventid) and(FullParticipations.participationtype eq type) }
                .singleOrNull()
        }!=null
    }

    suspend fun addNewFollower(user_id: Int, event_id: Int):Int = dbQuery{
        val participationInsert =Participations.insert {
                it[userid]=user_id
                it[eventid]=event_id
                it[participationtypeid]=4
            }
        participationInsert.resultedValues?.size?:0
    }

    suspend fun addNewParticipant(user_id: Int, event_id: Int):Int = dbQuery{
        val participationInsert =Participations.insert {
            it[userid]=user_id
            it[eventid]=event_id
            it[participationtypeid]=1
        }
        participationInsert.resultedValues?.size?:0
    }

    suspend fun delete(user_id: Int, event_id: Int,typeid:Int) {
        var condition=Op.build { Participations.userid eq user_id }
        condition=condition.and(Participations.eventid eq event_id)
        condition=condition.and(Participations.participationtypeid eq typeid)
        dbQuery {
            Participations.deleteWhere { condition }
        }
    }
}