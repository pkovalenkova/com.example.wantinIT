package com.example.plugins

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceive(

    var secondname: String= "",
    var firstname: String= "",
    var fathername: String = "",
    var email: String= "",
    var password: String= "",
    var passwordConfirm: String= "",
    var ITN: String ="",
    var phone: String = "",
    var orgname: String ="",
) {
    constructor(secondname:String,firstname:String,fathername:String,email:String,password:String,passwordConfirm:String)
            : this(secondname, firstname, fathername, email, password, passwordConfirm,"","","") {

    }

    constructor(ITN:String,orgname:String, email:String,password:String,passwordConfirm:String)
            : this("", "", "", email, password, passwordConfirm,ITN,"",orgname) {

    }
}
