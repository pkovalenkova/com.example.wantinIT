package com.example.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

@Serializable
data class Organization(val id: Int? = 0, val orgname: String, val site:String?="")

class OrganizationService(private val database: Database) {
    object Organizations : Table(name = "organizations") {
        val id = integer("id").autoIncrement()
        val orgname = varchar("orgname", length = 100)
        val site = varchar("site", length = 50)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Organizations)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun create(organization: Organization): Int = dbQuery {
        val organizationInsert = Organizations.insert {
            it[orgname] = organization.orgname
            it[site] = organization.site?:""
        }
        organizationInsert.resultedValues?.size?:0
    }
    suspend fun readAll(): MutableList<Organization> {
        return dbQuery {
            Organizations.selectAll().map { Organization(it[Organizations.id], it[Organizations.orgname],it[Organizations.site]) }.toMutableList()
        }
    }
    suspend fun read(id: Int): Organization? {
        return dbQuery {
            Organizations.select { Organizations.id eq id }
                .map { Organization(it[Organizations.id], it[Organizations.orgname],it[Organizations.site]) }
                .singleOrNull()
        }
    }

    suspend fun fetch(orgname: String): Organization? {
        return dbQuery {
            Organizations.select { Organizations.orgname eq orgname }
                .map { Organization(it[Organizations.id], it[Organizations.orgname],it[Organizations.site]) }
                .singleOrNull()
        }
    }

    suspend fun update(id: Int, organization: Organization) {
        dbQuery {
            Organizations.update({ Organizations.id eq id }) {
                it[orgname] = organization.orgname
                it[site] = organization.site?:""
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            Organizations.deleteWhere { Organizations.id.eq(id) }
        }
    }
}