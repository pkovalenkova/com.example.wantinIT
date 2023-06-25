package com.example.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object UserRoles{
    val individual : String = "individual"
    val juridical : String = "juridical"
    val moder : String = "moder"
    val admin : String = "admin"
}

@Serializable
data class User(
    var id: Int? = 0,
    val password: String,
    val role: String,
    val firstname: String,
    val secondname: String,
    val fathername: String?,
    val email: String,
    val phone: String?,
    val itn: String?,
    val org_id: Int?=1,
    val imgpath: String?,
    val verified: Boolean,
)
@Serializable
data class Speaker(
    var id: Int? = 0,
    var eventid: Int? = 0,
    val imgpath: String?,
    val firstname: String,
    val secondname: String,
    val fathername: String?,
    val orgname:String
)
@Serializable
data class Partner(
    var id: Int? = 0,
    var eventid: Int? = 0,
    val imgpath: String?,
    val orgname:String,
    val email: String,
    val phone: String?,
    val site: String,
)
@Serializable
data class Organizer(
    var eventid: Int? = 0,
    var userid: Int? = 0,
    val imgpath: String?,
    val orgname:String,
    val email: String,
    val phone: String?,
    val site: String,
)

class UserService(private val database: Database) {
    object Users : Table(name = "users") {
        val id = integer("id").autoIncrement()
        val password = varchar("password", length = 32)
        val role = varchar("role", length = 20)
        val firstname = varchar("firstname", length = 20)
        val secondname = varchar("secondname", length = 30)
        val fathername = varchar("fathername", length = 20)
        val email = varchar("email", length = 30)
        val phone = varchar("phone", length = 20)
        val itn = varchar("itn", length = 12)
        val org_id = integer("org_id").references(OrganizationService.Organizations.id)
        val imgpath = text("imgpath")
        val verified = bool("verified")

        override val primaryKey = PrimaryKey(id)
    }

    object Speakers : Table(name = "speakers") {
        val id = integer("id").autoIncrement()
        val eventid = integer("eventid")
        val imgpath = text("imgpath")
        val firstname = varchar("firstname", length = 20)
        val secondname = varchar("secondname", length = 30)
        val fathername = varchar("fathername", length = 20)
        val orgname = varchar("orgname", length = 100)

        override val primaryKey = PrimaryKey(id)
    }
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

    object Organizers : Table(name = "organizers") {
        val eventid = integer("eventid").autoIncrement()
        val userid = integer("userid")
        val imgpath = text("imgpath")
        val orgname = varchar("orgname", length = 100)
        val email = varchar("email", length = 30)
        val phone = varchar("phone", length = 20)
        val site = varchar("site", length = 50)

        override val primaryKey = PrimaryKey(eventid)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }


    suspend fun create(user: User): Int = dbQuery {
        val userInsert = Users.insert {
            it[password] = user.password
            it[role] = user.role
            it[firstname] = user.firstname
            it[secondname] = user.secondname
            it[fathername] = user.fathername?:""
            it[email] = user.email
            it[phone] = user.phone?:""
            it[itn] = user.itn?:""
            it[org_id] = user.org_id ?:1
            it[imgpath] = user.imgpath?:""
            it[verified] = user.verified
        }
        userInsert.resultedValues?.size?:0
    }

    suspend fun read(id: Int): User? {
        return dbQuery {
            Users.select { Users.id eq id }
                .map { User(
                    it[Users.id],
                    it[Users.password],
                    it[Users.role],
                    it[Users.firstname],
                    it[Users.secondname],
                    it[Users.fathername],
                    it[Users.email],
                    it[Users.phone],
                    it[Users.itn],
                    it[Users.org_id],
                    it[Users.imgpath],
                    it[Users.verified],
                    ) }
                .singleOrNull()
        }
    }

