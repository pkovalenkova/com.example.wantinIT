package com.example.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class Place(
    val id: Int,
    val name: String,
    val cityid: Int,
    val street: String,
    val housenumb: Int,
    val latitude : Float,
    val longitude: Float,
)

class PlaceService(private val database: Database) {
    object Places : Table(name = "places") {
        val id = integer("id").autoIncrement()
        val name = varchar("name", length = 100)
        val cityid = integer("cityid").references(CityService.Cities.id)
        val street = varchar("street", length = 20)
        val housenumber = integer("housenumber")
        val latitude = float("latitude")
        val longitude = float("longitude")

        override val primaryKey = PrimaryKey(id)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readAll(): MutableList<Place> {
        return dbQuery {
            Places.selectAll().map {
                Place(
                    it[Places.id],
                    it[Places.name],
                    it[Places.cityid],
                    it[Places.street],
                    it[Places.housenumber],
                    it[Places.latitude],
                    it[Places.longitude]) }.toMutableList()
        }
    }
    suspend fun fetch(place: String): Place {
        return dbQuery {
            Places.select { Places.name eq place}
                .map {
                    Place(
                        it[Places.id],
                        it[Places.name],
                        it[Places.cityid],
                        it[Places.street],
                        it[Places.housenumber],
                        it[Places.latitude],
                        it[Places.longitude]) }.single()
        }
    }
}