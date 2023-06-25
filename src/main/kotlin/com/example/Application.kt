package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import com.example.plugins.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*

data class UserSession(val user:User):Principal

//data class UserSession(val name: String):Principal

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}



fun Application.module() {
    install(Sessions) {
            cookie<UserSession>("user_session") {
                cookie.path = "/"
                cookie.maxAgeInSeconds = 300
            }
    }
    install(Authentication){
        form("auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            /*validate { credentials ->
                println("CREDENTIALS " +credentials.name+" "+credentials.password)
                if (credentials.name == "jetbrains" && credentials.password == "foobar") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }*/
            validate { credentials ->
                val userService=UserService(DbConnection.database)
                val user = userService.fetch(credentials.name)
                if(user!=null){
                    if (user.password==credentials.password) {
                        UserIdPrincipal(credentials.name)
                    }
                    else {
                        println("Неправильные данные учетной записи")
                        null
                    }
                } else{
                    println("Такого пользователя не существует")
                    null
                }
            }
            challenge {
                call.respond(HttpStatusCode.Unauthorized, "Credentials are not valid")
            }
        }

        session<UserSession>("auth-session") {
            validate { session ->
                val userService=UserService(DbConnection.database)
                val user = userService.fetch(session.user.email)
                if(user!=null) {
                    if (user.password==session.user.password) {
                        session
                    }
                    else {
                        null
                    }
                } else {
                    null
                }
            }
            challenge {
                call.respondRedirect("/login")
            }
        }
    }




    configureSerialization()
    configureDatabases()
    configureTemplating()
  //  configureSecurity()
    configureRouting()
}
