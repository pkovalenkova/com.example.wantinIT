package com.example.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

/*@Serializable
data class Partner(
    var id: Int? = 0,
    var eventid: Int? = 0,
    val imgpath: String?,
    val orgname:String,
    val email: String,
    val phone: String?,
    val site: String,
)

class PartnerService(private val database: Database) {
    object Partners : Table(name = "partners") {
        val id = integer("id").autoIncrement()
        val eventid = integer("eventid")
        val imgpath = text("imgpath")
        val orgname = varchar("orgname", length = 100)
        val email = varchar("email", length = 30)
        val phone = varchar("phone", length = 20)
        val site = varchar("site", length = 50)

        override val primaryKey = PrimaryKey(id)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readEventPartners(id: Int): MutableList<Partner> {
        return dbQuery {
            PartnerService.Partners.select {  PartnerService.Partners.eventid eq id }
                .map { Partner(
                    it[PartnerService.Partners.id],
                    it[PartnerService.Partners.eventid],
                    it[PartnerService.Partners.imgpath],
                    it[PartnerService.Partners.orgname],
                    it[PartnerService.Partners.email],
                    it[PartnerService.Partners.phone],
                    it[PartnerService.Partners.site],
                ) }
                .toMutableList()
        }
    }
}*/
