package com.example.plugins

import com.example.UserSession
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.resources.*
import io.ktor.server.resources.Resources
import kotlinx.serialization.Serializable
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.respondRedirect
import io.ktor.server.sessions.*
import kotlinx.html.*
import java.io.File


fun Application.configureRouting() {
    install(Resources)

    val organizationService = OrganizationService(DbConnection.database)
    val eventService = EventService(DbConnection.database)
    val placeService = PlaceService(DbConnection.database)
    val cityService = CityService(DbConnection.database)
    val eventCategoryService = EventCategoryService(DbConnection.database)
    val eventTypeService = EventTypeService(DbConnection.database)
    val userService = UserService(DbConnection.database)
    val docService = EventDocService(DbConnection.database)
    val photoService = EventPhotoService(DbConnection.database)
    val participationService=ParticipationService(DbConnection.database)
    //val speakerService = SpeakerService(DbConnection.database)
    //val partnerService = PartnerService(DbConnection.database)

    routing {

        get("/") {
            val userSession = call.sessions.get<UserSession>()
            call.respondText ( Parts.pageHeadMain()
                    +Parts.pageHeader(userSession)
                    +Parts.mainMain(userSession)
                    +Parts.pageFooter(),
                ContentType.Text.Html )
            /*call.respondHtml {

                head {
                    title("Хочу в IT")
                    script{type = ScriptType.textJavaScript
                            this.src = "eventsscript.js"}
                }
                body {

                    main {
                        div{
                            this.id = "sampleDiv"
                            this.classes = setOf("class1")
                            +"Главная страница"
                            ul {
                                li {
                                    a("/registration"){
                                        +"Регистрация"
                                    }
                                }
                                li {
                                    a("/login"){
                                        +"Войти"
                                    }
                                }
                                li {
                                    a("/events"){
                                        +"Календарь мероприятий"
                                    }
                                }
                                li {
                                    a("/archive"){
                                        +"Архив"
                                    }
                                }
                                li {
                                    a("/vacancies"){
                                        +"Вакансии"
                                    }
                                }
                            }

                        }
                    }
                    button {
                        onClick = "window.alert(1)"
                        id = "filterButton"
                        text("sadsadsdasd")
                    }

                }
            }*/
        }

        staticResources("/", File("*").toString()) {
               // default("static/logo.png")
                preCompressed(CompressedFileType.GZIP)
        }

        get("/events"){

            val eventlist = eventService.readAllDuring()
            eventlist.sortBy { it.eventdate }
            val citylist=cityService.readAll()
            val eventcategorylist=eventCategoryService.readAll()
            val eventtypelist=eventTypeService.readAll()
            val userSession = call.sessions.get<UserSession>()
            call.respondText ( Parts.pageHeadEvents()
                    + Parts.pageHeader(userSession)
                    +Parts.mainEvents(eventlist,citylist,eventcategorylist,eventtypelist)
                    +Parts.pageFooter()
                    +Parts.pageScriptAxios()
                    +Parts.pageScriptEvents(),
                     ContentType.Text.Html )
        }

        get("/filtred-events"){
            val filter = FilterReceive(
                call.request.queryParameters["name"],
                call.request.queryParameters["city"],
                call.request.queryParameters["category"],
                call.request.queryParameters["timestart"].toString(),
                call.request.queryParameters["timeend"].toString(),
                call.request.queryParameters["type"],
                call.request.queryParameters["online"].toBoolean(),
                call.request.queryParameters["regOpened"].toBoolean()
            )
            println("Фильтры:"+filter.toString())
            val eventlist = eventService.readAllDuring(filter)
            if(eventlist.isNotEmpty()) {
                eventlist.sortBy { it.eventdate }
                call.respondText(Parts.filtredEvents(eventlist), ContentType.Text.Html)
            }
            else{
                call.respondText("Подходящих мероприятий не найдено")
            }
        }

        get("/filtred-archive"){
            val filter = FilterReceive(
                call.request.queryParameters["name"],
                call.request.queryParameters["city"],
                call.request.queryParameters["category"],
                call.request.queryParameters["timestart"].toString(),
                call.request.queryParameters["timeend"].toString(),
                call.request.queryParameters["type"],
                call.request.queryParameters["online"].toBoolean(),
                call.request.queryParameters["regOpened"].toBoolean()
            )
            println("Фильтры:"+filter.toString())
            val eventlist = eventService.readAllArchive(filter)
            if(eventlist.isNotEmpty()) {
                eventlist.sortByDescending { it.eventdate }
                call.respondText(Parts.filtredEvents(eventlist), ContentType.Text.Html)
            }
            else{
                call.respondText("Подходящих мероприятий не найдено")
            }
        }

        get("/events/{id}"){
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val event: FullEvent? = eventService.read(id)
            val userSession = call.sessions.get<UserSession>()
            if (event != null) {

                    val speakerlist = userService.readEventSpeakers(event.id)
                    val partnerlist = userService.readEventPartners(event.id)
                    val organizer = userService.readOrganizer(event.id)
                    val doclist = docService.readAll(event.id)
                    val photolist = photoService.readAll(event.id)

                    var isParticipant = false
                    var isFollower = false
                    var isPartner = false

                    if (userSession != null) {
                        if (userSession.user.role == UserRoles.individual) {
                            isParticipant =
                                participationService.fetchParticipation(userSession.user.id!!, event.id, "Участник")
                            isFollower =
                                participationService.fetchParticipation(userSession.user.id!!, event.id, "Подписчик")
                        }

                        if (userSession.user.role == UserRoles.juridical) {
                            isPartner =
                                participationService.fetchParticipation(userSession.user.id!!, event.id, "Партнер")
                        }
                    }
                if(event.approved) {
                    call.respondText(
                        Parts.pageHeadEvent(event.name)
                                + Parts.pageHeaderDown(userSession)
                                + Parts.mainSingleEvent(
                            event,
                            speakerlist,
                            partnerlist,
                            organizer,
                            doclist,
                            photolist,
                            userSession,
                            isFollower,
                            isParticipant,
                            isPartner
                        )
                                + Parts.pageFooterDown()
                                + Parts.pageScriptSlider()
                                + Parts.pageScriptAxios()
                                + Parts.pageScriptSingleEvent(),
                        ContentType.Text.Html
                    )
                }
                else{
                    if(userSession!=null){
                        if(userSession.user.role==UserRoles.individual) call.respondText { "Данная страница недоступна" }
                        else{
                            if(userSession.user.role==UserRoles.juridical&&organizer.userid!=userSession.user.id) call.respondText { "Данная страница недоступна" }
                            else{
                                call.respondText(
                                    Parts.pageHeadEvent(event.name)
                                            + Parts.pageHeaderDown(userSession)
                                            + Parts.mainSingleEvent(
                                        event,
                                        speakerlist,
                                        partnerlist,
                                        organizer,
                                        doclist,
                                        photolist,
                                        userSession,
                                        isFollower,
                                        isParticipant,
                                        isPartner
                                    )
                                            + Parts.pageFooterDown()
                                            + Parts.pageScriptSlider()
                                            + Parts.pageScriptAxios()
                                            + Parts.pageScriptSingleEvent(),
                                    ContentType.Text.Html
                                )
                            }

                        }
                    }
                    else call.respondRedirect("/login")
                }
            }
        }

        get("/follow-script"){
            val value=call.request.queryParameters["value"].toBoolean()
            val eventid= call.request.queryParameters["eventid"]!!.toInt()
            val userSession = call.sessions.get<UserSession>()
            if(userSession!=null) {
                if (value) {
                    // добавление в таблицу записи отслеживающего
                    participationService.addNewFollower(userSession.user.id!!,eventid)
                    call.respondText(Parts.becomeFollower(value), ContentType.Text.Html)

                } else {
                    // удаление из таблицы записи отслеживающего
                    participationService.delete(userSession.user.id!!,eventid,4)
                    call.respondText(Parts.becomeFollower(value), ContentType.Text.Html)


                }
            }
            else call.respondRedirect("/login")
        }

        get("/check-script"){
            val value=call.request.queryParameters["value"].toBoolean()
            val eventid= call.request.queryParameters["eventid"]!!.toInt()
            val userSession = call.sessions.get<UserSession>()
            if(userSession!=null) {
                if (value) {
                    // добавление в таблицу записи участника
                    participationService.addNewParticipant(userSession.user.id!!,eventid)
                    call.respondText(Parts.takePart(value), ContentType.Text.Html)

                } else {
                    // удаление из таблицы записи отслеживающего
                    participationService.delete(userSession.user.id!!,eventid,1)
                    call.respondText(Parts.takePart(value), ContentType.Text.Html)

                }
            }
        }

        get("/archive"){
            val eventlist = eventService.readAllArchive()
            eventlist.sortByDescending { it.eventdate }
            val citylist=cityService.readAll()
            val eventcategorylist=eventCategoryService.readAll()
            val eventtypelist=eventTypeService.readAll()

            val userSession = call.sessions.get<UserSession>()
            call.respondText ( Parts.pageHeadArchive()
                    + Parts.pageHeader(userSession)
                    +Parts.mainArchive(eventlist,citylist,eventcategorylist,eventtypelist)
                    +Parts.pageFooter()
                    +Parts.pageScriptAxios()
                    +Parts.pageScriptArchive(),
                ContentType.Text.Html )
        }

        get("/vacancies"){
            val userSession = call.sessions.get<UserSession>()
            call.respondText ( Parts.pageHeadVacancies()
                    +Parts.pageHeader(userSession)
                    +Parts.mainVacancies()
                    +Parts.pageFooter(),
                ContentType.Text.Html )
        }

        get("/registration"){
            call.respondText ( Parts.pageHeadRegistration()
                    + Parts.pageHeaderLogin()
                    +Parts.mainRegistrationIndividual()
                    +Parts.pageFooter(),
                ContentType.Text.Html )
        }
        post("/registration"){
            var reg = RegisterReceive()
            call.receiveParameters().entries().map { entry ->
                when(entry.key){
                    "secondname" -> reg.secondname = entry.value[0]
                    "firstname" -> reg.firstname = entry.value[0]
                    "fathername" -> reg.fathername = entry.value[0]
                    "email" -> reg.email = entry.value[0]
                    "password" -> reg.password = entry.value[0]
                    "passwordConfirm" -> reg.passwordConfirm = entry.value[0]
                }
            }
            val registerController = RegisterController(call,reg)
            registerController.registerNewUser("individual")
            call.respondRedirect("/", permanent = true)
        }

        get("/registrationjur"){
            call.respondText ( Parts.pageHeadRegistration()
                    + Parts.pageHeaderLogin()
                    +Parts.mainRegistrationJuridical()
                    +Parts.pageFooter(),
                ContentType.Text.Html )
        }
        post("/registrationjur"){
            val formParameters=call.receiveParameters()
            val reg=RegisterReceive(
                formParameters["ITN"].toString(),
                formParameters["orgname"].toString(),
                formParameters["email"].toString(),
                formParameters["password"].toString(),
                formParameters["passwordConfirm"].toString(),
            )
            val registerController = RegisterController(call,reg)
            registerController.registerNewUser("juridical")
            call.respondRedirect("/", permanent = true)

        }

         authenticate("auth-form") {
             post("/login") {
                 val userName = call.principal<UserIdPrincipal>()?.name.toString()
                 val userService=UserService(DbConnection.database)
                 val user = userService.fetch(userName)
                 if(user!=null) {
                     call.sessions.set(UserSession(user))
                     //println("СОЗДАНА СЕССИЯ НА ПОЧТУ " + (call.sessions.get<UserSession>()?.user?.email))
                     when(user.role){
                         UserRoles.individual-> call.respondRedirect("/profile-individual")
                         UserRoles.juridical-> call.respondRedirect("/profile-juridical")
                         UserRoles.moder-> call.respondRedirect("/profile-moder")
                         UserRoles.admin-> call.respondRedirect("/profile-admin")
                     }
                 }
                 //call.respondText("Hello, ${call.principal<UserIdPrincipal>()?.name}+${call.sessions.get<UserSession>()?.name}!")
            }
         }

        get("/login") {
            call.respondText ( Parts.pageHeadLogin()
                    + Parts.pageHeaderLogin()
                    +Parts.mainLogin()
                    +Parts.pageFooter(),
                ContentType.Text.Html )
         }

        get("/logout") {
            call.sessions.clear<UserSession>()
            call.respondRedirect("/login")
        }

        get("/profile-individual"){
            val userSession = call.sessions.get<UserSession>()
            if (userSession==null) call.respondRedirect("/login")
            else{
                val user= userSession.user
                val organizationService=OrganizationService(DbConnection.database)
                val organization=organizationService.read(user.org_id?:1)
                //if(userSession!=null)
                call.respondText ( Parts.pageHeadProfile()
                        + Parts.pageHeader(userSession)
                        +Parts.mainProfileIndividual(user,organization!!)
                        +Parts.pageFooter()
                        +Parts.pageScriptAxios()
                        +Parts.pageScriptProfileIndividual(),
                    ContentType.Text.Html )
                //call.respondText("Hello, ${userSession.user.email}!")
            }

        }
        get("/profile-individual-events"){
             val filter = FilterReceiveProfileEvents(
                 call.request.queryParameters["userEmail"]!!,
                 call.request.queryParameters["participationtype"],
             )
             println("Фильтры:"+filter.toString())
             val user = userService.fetchEmail(filter.userEmail)
             val paticipationlist=participationService.readAllEqType(user!!.id!!,filter)
             val eventlist = eventService.readAllDuring()
             eventlist.clear()
             paticipationlist.forEach {
                 val event = eventService.readDuring(it)
                 if(event!=null)
                     eventlist.add(event)
             }
             if(eventlist.isNotEmpty()) {
                 eventlist.sortBy { it.eventdate }
                 call.respondText(Parts.filtredEvents(eventlist), ContentType.Text.Html)
             }
             else {
                 call.respondText("Подходящих мероприятий не найдено")
             }
         }

        get("/profile-juridical-events"){
             val user = userService.fetchEmail(call.request.queryParameters["userEmail"]!!)
             val organization=organizationService.read(user!!.org_id!!)
             val organizedeventlist=userService.readEventsForOrganizer(organization!!.orgname)
             val eventlist = eventService.readAllDuring()
             eventlist.clear()
             organizedeventlist.forEach {
                 val event = eventService.readDuring(it)
                 if(event!=null)
                     eventlist.add(event)
             }
             if(eventlist.isNotEmpty()) {
                 eventlist.sortBy { it.eventdate }
                 call.respondText(Parts.filtredEvents(eventlist), ContentType.Text.Html)
             }
             else {
                 call.respondText("Подходящих мероприятий не найдено")
             }
         }

        get("/profile-eventsrequests"){
            val eventlist = eventService.readAllRequest()
            if(eventlist.isNotEmpty()) {
                eventlist.sortBy { it.eventdate }
                call.respondText(Parts.filtredEvents(eventlist), ContentType.Text.Html)
            }
            else {
                call.respondText("Текущих заявок нет")
            }
        }
        get("/profile-juridical-eventsrequests"){
            val user = userService.fetchEmail(call.request.queryParameters["userEmail"]!!)
            val organization=organizationService.read(user!!.org_id!!)
            val organizedeventlist=userService.readEventsForOrganizer(organization!!.orgname)
            val eventlist = eventService.readAllDuring()
            eventlist.clear()
            organizedeventlist.forEach {
                val event = eventService.readRequest(it)
                if(event!=null)
                    eventlist.add(event)
            }
            if(eventlist.isNotEmpty()) {
                eventlist.sortBy { it.eventdate }
                call.respondText(Parts.filtredEvents(eventlist), ContentType.Text.Html)
            }
            else {
                call.respondText("Текущих заявок нет")
            }
        }

        get("/profile-individualpastevents"){
             val user = userService.fetchEmail( call.request.queryParameters["userEmail"]!!)
             val paticipationlist=participationService.readAllEqUser(user!!.id!!)
             val eventlist = eventService.readAllDuring()
             eventlist.clear()
             paticipationlist.forEach {
                 val event = eventService.readPast(it)
                 if(event!=null)
                 eventlist.add(event)
             }
             if(eventlist.isNotEmpty()) {
                 eventlist.sortByDescending { it.eventdate }
                 call.respondText(Parts.filtredEvents(eventlist), ContentType.Text.Html)
             }
             else {
                 call.respondText("Подходящих мероприятий не найдено")
             }
         }

        get("/profile-juridicalpastevents"){
             val user = userService.fetchEmail( call.request.queryParameters["userEmail"]!!)
             val paticipationlist=participationService.readAllEqUser(user!!.id!!)
             val eventlist = eventService.readAllDuring()
             eventlist.clear()
             paticipationlist.forEach {
                 val event = eventService.readPast(it)
                 if(event!=null)
                     eventlist.add(event)
             }

             val organization=organizationService.read(user!!.org_id!!)
             val organizedeventlist=userService.readEventsForOrganizer(organization!!.orgname)
             organizedeventlist.forEach {
                 val event = eventService.readPast(it)
                 if(event!=null)
                     eventlist.add(event)
             }
             if(eventlist.isNotEmpty()) {
                 eventlist.sortByDescending { it.eventdate }
                 call.respondText(Parts.filtredEvents(eventlist), ContentType.Text.Html)
             }
             else {
                 call.respondText("Подходящих мероприятий не найдено")
             }
         }

        get("/profile-juridical") {
             val userSession = call.sessions.get<UserSession>()
             if (userSession == null) call.respondRedirect("/login")
             else {
                 val user = userSession.user
                 val organizationService = OrganizationService(DbConnection.database)
                 val organization = organizationService.read(user.org_id ?: 1)
                 //if(userSession!=null)
                 call.respondText(
                     Parts.pageHeadProfile()
                             + Parts.pageHeader(userSession)
                             + Parts.mainProfileJuridical(user, organization!!)
                             + Parts.pageFooter()
                             + Parts.pageScriptAxios()
                             + Parts.pageScriptModal()
                             + Parts.pageScriptProfileJuridical(),
                     ContentType.Text.Html
                 )
                 //call.respondText("Hello, ${userSession.user.email}!")
             }
         }

        get("/profile-admin"){
            val userSession = call.sessions.get<UserSession>()
            if (userSession==null) call.respondRedirect("/login")
            else{
                val user= userSession.user
                call.respondText ( Parts.pageHeadProfile()
                        + Parts.pageHeader(userSession)
                        +Parts.mainProfileAdmin(user)
                        +Parts.pageFooter()
                        +Parts.pageScriptAxios()
                        +Parts.pageScriptProfileAdmin(),
                    ContentType.Text.Html )
                //call.respondText("Hello, ${userSession.user.email}!")
            }

        }

        authenticate("auth-session") {

            get("/event-new") {
                 val userSession = call.sessions.get<UserSession>()

                 if (userSession == null) call.respondRedirect("/login")
                 else {
                     if(userSession.user.role==UserRoles.individual) call.respondText("Данная страница не доступна для физических лиц")
                     else {
                         val placelist = placeService.readAll()
                         val citylist = cityService.readAll()
                         val eventcategorylist=eventCategoryService.readAll()
                         val eventtypelist=eventTypeService.readAll()
                         val organization = organizationService.read(userSession.user.org_id!!)
                         call.respondText(
                             Parts.pageHeadCreateNew()
                                     + Parts.pageHeader(userSession)
                                     + Parts.mainCreateNew(userSession.user,organization!!, placelist, citylist,eventcategorylist,eventtypelist)
                                     + Parts.pageFooter()
                                     + Parts.pageScriptAxios()
                                     + Parts.pageScriptCreateNew(),
                             ContentType.Text.Html
                         )
                     }
                 }
             }

            post("/event-new") {
                val formParameters=call.receiveParameters()
                println(formParameters.toString())
                val organization=organizationService.fetch(formParameters["orgname"].toString())
                val organizer=userService.readUserForOrganization(organization!!.id!!)
                val categoryid=eventCategoryService.fetch(formParameters["category"].toString()).id
                val typeid=eventTypeService.fetch(formParameters["type"].toString()).id
                val placeid=placeService.fetch(formParameters["place"].toString().substringBefore(",")).id
                val file=formParameters["img"]
                var formatid=1
                if(formParameters["format"]=="on") formatid=2

                val event = Event(
                    name=formParameters["name"].toString(),
                    description = formParameters["description"].toString(),
                    eventdate = formParameters["eventdate"].toString().toInstantDateTime()!!,
                    formatid = formatid,
                    typeid = typeid,
                    categoryid = categoryid,
                    registration =  formParameters["reg"].toBoolean(),
                    regdate = formParameters["regdate"].toString().toInstantDateTime(),
                    organizerid = organizer.id!!,
                    placeid=placeid,
                    imgpath = "",
                    report = "",
                    approved = false
                )
                println(event)
                eventService.create(event)
                val userSession = call.sessions.get<UserSession>()
                when(userSession!!.user.role){
                    UserRoles.juridical-> call.respondRedirect("/profile-juridical")
                    UserRoles.moder-> call.respondRedirect("/profile-moder")
                    UserRoles.admin-> call.respondRedirect("/profile-admin")
                }
            }
         }

        get("/events/{id}/approve"){
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val event = eventService.readEvent(id)
            if(event!=null){
                if(event.approved) call.respondText { "Мероприятие не найдено" }
                else{
                    eventService.approve(id,event)
                    call.respondRedirect("/events/$id")
                }
            }

        }

        get<Articles> { article ->
            // Get all articles ...
            call.respond("List of articles sorted starting from ${article.sort}")
        }
    }
}
@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
