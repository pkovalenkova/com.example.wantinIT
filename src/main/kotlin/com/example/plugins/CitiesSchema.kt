package com.example.plugins

import com.example.plugins.OrganizationService.Organizations.autoIncrement
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class City(val id: Int, val city: String)

class CityService(private val database: Database) {
    object Cities : Table(name = "cities") {
        val id = integer("id").autoIncrement()
        val city = varchar("city", length = 30)

        override val primaryKey = PrimaryKey(id)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readAll(): MutableList<City> {
        return dbQuery {
            CityService.Cities.selectAll().map {City(it[CityService.Cities.id],it[CityService.Cities.city])}.toMutableList()
        }
    }
}

