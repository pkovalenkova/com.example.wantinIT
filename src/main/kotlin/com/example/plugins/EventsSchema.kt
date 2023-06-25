package com.example.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.between
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.Instant
import java.util.*

@Serializable
data class Event(
    val id: Int? = 0,
    val name: String,
    val description: String,
    @Serializable(with=LocalDateTimeSerializer::class)
    val eventdate: Instant,
    val formatid: Int,
    val typeid: Int,
    val categoryid: Int,
    val registration: Boolean,
    @Serializable(with=LocalDateTimeSerializer::class)
    val regdate: Instant?,
    val organizerid: Int,
    val placeid: Int,
    val imgpath: String?,
    val report: String?,
    val approved:Boolean
)

@Serializable
data class FullEvent(
    val id: Int,
    val name: String,
    val category: String,
    val description: String,
    val placename: String,
    val latitude: Double,
    val longitude: Double,
    val city: String,
    val imgpath: String?,
    @Serializable(with=LocalDateTimeSerializer::class)
    val eventdate: Instant,
    val registration: Boolean,
    @Serializable(with=LocalDateTimeSerializer::class)
    val regdate: Instant?,
    val format: String,
    val eventtype: String,
    val report: String?,
    val approved:Boolean
)

class EventService(private val database: Database) {
    object Events : Table(name = "events") {
        val id = integer("id").autoIncrement()
        val name = varchar("name", length = 100)
        val description = varchar("description", length = 600)
        val eventdate = timestamp("eventdate")
        val formatid = integer("formatid").references(EventFormatService.EventFormats.id)
        val typeid = integer("typeid").references(EventTypeService.EventTypes.id)
        val categoryid = integer("categoryid").references(EventCategoryService.EventCategories.id)
        val registration = bool("registration")
        val regdate = timestamp("regdate")
        val organizerid = integer("organizerid").references(UserService.Users.id)
        val placeid = integer("placeid").references(PlaceService.Places.id)
        val imgpath = text("img_path")
        val report = text("report")
        val approved = bool("approved")

        override val primaryKey = PrimaryKey(id)
    }

    object FullEvents : Table(name = "fullevents") {
        val id = integer("id").autoIncrement()
        val name = varchar("name", length = 100)
        val category = varchar("category", length = 20)
        val description = varchar("description", length = 600)
        val placename = varchar("placename", length = 100)
        val latitude = double("latitude")
        val longitude = double("longitude")
        val city = varchar("city", length = 30)
        val imgpath = text("img_path")
        val eventdate = timestamp("eventdate")
        val registration=bool("registration")
        val regdate = timestamp("regdate")
        val format = varchar("format", length = 20)
        val eventtype = varchar("eventtype", length = 20)
        val report = text("report")
        val approved = bool("approved")

        override val primaryKey = PrimaryKey(id)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun create(event: Event): Int = dbQuery {
        Events.insert {
            it[name] = event.name
            it[description] = event.description
            it[eventdate] = event.eventdate
            it[formatid] = event.formatid
            it[typeid] = event.typeid
            it[categoryid] = event.categoryid
            it[registration] = event.registration
            it[regdate] = event.regdate ?: Instant.now()
            it[organizerid] = event.organizerid
            it[placeid] = event.placeid
            it[imgpath] = event.imgpath ?: ""
            it[report] = event.report ?:""
            it[approved]=event.approved
        }[Events.id]
    }

    suspend fun readEvent(id: Int): Event? {
        return dbQuery {
            Events.select { Events.id eq id }
                .map {
                    Event(it[Events.id],
                    it[Events.name],
                    it[Events.description],
                    it[Events.eventdate],
                    it[Events.formatid],
                    it[Events.typeid],
                    it[Events.categoryid],
                    it[Events.registration],
                    it[Events.regdate],
                    it[Events.organizerid],
                    it[Events.placeid],
                    it[Events.imgpath],
                    it[Events.report],
                    it[Events.approved]    ) }
                .singleOrNull()
        }

    }

    /*suspend fun readAll(): MutableList<Event> {
        return dbQuery {
            EventService.Events.selectAll()
                .map {
                    Event(it[Events.id],
                        it[Events.name],
                        it[Events.description],
                        it[Events.eventdate],
                        it[Events.formatid],
                        it[Events.typeid],
                        it[Events.categoryid],
                        it[Events.registration],
                        it[Events.regdate],
                        it[Events.organizerid],
                        it[Events.placeid],
                        it[Events.imgpath],
                        it[Events.report] )
                }.toMutableList()
        }
    }*/

