package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.html.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceive>()
        val userService = UserService(DbConnection.database)
        val user = userService.fetchEmail(receive.email)
        println("PERF LOGIN")
        user?: println("NOPE")
        if (user == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (user.password == receive.password) {

                call.respondHtml {head{

                }
                    body{
                        "window.alert(1)"
                    }
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}