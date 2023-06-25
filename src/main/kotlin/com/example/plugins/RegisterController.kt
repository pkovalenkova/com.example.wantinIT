package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class RegisterController(private val call: ApplicationCall, private val registerReceive: RegisterReceive){

    suspend fun registerNewUser(role:String) {
        val userService = UserService(DbConnection.database)

        if (!registerReceive.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }
        else {

            println(registerReceive.toString())

            if (registerReceive.password != registerReceive.passwordConfirm) {
                call.respond(HttpStatusCode.BadRequest, "Пароли не совпадают")
            } else {

                val orgService = OrganizationService(DbConnection.database)

                var user = userService.fetchEmail(registerReceive.email)
                if (user != null) {
                    call.respond(HttpStatusCode.Conflict, "Пользователь уже существует")
                } else {
                    try {
                        user = userService.fetchITN(registerReceive.ITN)
                        if (role == "juridical" && user != null) {
                            val organization = orgService.fetch(registerReceive.orgname)
                            if (organization != null) {
                                call.respond(HttpStatusCode.Conflict, "Данная организация уже существует")
                            } else {
                                call.respond(HttpStatusCode.Conflict, "Организация с заданным ИНН уже существует")
                            }
                        } else {
                            val organization = orgService.fetch(registerReceive.orgname)
                            if (organization == null)
                                if (role == "juridical")
                                    orgService.create(Organization(orgname = registerReceive.orgname, site = ""))
                            val org_id = orgService.fetch(registerReceive.orgname)?.id ?: 1
                            println(registerReceive.toString())
                            userService.create(
                                User(
                                    password = registerReceive.password,
                                    role = role,
                                    firstname = registerReceive.firstname,
                                    secondname = registerReceive.secondname,
                                    fathername = registerReceive.fathername,
                                    email = registerReceive.email,
                                    phone = registerReceive.phone,
                                    itn = registerReceive.ITN,
                                    org_id = org_id,
                                    imgpath = "",
                                    verified = false
                                )
                            )
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.localizedMessage}")
                    }
                }
            }
        }
    }
}