    suspend fun readAll(): MutableList<FullEvent> {
        return dbQuery {
            FullEvents.selectAll()
                .map {
                    FullEvent(it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved])
                }.toMutableList()
        }
    }
    suspend fun readAllDuring(): MutableList<FullEvent> {
        return dbQuery {
            FullEvents.select{FullEvents.eventdate.greaterEq(Instant.now()) and(FullEvents.approved eq true)}
                .map {
                    FullEvent(it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved])
                }.toMutableList()
        }
    }

    suspend fun readAllDuring(filters: FilterReceive): MutableList<FullEvent> {
        return dbQuery {

            var condition= Op.build { FullEvents.eventdate.greaterEq(Instant.now()) and(FullEvents.approved eq true)}

            if(!filters.name.isNullOrEmpty())
                condition=condition.and(FullEvents.name.lowerCase() like "%${filters.name.lowercase()}%")

            if(!filters.city.isNullOrEmpty())
                condition = condition.and(FullEvents.city eq filters.city)

            if(!filters.category.isNullOrEmpty())
                condition = condition.and(FullEvents.category eq filters.category)

            if(!filters.timestart.isNullOrEmpty() && !filters.timeend.isNullOrEmpty())
                condition = condition.and(FullEvents.eventdate.between(filters.timestart.toInstant(), filters.timeend.toInstant() ) )

            if(!filters.type.isNullOrEmpty())
                condition = condition.and(FullEvents.eventtype eq filters.type)

            if(filters.online == true)
                condition = condition.and(FullEvents.format eq "Онлайн")

            if(filters.regOpened == true)
                condition = condition.and(FullEvents.registration eq true )
            println("УСЛОВИЕ:"+condition.toString())

            FullEvents.select{condition}
                .map {
                    FullEvent(it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved])
                }.toMutableList()
        }
    }

    suspend fun readAllArchive(filters: FilterReceive): MutableList<FullEvent> {
        return dbQuery {

            var condition= Op.build { FullEvents.eventdate.less(Instant.now()) }

            if(!filters.name.isNullOrEmpty())
                condition=condition.and(FullEvents.name.lowerCase() like "%${filters.name.lowercase()}%")

            if(!filters.city.isNullOrEmpty())
                condition = condition.and(FullEvents.city eq filters.city)

            if(!filters.category.isNullOrEmpty())
                condition = condition.and(FullEvents.category eq filters.category)

            if(!filters.timestart.isNullOrEmpty() && !filters.timeend.isNullOrEmpty())
                condition = condition.and(FullEvents.eventdate.between(filters.timestart.toInstant(), filters.timeend.toInstant() ) )

            if(!filters.type.isNullOrEmpty())
                condition = condition.and(FullEvents.eventtype eq filters.type)

            if(filters.online == true)
                condition = condition.and(FullEvents.format eq "online")

            if(filters.regOpened == true)
                condition = condition.and(FullEvents.registration eq true )
            println("УСЛОВИЕ:"+condition.toString())

            FullEvents.select{condition}
                .map {
                    FullEvent(it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved])
                }.toMutableList()
        }
    }

    suspend fun readAllArchive(): MutableList<FullEvent> {
        return dbQuery {
            FullEvents.select {FullEvents.eventdate.less(Instant.now())}
                .map {
                    FullEvent(it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved])
                }.toMutableList()
        }
    }

    suspend fun read(id: Int): FullEvent? {
        return dbQuery {
            FullEvents.select { FullEvents.id eq id }
                .map {
                    FullEvent(it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved]) }
                .singleOrNull()
        }
    }

    suspend fun readDuring(id: Int): FullEvent? {
        return dbQuery {
            FullEvents.select { FullEvents.id eq id and (FullEvents.eventdate.greaterEq(Instant.now())) and(FullEvents.approved eq true) }
                .map {
                    FullEvent(
                        it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved]
                    )
                }
                .singleOrNull()
        }
    }

    suspend fun readPast(id: Int): FullEvent? {
        return dbQuery {
            FullEvents.select { FullEvents.id eq id and (FullEvents.eventdate.less(Instant.now())) }
                .map {
                    FullEvent(
                        it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved]
                    )
                }
                .singleOrNull()
        }
    }

    suspend fun readRequest(id: Int):FullEvent? {
        return dbQuery {
            FullEvents.select { FullEvents.id eq id and (FullEvents.eventdate.greaterEq(Instant.now())) and(FullEvents.approved eq false) }
                .map {
                    FullEvent(
                        it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved]
                    )
                }
                .singleOrNull()
        }
    }

    suspend fun readAllRequest():MutableList<FullEvent> {
        return dbQuery {
            FullEvents.select { FullEvents.eventdate.greaterEq(Instant.now()) and(FullEvents.approved eq false) }
                .map {
                    FullEvent(
                        it[FullEvents.id],
                        it[FullEvents.name],
                        it[FullEvents.category],
                        it[FullEvents.description],
                        it[FullEvents.placename],
                        it[FullEvents.latitude],
                        it[FullEvents.longitude],
                        it[FullEvents.city],
                        it[FullEvents.imgpath],
                        it[FullEvents.eventdate],
                        it[FullEvents.registration],
                        it[FullEvents.regdate],
                        it[FullEvents.format],
                        it[FullEvents.eventtype],
                        it[FullEvents.report],
                        it[FullEvents.approved]
                    )
                }
                .toMutableList()
        }
    }

    suspend fun update(id: Int, event: Event) {
        dbQuery {
            Events.update({ Events.id eq id }) {
                it[name] = event.name
                it[description] = event.description
                it[eventdate] = event.eventdate
                it[formatid] = event.formatid
                it[typeid] = event.typeid
                it[categoryid] = event.categoryid
                it[registration] = event.registration
                it[regdate] = event.regdate ?: Instant.now()
                it[organizerid] = event.organizerid
                it[placeid] = event.placeid
                it[imgpath] = event.imgpath ?: ""
                it[report] = event.report ?:""
                it[approved]=event.approved
            }
        }
    }

    suspend fun approve(id: Int, event: Event) {
        dbQuery {
            Events.update({ Events.id eq id }) {
                it[name] = event.name
                it[description] = event.description
                it[eventdate] = event.eventdate
                it[formatid] = event.formatid
                it[typeid] = event.typeid
                it[categoryid] = event.categoryid
                it[registration] = event.registration
                it[regdate] = event.regdate ?: Instant.now()
                it[organizerid] = event.organizerid
                it[placeid] = event.placeid
                it[imgpath] = event.imgpath ?: ""
                it[report] = event.report ?:""
                it[approved]=true
            }
        }
    }

}