    suspend fun fetchEmail(email: String): User? {
        return dbQuery {
            Users.select { Users.email eq email}
                .map { User(
                    it[Users.id],
                    it[Users.password],
                    it[Users.role],
                    it[Users.firstname],
                    it[Users.secondname],
                    it[Users.fathername],
                    it[Users.email],
                    it[Users.phone],
                    it[Users.itn],
                    it[Users.org_id],
                    it[Users.imgpath],
                    it[Users.verified],
                ) }
                .singleOrNull()
        }
    }
    suspend fun fetchITN(itn: String): User? {
        return dbQuery {
            Users.select { Users.itn eq itn}
                .map { User(
                    it[Users.id],
                    it[Users.password],
                    it[Users.role],
                    it[Users.firstname],
                    it[Users.secondname],
                    it[Users.fathername],
                    it[Users.email],
                    it[Users.phone],
                    it[Users.itn],
                    it[Users.org_id],
                    it[Users.imgpath],
                    it[Users.verified],
                ) }
                .singleOrNull()
        }
    }
    suspend fun fetch(email: String): User? {
        return dbQuery {
            Users.select { Users.email eq email}
                .map { User(
                    it[Users.id],
                    it[Users.password],
                    it[Users.role],
                    it[Users.firstname],
                    it[Users.secondname],
                    it[Users.fathername],
                    it[Users.email],
                    it[Users.phone],
                    it[Users.itn],
                    it[Users.org_id],
                    it[Users.imgpath],
                    it[Users.verified],
                ) }
                .singleOrNull()
        }
    }

    suspend fun readEventSpeakers(id: Int): MutableList<Speaker> {
        return dbQuery {
            Speakers.select {  Speakers.eventid eq id }
                .map { Speaker(
                    it[Speakers.id],
                    it[Speakers.eventid],
                    it[Speakers.imgpath],
                    it[Speakers.firstname],
                    it[Speakers.secondname],
                    it[Speakers.fathername],
                    it[Speakers.orgname],
                ) }
                .toMutableList()
        }
    }

    suspend fun readEventPartners(id: Int): MutableList<Partner> {
        return dbQuery {
            Partners.select {  Partners.eventid eq id }
                .map { Partner(
                    it[Partners.id],
                    it[Partners.eventid],
                    it[Partners.imgpath],
                    it[Partners.orgname],
                    it[Partners.email],
                    it[Partners.phone],
                    it[Partners.site],
                ) }
                .toMutableList()
        }
    }

    suspend fun readOrganizer(id: Int): Organizer {
        return dbQuery {
            Organizers.select {  Organizers.eventid eq id }
                .map { Organizer(
                    it[Organizers.eventid],
                    it[Organizers.userid],
                    it[Organizers.imgpath],
                    it[Organizers.orgname],
                    it[Organizers.email],
                    it[Organizers.phone],
                    it[Organizers.site],
                ) }
                .single()
        }
    }
    suspend fun readUserForOrganization(id: Int): User {
        return dbQuery {
            Users.select {  Users.org_id eq id and(Users.role eq UserRoles.juridical)}
                .map { User(
                    it[Users.id],
                    it[Users.password],
                    it[Users.role],
                    it[Users.firstname],
                    it[Users.secondname],
                    it[Users.fathername],
                    it[Users.email],
                    it[Users.phone],
                    it[Users.itn],
                    it[Users.org_id],
                    it[Users.imgpath],
                    it[Users.verified],
                ) }
                .single()
        }
    }

    suspend fun readEventsForOrganizer(orgname: String): MutableList<Int> {
        return dbQuery {
            Organizers.select {  Organizers.orgname eq orgname }
                .map {  it[Organizers.eventid]}.toMutableList()
        }
    }

    /*
        suspend fun update(id: Int, user: User) {
            dbQuery {
                Users.update({ Users.id eq id }) {
                    it[name] = user.name
                    it[age] = user.age
                }
            }
        }

        suspend fun delete(id: Int) {
            dbQuery {
                Users.deleteWhere { Users.id.eq(id) }
            }
        }*/
}