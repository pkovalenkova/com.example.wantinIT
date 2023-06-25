package com.example.plugins


import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class EventCategory(val id: Int, val category: String)

class EventCategoryService(private val database: Database) {
    object EventCategories : Table(name = "eventcategories") {
        val id = integer("id").autoIncrement()
        val category = varchar("category", length = 20)

        override val primaryKey = PrimaryKey(id)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun readAll(): MutableList<EventCategory> {
        return dbQuery {
            EventCategories.selectAll().map {EventCategory(it[EventCategories.id],it[EventCategories.category])}.toMutableList()
        }
    }

    suspend fun fetch(category: String): EventCategory {
        return dbQuery {
            EventCategories.select {EventCategories.category eq category}
                .map {EventCategory(it[EventCategories.id],it[EventCategories.category])}.single()
        }
    }
}