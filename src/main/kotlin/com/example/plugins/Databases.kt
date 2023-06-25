package com.example.plugins

import org.jetbrains.exposed.sql.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.coroutines.*
import java.sql.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

object DbConnection {
    val database = Database.connect(
        url = "jdbc:postgresql://localhost:5434/moduledb",
        user = "postgres",
        driver = "org.postgresql.Driver",
        password = "admin2023"
    )
}

fun Application.configureDatabases() {
    DbConnection.database
//    val dbConnection: Connection = connectToPostgres(embedded = true)

    val organizationService = OrganizationService(DbConnection.database)
    routing {

        // Create user
        post("/users") {

            call.respondText ("ok")
        }
        // Read user
        /*get("/users/{id}") {
            call.respondText ("ok")

        }
        // Update user
       put("/users/{id}") {
            call.respondText ("ok")

        }*/
        // Delete user
        delete("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            call.respond(HttpStatusCode.OK)
        }

        }

}
