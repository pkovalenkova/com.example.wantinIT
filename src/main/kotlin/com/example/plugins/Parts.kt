package com.example.plugins

import com.example.UserSession
import com.sun.org.apache.xpath.internal.operations.Bool
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import java.time.Instant

object Parts {

    fun pageHead()  = createHTML().head() {
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )
        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")

    }

    fun pageHeadMain()  = createHTML().head(){
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )
        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "css/mainpagestyle.css", type = "text/css")

        title("Хочу в IT")
    }

    fun pageHeadVacancies()  = createHTML().head(){
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )
        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "css/mainpagestyle.css", type = "text/css")

        title("Вакансии")
    }

    fun pageHeadEvents()  = createHTML().head() {
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )
        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "css/eventcatalogstyle.css", type = "text/css")

        title("Календарь мероприятий")
    }

    fun pageHeadEvent(eventname: String)  = createHTML().head(){
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")

        link(rel = "stylesheet", href = "../css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "../css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "../css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "../css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "../css/eventpagestyle.css", type = "text/css")
        link(rel = "stylesheet", href = "../css/itc-slider.css", type = "text/css")

        title("${eventname}")
    }

    fun pageHeadArchive()  = createHTML().head(){
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )

        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "css/eventcatalogstyle.css", type = "text/css")

        title("Архив")
    }

    fun pageHeadRegistration()  = createHTML().head(){
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )

        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "css/regstyle.css", type = "text/css")

        title("Регистрация")
    }

    fun pageHeadLogin()  = createHTML().head(){
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )

        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "css/regstyle.css", type = "text/css")

        title("Авторизация")
    }

    fun pageHeadProfile()  = createHTML().head(){
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )

        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "css/profilestyle.css", type = "text/css")
        link(rel = "stylesheet", href = "css/hystmodal.css", type = "text/css")

        title("Профиль")
    }

    fun pageHeadCreateNew()  = createHTML().head() {
        meta(name = "viewport",content="width=device-width, initial-scale=1.0" )
        link(rel = "stylesheet", href = "css/bootstrap-reboot.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/bootstrap-grid.min.css", type = "text/css")
        link(rel = "stylesheet", href = "css/fonts/Montserrat.css", type = "text/css")
        link(rel = "stylesheet", href = "css/style.css", type = "text/css")
        link(rel = "stylesheet", href = "css/eventnewstyle.css", type = "text/css")

        title("Создать новое мероприятие")
    }

    fun pageHeader(userSession: UserSession?)  = createHTML().header(classes = "header") {
        div(classes = "header__left") {
            a("/") {
                img(classes = "header__logo", alt = "Логотип", src = "static/logo2.svg")
            }
        }
        div(classes = "header__right") {
            nav(classes = "header__menu") {
                ul {
                    li {
                        a("/events") {
                            +"Календарь мероприятий"
                        }
                    }
                    li {
                        a("/archive") {
                            +"Архив"
                        }
                    }
                    li {
                        a("/vacancies") {
                            +"Вакансии"
                        }
                    }
                }
            }
            a() {
                img(
                    classes = "header__notify",
                    alt = "Уведомление",
                    src = "static/notification-bell.svg"
                )
            }
            if (userSession!=null){
                a(href = "/profile-${userSession.user.role}") {
                    img(
                        classes = "header__notify",
                        alt = "Профиль",
                        src = "static/profile.svg"
                    )
                }
                a(href = "/logout") {
                    img(
                        classes = "header__notify",
                        alt = "Профиль",
                        src = "static/logout.svg"
                    )
                }
            }
            else {
                a("/login", classes = "header__login") {
                    span {
                        +"Войти"
                    }
                }
            }
        }
    }

    fun pageHeaderLogin()  = createHTML().header(classes = "header") {
        div(classes = "header__left") {
            a("/") {
                img(classes = "header__logo", alt = "Логотип", src = "static/logo2.svg")
            }
        }
        div(classes = "header__right") {
            nav(classes = "header__menu") {
                ul {
                    li {
                        a("/events") {
                            +"Календарь мероприятий"
                        }
                    }
                    li {
                        a("/archive") {
                            +"Архив"
                        }
                    }
                    li {
                        a("/vacancies") {
                            +"Вакансии"
                        }
                    }
                }
            }
            a() {
                img(
                    classes = "header__notify",
                    alt = "Уведомление",
                    src = "static/notification-bell.svg"
                )
            }
            a("/login", classes = "header__login") {
                span {
                    +"Войти"
                }
            }
        }
    }

    fun pageHeaderDown(userSession: UserSession?)  = createHTML().header(classes = "header") {
        div(classes = "header__left") {
            a("/") {
                img(classes = "header__logo", alt = "Логотип", src = "../static/logo2.svg")
            }
        }
        div(classes = "header__right") {
            nav(classes = "header__menu") {
                ul {
                    li {
                        a("/events") {
                            +"Календарь мероприятий"
                        }
                    }
                    li {
                        a("/archive") {
                            +"Архив"
                        }
                    }
                    li {
                        a("/vacancies") {
                            +"Вакансии"
                        }
                    }
                }
            }
            a() {
                img(
                    classes = "header__notify",
                    alt = "Уведомление",
                    src = "../static/notification-bell.svg"
                )
            }
            if (userSession!=null){
                a(href = "/profile-${userSession.user.role}") {
                    img(
                        classes = "header__notify",
                        alt = "Профиль",
                        src = "../static/profile.svg"
                    )
                }
                a(href = "/logout") {
                    img(
                        classes = "header__notify",
                        alt = "Профиль",
                        src = "../static/logout.svg"
                    )
                }
            }
            else {
                a("/login", classes = "header__login") {
                    span {
                        +"Войти"
                    }
                }
            }
        }
    }

    fun mainMain(userSession: UserSession?)  = createHTML().main(classes="main") {
        div(classes="img__area"){
            //img(src="static/main.png", alt="", classes = "img__area-background")
            div(classes="container"){
                div(classes="portaldescription"){
                    img(src="static/logomain.svg", alt="", classes="portaldescription__title")
                    div(classes="portaldescription__text"){
                        span{
                            +"платформа для систематизации информации о мероприятиях в Хабаровском крае, способствующих профессиональной ориентации и трудоустройству, направленная на формирование сообщества специалистов"
                        }
                    }
                }
            }
        }
        if(userSession==null) {
            div(classes = "container") {
                div(classes = "button__area") {
                    a(classes = "button__area-button", href = "/login") {
                        +"Войти"
                    }
                    a(classes = "button__area-button", href = "/registration") {
                        +"Зарегистрироваться"
                    }
                }
            }
        }
    }

    fun mainProfileAdmin(user:User)  = createHTML().main(classes="main") {
        div(classes = "container") {
            div(classes = "profile__header") {
                div(classes = "profile__header-left") {
                    if(user.imgpath.isNullOrEmpty()){
                        img(src = "../images/users/default.jpg", classes = "profile__header-img")
                    }
                    else
                        img(src = "../images/users/${user.imgpath}", classes = "profile__header-img")

                    div(classes="profile__header-left-imageupload"){
                        label(classes="profile__header-left-imageupload-label"){
                            id="imguploadLabel"
                            img(src = "../static/camera.svg")
                            {
                                input(type=InputType.file) {
                                    id="imginput"
                                }
                            }
                        }
                    }
                }
                div(classes = "profile__header-text") {
                    div(classes = "profile__header-text-title") {
                        span {
                            +"${user.secondname} ${user.firstname} ${user.fathername?:""}"
                        }
                    }
                    div(classes = "profile__header-text-role"){
                        span{
                            when(user.role){
                                UserRoles.moder->+"Модератор"
                                UserRoles.admin->+"Администратор"
                            }
                        }
                    }
                    div(classes = "profile__header-text-item") {
                        img(src = "static/email.svg")
                        div(classes = "profile__header-text-item-text") {
                            id="userEmail"
                            +user.email
                        }
                    }
                }
            }

            div(classes="profile__events"){
                div(classes = "profile__events-title") {
                    span { +"События на рассмотрении" }
                }
                div(classes="profile__events-area"){
                    div(classes="card__area") {
                        div(classes = "card__wrapper") {
                            id = "cardWrapper"
                        }
                    }
                }
            }

            if(user.role==UserRoles.admin) {
                div(classes = "profile__createmoder-area") {
                    div(classes = "card__area") {
                        div() {
                            style = "display:flex; margin-bottom:20px;"
                            a("/event-new", classes = "header__login") {
                                span {
                                    +"Создать мероприятие"
                                }
                            }
                        }
                        div() {
                            style = "display:flex;"
                            a("/event-new", classes = "header__login") {
                                span {
                                    +"Создать учетную запись модератора"
                                }
                            }
                        }
                        div(classes = "card__wrapper") {
                            id = "moderList"
                            style="display:none;"
                        }
                    }
                }
            }
        }
    }

    fun mainProfileIndividual(user:User, org:Organization)  = createHTML().main(classes="main") {
        div(classes = "container") {
            div(classes = "profile__header") {
                div(classes = "profile__header-left") {
                    if(user.imgpath.isNullOrEmpty()){
                        img(src = "../images/users/default.jpg", classes = "profile__header-img")
                    }
                    else
                    img(src = "../images/users/${user.imgpath}", classes = "profile__header-img")

                    div(classes="profile__header-left-imageupload"){
                        label(classes="profile__header-left-imageupload-label"){
                            id="imguploadLabel"
                            img(src = "../static/camera.svg")
                            {
                                input(type=InputType.file) {
                                    id="imginput"
                                }
                            }
                        }
                    }
                }
                div(classes = "profile__header-text") {
                    div(classes = "profile__header-text-title") {
                        span {
                            +"${user.secondname} ${user.firstname} ${user.fathername?:""}"
                        }
                    }
                    div(classes = "profile__header-text-role"){
                        span{
                            +"Физическое лицо"
                        }
                    }
                    div(classes = "profile__header-text-item") {
                        img(src = "static/email.svg")
                        div(classes = "profile__header-text-item-text") {
                            id="userEmail"
                            +user.email
                        }
                    }
                        div(classes = "profile__header-text-item") {
                            img(src = "static/organization.svg")
                            div(classes = "profile__header-text-item-text") {
                                if(org.id!=1) span{+org.orgname}
                                label(classes="profile__header-text-item-text-label"){
                                    id="orgnameChangeLable"
                                    input(type=InputType.checkBox){
                                        id="orgnameChange"
                                        onClick="orgnameChangeClick()"
                                    }
                                    img(src="../static/pencil.svg")
                                }
                            }
                        }
                    div(classes = "profile__header-text-item") {
                        img(src = "static/call.svg")
                        div(classes = "profile__header-text-item-text") {
                            +(user.phone?:"")
                            label(classes="profile__header-text-item-text-label"){
                                id="phoneChangeLable"
                                input(type=InputType.checkBox){
                                    id="phoneChange"
                                    onClick="phoneChangeClick()"
                                }
                                img(src="../static/pencil.svg")
                            }
                        }
                    }
                    div(classes = "profile__header-text-item") {
                        div(classes = "profile__header-text-item-text") {
                            style="margin-left: 0px"
                            +"ИНН: "
                        }
                        div(classes = "profile__header-text-low") {
                            +(user.itn?:"")
                            label(classes="profile__header-text-item-text-label"){
                                id="itnChangeLable"
                                input(type=InputType.checkBox){
                                    id="itnChange"
                                    onClick="itnChangeClick()"
                                }
                                img(src="../static/pencil.svg")
                            }
                        }

                    }
                }
            }

            div(classes="profile__events"){
                div(classes = "profile__events-title") {
                    span { +"Мероприятия" }
                }
                div(classes="profile__events-area"){
                    div(classes = "profile__events-checkbox-wrapper"){
                        label(classes = "profile__events-checkbox-label") {
                            id = "favFilterLabel"
                            input(
                                classes = "profile__events-checkbox",
                                type = InputType.checkBox,
                                name = "favFilter"
                            ) {
                                onClick = "favCheckboxClicked()"
                                id = "favFilter"
                            }
                            span { +"Отслеживаемые" }
                        }
                        label(classes = "profile__events-checkbox-label") {
                            id = "participantFilterLabel"
                            input(
                                classes = "profile__events-checkbox",
                                type = InputType.checkBox,
                                name = "participantFilter"
                            ) {
                                onClick = "participantCheckboxClicked()"
                                id = "participantFilter"
                            }
                            span { +"Участник" }
                        }
                        label(classes = "profile__events-checkbox-label") {
                            id = "spickerFilterLabel"
                            input(
                                classes = "profile__events-checkbox",
                                type = InputType.checkBox,
                                name = "spickerFilter"
                            ) {
                                onClick = "spickerCheckboxClicked()"
                                id = "spickerFilter"
                            }
                            span { +"Спикер" }
                        }
                        label(classes = "profile__events-checkbox-label") {
                            id = "pastFilterLabel"
                            input(
                                classes = "profile__events-checkbox",
                                type = InputType.checkBox,
                                name = "pastFilter"
                            ) {
                                onClick = "pastCheckboxClicked()"
                                id = "pastFilter"
                            }
                            span { +"Прошедшие" }
                        }
                    }
                    div(classes="card__area") {
                        div(classes = "card__wrapper") {
                            id = "cardWrapper"
                        }
                    }
                }
            }
        }
    }

    fun mainProfileJuridical(user:User, org:Organization)  = createHTML().main(classes="main") {
        div(classes = "container") {
            div(classes = "profile__header") {
                div(classes = "profile__header-left") {
                    if(user.imgpath.isNullOrEmpty()){
                        img(src = "../images/users/default.jpg", classes = "profile__header-img")
                    }
                    else
                    img(src = "../images/users/${user.imgpath}", classes = "profile__header-img")
                    div(classes="profile__header-left-imageupload"){
                        label(classes="profile__header-left-imageupload-label"){
                            id="imguploadLabel"
                            img(src = "../static/camera.svg")
                            {
                                input(type=InputType.file) {
                                    id="imginput"
                                }
                            }
                        }
                    }
                }
                div(classes = "profile__header-text") {
                    div(classes = "profile__header-text-title") {
                        span {
                            +org.orgname
                        }
                    }
                    div(classes = "profile__header-text-role"){
                        span{
                            +"Юридическое лицо"
                        }
                    }
                    div(classes = "profile__header-text-item") {
                        img(src = "static/representative.svg")
                        div(classes = "profile__header-text-item-text") {
                            +"${user.secondname} ${user.firstname} ${user.fathername?:""}"
                            label(classes="profile__header-text-item-text-label"){
                                id="fioChangeLable"
                                input(type=InputType.checkBox){
                                    id="fioChange"
                                    onClick="fioChangeClick()"
                                }
                                img(src="../static/pencil.svg")
                            }
                        }

                    }
                    div(classes = "profile__header-text-item") {
                        img(src = "static/email.svg")
                        div(classes = "profile__header-text-item-text") {
                            id="userEmail"
                            +user.email
                        }
                    }
                        div(classes = "profile__header-text-item") {
                            img(src = "static/web.svg")
                            div(classes = "profile__header-text-item-text") {
                                a(href="${org.site}"){
                                    +"${org.site?:""}"
                                }
                                label(classes="profile__header-text-item-text-label"){
                                    id="siteChangeLable"
                                    input(type=InputType.checkBox){
                                        id="siteChange"
                                        onClick="siteChangeClick()"
                                    }
                                    img(src="../static/pencil.svg")
                                }
                            }

                        }
                    div(classes = "profile__header-text-item") {
                        img(src = "static/call.svg")
                        div(classes = "profile__header-text-item-text") {
                            +(user.phone?:"")
                            label(classes="profile__header-text-item-text-label"){
                                id="phoneChangeLable"
                                input(type=InputType.checkBox){
                                    id="phoneChange"
                                    onClick="phoneChangeClick()"
                                }
                                img(src="../static/pencil.svg")
                            }
                        }
                    }
                    div(classes = "profile__header-text-item") {
                        div(classes = "profile__header-text-item-text") {
                            style="margin-left: 0px"
                            +"ИНН: "
                        }
                        div(classes = "profile__header-text-low") {
                            +(user.itn?:"")
                            label(classes="profile__header-text-item-text-label"){
                                id="itnChangeLable"
                                input(type=InputType.checkBox){
                                    id="itnChange"
                                    onClick="itnChangeClick()"
                                }
                                img(src="../static/pencil.svg")
                            }
                        }
                    }
                }
            }

            div(classes="profile__events"){
                div(classes = "profile__events-title") {
                    span { +"Мероприятия" }
                }
                div(classes="profile__events-area"){
                    div(classes = "profile__events-checkbox-wrapper"){
                        label(classes = "profile__events-checkbox-label") {
                            id = "organizerFilterLabel"
                            input(
                                classes = "profile__events-checkbox",
                                type = InputType.checkBox,
                                name = "organizerFilter"
                            ) {
                                onClick = "organizerCheckboxClicked(this)"
                                id = "organizerFilter"
                                value="true"
                            }
                            span { +"Организатор" }
                        }
                        label(classes = "profile__events-checkbox-label") {
                            id = "partnerFilterLabel"
                            input(
                                classes = "profile__events-checkbox",
                                type = InputType.checkBox,
                                name = "partnerFilter"
                            ) {
                                onClick = "partnerCheckboxClicked(this)"
                                id = "partnerFilter"
                            }
                            span { +"Партнер" }
                        }
                        label(classes = "profile__events-checkbox-label") {
                            id = "pastFilterLabel"
                            input(
                                classes = "profile__events-checkbox",
                                type = InputType.checkBox,
                                name = "pastFilter"
                            ) {
                                onClick = "pastCheckboxClicked(this)"
                                id = "pastFilter"
                            }
                            span { +"Прошедшие" }
                        }
                        }
                    div(classes="card__area") {
                        div(classes = "card__wrapper") {
                            id = "cardWrapper"

                        }
                    }
                    }
            }

            div(classes="profile__createevent"){
                div(classes = "profile__createevent-title") {
                    span { +"Мероприятия на рассмотрении" }
                }
                div(classes="profile__createevent-area"){
                    div(classes="card__area") {
                        div(){
                            style="display:flex;"
                            a("/event-new", classes = "header__login") {
                                attributes["data-hystmodal"] = "#myModal"
                                span {
                                    +"Создать мероприятие"
                                }
                            }
                        }
                        div(classes = "card__wrapper") {
                            id = "eventRequestWrapper"
                            +"Текущих заявок нет"
                        }
                    }
                }
            }

            /*div(classes = "hystmodal"){
                id="myModal"
                attributes["aria-hidden"] = "true"
                div(classes="hystmodal__wrap"){
                    div(classes="hystmodal__window"){
                        role="dialog"
                        attributes["aria-modal"] = "true"
                        button(classes="hystmodal__close"){
                            attributes["data-hystclose"] = ""
                            +"Закрыть"
                        }
                    }
                    //

                }

            }*/

            div(classes="profile__partner-request"){
                div(classes = "profile__partner-request-title") {
                    span { +"Заявки на партнерство" }
                }
                div(classes="profile__createevent-area"){
                    div(classes="card__area") {
                        div(classes = "profile__events-checkbox-wrapper"){
                            label(classes = "profile__events-checkbox-label") {
                                id = "incomingFilterLabel"
                                input(
                                    classes = "profile__events-checkbox",
                                    type = InputType.checkBox,
                                    name = "incomingFilter"
                                ) {
                                    onClick = "incomingCheckboxClicked()"
                                    id = "incomingFilter"
                                }
                                span { +"Входящие" }
                            }
                            label(classes = "profile__events-checkbox-label") {
                                id = "outgoingFilterLabel"
                                input(
                                    classes = "profile__events-checkbox",
                                    type = InputType.checkBox,
                                    name = "outgoingFilter"
                                ) {
                                    onClick = "outgoingCheckboxClicked()"
                                    id = "outgoingFilter"
                                }
                                span { +"Исходящие" }
                            }
                        }
                        div(classes = "card__wrapper") {
                            id = "partnerRequestWrapper"
                            +"Текущих заявок нет"
                        }
                    }
                }
            }



        }
    }

    fun mainCreateNew(user:User,organization:Organization,placelist:MutableList<Place>,citylist: MutableList<City>,eventcategorylist:MutableList<EventCategory>,eventtypelist:MutableList<EventType>)=createHTML().main(classes="main") {
        div(classes = "container") {
            div(classes = "newevent__title") {
                +"Создание мероприятия"
            }
            form(
                action = "/event-new",
                encType = FormEncType.applicationXWwwFormUrlEncoded,
                method = FormMethod.post,
                classes = "form__example"
            ) {
                div(classes="form__item") { label(classes = "form__name") { +"Организатор*" }
                    input(type = InputType.text, name = "orgname", classes = "form__example-input") {
                        style="width:600px;"
                        if(organization.id!=1) this.value=organization.orgname
                    }
                }
                div(classes="form__item") { label(classes = "form__name") { +"Название*" }
                    input(type = InputType.text, name = "name", classes = "form__example-input") {
                        style="width:600px;"
                    }
                }

                div(classes="form__item") { label(classes = "form__name") { +"Описание" }
                    textArea(classes = "form__description") {
                        name = "description"
                    }
                }

                div(classes="form__item") { label(classes = "form__name") { +"Добавьте обложку" }
                    input(type = InputType.file, name = "img", classes = "form__example-input") {
                    }
                }
                div(classes="form__item") { label(classes = "form__name") { +"Онлайн"
                    input(type = InputType.checkBox, name = "format", classes = "form__example-input") {
                        id = "reg"
                        style="width:20px;"
                    }
                }
                }
                div(classes="form__item") { label(classes = "form__name") { +"Тип*" }
                    input(type = InputType.text, name = "type"){
                        style="display:none;"
                        id="typeInput"
                    }
                    select(classes = "main__filters-select") {
                        onChange="typeChange(this)"
                        option { +"Выберите тип" }
                        eventtypelist.forEach { option { +it.eventtype } }
                    }
                }
                div(classes="form__item") { label(classes = "form__name") { +"Категория*" }
                    input(type = InputType.text, name = "category"){
                        style="display:none;"
                        id="categoryInput"
                    }
                    select(classes = "main__filters-select") {
                        onChange="categoryChange(this)"
                        option { +"Выберите категорию" }
                        eventcategorylist.forEach { option { +it.category } }
                    }
                }

                div(classes="form__item") { label(classes = "form__name") { +"Дата и время проведения*" }
                    input(type = InputType.dateTimeLocal, name = "eventdate", classes = "form__example-input") {
                        style="width:160px;"
                        min=Instant.now().plusSeconds(86400).parseDateForDateTimeInput()
                    }
                }

                div(classes="form__item") { label(classes = "form__name") { +"Место проведения*" }
                    input(type = InputType.text, name = "place"){
                        style="display:none;"
                        id="placeInput"
                    }
                    select(classes = "main__filters-select") {
                        onChange="placeChange(this)"
                        option { +"Выберите место проведения" }
                        placelist.forEach { p ->
                            var placename = p.name
                            citylist.forEach {
                                if (it.id == p.cityid) placename += ", ${it.city}"
                            }
                            option { +placename }
                        }
                    }
                }

                div(classes="form__item") { label(classes = "form__name") { +"Нужна ли регистрация"
                    input(type = InputType.checkBox, name = "reg", classes = "form__example-input") {
                        id = "reg"
                        onClick = "regClick(this)"
                        style="width:20px;"
                    }
                }
                }

                div(classes="form__item") {
                    id = "regdate"
                    label(classes = "form__name") { +"Дата и время окончания регистрации*" }
                        input(type = InputType.dateTimeLocal, name = "regdate", classes = "form__example-input") {
                            style="width:160px;"
                            min=Instant.now().plusSeconds(86400).parseDateForDateTimeInput()
                        }
                }

                div(classes="form__item") {
                    input(type = InputType.submit, classes="form__example-button") {
                        value = "Отправить заявку"
                    }
                }
                /*div(classes="form__item") { label(classes = "form__name") { +"Дата и время окончания регистрации*" }
                    input(type = InputType.dateTimeLocal, name = "regdatetime", classes = "form__example-input") {
                    }
                }*/


            }
        }
    }

    fun mainEvents(eventlist: MutableList<FullEvent>,citylist: MutableList<City>,eventcategorylist:MutableList<EventCategory>,eventtypelist:MutableList<EventType>)  = createHTML().main(classes="main") {
        div(classes = "container"){
            div(classes = "main__search"){
                form (classes = "main__search"){
                    input(classes = "main__search-input") {
                        id="searchInput"
                        onInput="valueFilterChange()"
                    }
                    img(src="static/search.svg", alt="")
                    /*button() {
                        type=ButtonType.submit
                        img(src="static/search.svg", alt="")
                    }*/
                }
            }
            div(classes = "main__filters"){
                div(classes = "main__filters-select-wrapper"){
                    div(classes="main__filters-select-item"){
                        span{+"Город"}
                        select(classes="main__filters-select"){
                            id="cityFilter"
                            onInput="valueFilterChange()"
                            option{+"Все города"}
                            citylist.forEach {option{+it.city}}
                        }
                    }
                    div(classes="main__filters-select-item") {
                        span { +"Категория" }
                        select(classes = "main__filters-select") {
                            id = "categoryFilter"
                            onInput = "valueFilterChange()"
                            option { +"Все категории" }
                            eventcategorylist.forEach { option { +it.category } }
                        }
                    }

                    div(classes="main__filters-select-item") {
                        span { +"Дата от" }
                        input(classes = "main__filters-select-time", type = InputType.date) {
                            id = "timeStart"
                            value=Instant.now().parseDateForInput()
                            min=Instant.now().parseDateForInput()
                            onChange="valueFilterChange()"
                        }
                    }
                    div(classes="main__filters-select-item") {
                        span { +"Дата до" }
                        input(classes = "main__filters-select-time", type = InputType.date) {
                            id = "timeEnd"
                            value=eventlist.last().eventdate.plusSeconds(86400).parseDateForInput()
                            min=Instant.now().parseDateForInput()
                            onChange="valueFilterChange()"
                        }
                    }

                    div(classes="main__filters-select-item") {
                        span { +"Вид" }
                        select(classes = "main__filters-select") {
                            id = "typeFilter"
                            onInput = "valueFilterChange()"
                            option { +"Все виды" }
                            eventtypelist.forEach { option { +it.eventtype } }
                        }
                    }
                }
                div(classes = "main__filters-checkbox-wrapper"){
                    label(classes = "main__filters-checkbox-label" ){
                        id="onlineFilterLabel"
                        input(classes = "main__filters-checkbox", type = InputType.checkBox, name="onlineFilter"){
                            onClick="onlineCheckboxClicked(this)"
                            id="onlineFilter"
                        }
                        span{+"Онлайн"}
                    }
                    label(classes = "main__filters-checkbox-label" ){
                        id="regOpenFilterLabel"
                        //for="regOpenFilter"
                        input(classes = "main__filters-checkbox", type = InputType.checkBox, name="regOpenFilter"){
                            onClick="regOpenedCheckboxClicked(this)"
                            id="regOpenFilter"
                        }
                        span{+"Открыта регистрация"}
                    }

                }
            }
            div(classes = "card__wrapper"){
                id="cardWrapper"
                eventlist.forEach {
                    a("/events/${it.id}", classes = "card") {
                        div(classes = "card__header") {
                            img(src = "images/events/${it.id}.jpg", classes = "card__img")
                        }
                        div(classes = "card__main") {

                            div(classes = "card__body") {
                                div(classes = "card__body_header") {
                                    div(classes = "card__type") {
                                        span {
                                            +it.eventtype
                                        }
                                    }
                                    div(classes = "card__type") {
                                        span {
                                            +it.category
                                        }
                                    }
                                }

                                div(classes = "card__title_area") {
                                    h2(classes = "card__title") {
                                        +it.name
                                    }
                                }
                            }
                            div(classes = "card__footer") {
                                div(classes = "card__date") {
                                    +it.eventdate.parseDateToString()
                                }
                                div(classes = "card__place") {
                                    if ((it.placename.length + it.city.length) > 30) +it.city
                                    else {
                                        +"${it.placename}, ${it.city}"
                                    }
                                }
                            }
                        }
                    }
                    //}
                }
            }
        }
    }

    fun filtredEvents(eventlist: MutableList<FullEvent>):String{
        var cardsHtml=""
        eventlist.forEach{
            cardsHtml+=createCards(it)
        }
        return cardsHtml
    }

    fun createCards(event: FullEvent)  = createHTML().a(classes = "card") {
                href="/events/${event.id}"
                div(classes = "card__header") {
                    img(src = "images/events/${event.id}.jpg", classes = "card__img")
                }
                div(classes = "card__main") {

                    div(classes = "card__body") {
                        div(classes = "card__body_header") {
                            div(classes = "card__type") {
                                span {
                                    +event.eventtype
                                }
                            }
                            div(classes = "card__type") {
                                span {
                                    +event.category
                                }
                            }
                        }

                        div(classes = "card__title_area") {
                            h2(classes = "card__title") {
                                +event.name
                            }
                        }
                    }
                    div(classes = "card__footer") {
                        div(classes = "card__date") {
                            +event.eventdate.parseDateToString()
                        }
                        div(classes = "card__place") {
                            if ((event.placename.length + event.city.length) > 30) +event.city
                            else {
                                +"${event.placename}, ${event.city}"
                            }
                        }
                    }
                }
    }

    fun mainSingleEvent(event: FullEvent,
                        speakerlist:MutableList<Speaker>,
                        partnerlist:MutableList<Partner>,
                        organizer: Organizer,
                        doclist:MutableList<String>,
                        photolist:MutableList<String>,
                        userSession:UserSession?,
                        isFollower:Boolean,
                        isParticipant:Boolean,
                        isPartner:Boolean)  = createHTML().main(classes="main"){
        div(classes = "container") {
            if(userSession!=null) {
                if ((userSession.user.id == organizer.userid) || (userSession.user.role == UserRoles.moder) || (userSession.user.role == UserRoles.admin)) {
                    div(classes="change-event"){
                        a(href="/events/${event.id}/cancel"){
                            +"Изменить"
                        }
                        img(src="../static/pencilbig.svg"){
                            style="height:25px;margin:0px 5px;"
                        }
                        if(event.eventdate.isAfter(Instant.now())) {
                            a(href = "/events/${event.id}/update") {
                                +"Отменить"
                            }
                            img(src = "../static/cancel.svg") {
                                style = "height:25px;margin:0px 5px;"
                            }
                        }

                        if(!event.approved&&((userSession.user.role == UserRoles.moder) || (userSession.user.role == UserRoles.admin))) {
                            a(href = "../events/${event.id}/approve") {
                                +"Одобрить"

                            }
                            img(src = "../static/approved.svg") {
                                style = "height:25px;margin:0px 5px;"
                            }
                        }
                    }
                }
            }
            div(classes = "event__header") {
                div(classes = "event__header-left") {
                    img(src = "../images/events/${event.id}.jpg", classes = "event__header-img")
                }
                div(classes = "event__header-text") {
                    div(classes = "event__header-text-title") {
                        span {
                            +event.name
                        }
                    }
                    div(classes = "event__type_header") {
                        div(classes = "event__type") {
                            span {
                                +event.eventtype
                            }
                        }
                        div(classes = "event__type") {
                            span {
                                +event.category
                            }
                        }
                    }
                    div(classes = "event__header-text-discription") {
                        span() {
                            +event.description
                        }
                    }
                    div(classes = "event__header-text-time") {
                        img(src = "../static/clock.svg")
                        div(classes = "event__header-text-time-date") {
                            +event.eventdate.parseDateToString()
                        }
                    }
                    div(classes = "event__header-text-time") {
                        img(src = "../static/location.svg")
                        div(classes = "event__header-text-time-date") {
                            +"${event.placename}, ${event.city}"
                        }
                    }
                }
            }

            if (event.eventdate.isAfter(Instant.now())) {
                if(userSession?.user?.role==UserRoles.individual||userSession==null) {
                    div(classes = "activities-checkbox-wrapper") {
                        div(classes = "activities-checkbox-container") {
                            label(classes = "activities-checkbox-label") {
                                id = "followLabel"
                                input(
                                    classes = "activities-checkbox",
                                    type = InputType.checkBox,
                                    name = "followCheckbox"
                                ) {
                                    onClick = "followCheckboxClicked(this)"
                                    id = "followCheckbox"
                                    this.checked=isFollower
                                    /*if(isFollower){
                                        this.checked= true
                                    }
                                    else{
                                        this.checked= false
                                    }*/
                                }
                                span { +"Отслеживать" }
                            }
                            label(classes = "activities-checkbox-icon") {
                                id = "heartLabel"
                                input(
                                    classes = "activities-checkbox",
                                    type = InputType.checkBox,
                                    name = "heartCheckbox"
                                ) {
                                    id="heartCheckbox"
                                    onClick="followCheckboxClicked(this)"
                                    if(isFollower){
                                        this.checked= true
                                        img( src = "../static/heart2.svg", alt = "") {
                                            style = "height:45px"
                                        }
                                    }else {
                                        this.checked= false
                                        img(src = "../static/heart.svg", alt = "") {
                                            style = "height:45px"
                                        }
                                    }
                                }
                            }
                        }

                        if (event.registration) {
                            div(classes = "activities-checkbox-container") {
                                label(classes = "activities-checkbox-label") {
                                    id = "takingPartLabel"
                                    input(
                                        classes = "activities-checkbox",
                                        type = InputType.checkBox,
                                        name = "takingPartCheckbox"
                                    ) {
                                        onClick = "takingPartCheckboxClicked(this)"
                                        id = "takingPartCheckbox"
                                        this.checked=isParticipant
                                        /*if(isParticipant){
                                            this.checked= true
                                        }
                                        else{
                                            this.checked= false
                                        }*/
                                    }
                                    span { +"Принять участие" }
                                }
                                label(classes = "activities-checkbox-icon") {
                                    id = "checkLabel"
                                    input(
                                        classes = "activities-checkbox",
                                        type = InputType.checkBox,
                                        name = "checkCheckbox"
                                    ) {
                                        id="checkCheckbox"
                                        onClick="takingPartCheckboxClicked(this)"
                                        if(isParticipant){
                                            this.checked=true
                                            img(src = "../static/check2.svg", alt = "") {
                                                style = "height:45px"
                                            }
                                        } else {
                                            this.checked=false
                                            img(src = "../static/check.svg", alt = "") {
                                                style = "height:45px"
                                            }
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
                if(userSession?.user?.role==UserRoles.juridical) {
                    if(userSession.user.id!=organizer.userid)
                    div(classes = "activities-checkbox-wrapper") {
                        div(classes = "activities-checkbox-container") {
                            label(classes = "activities-checkbox-label") {
                                id = "partnerLabel"
                                input(
                                    classes = "activities-checkbox",
                                    type = InputType.checkBox,
                                    name = "partnerCheckbox"
                                ) {
                                    onClick = "partnerCheckboxClicked(this)"
                                    id = "partnerCheckbox"
                                }
                                span { +"Стать партнером" }
                            }
                            label(classes = "activities-checkbox-icon") {
                                id = "checkLabel"
                                input(
                                    classes = "activities-checkbox",
                                    type = InputType.checkBox,
                                    name = "checkPartnerCheckbox"
                                ) {
                                    id="checkPartnerCheckbox"
                                    onClick="checkPartnerCheckboxClicked(this)"
                                    if(isPartner){
                                        img(src = "../static/check2.svg", alt = "") {
                                            style = "height:45px"
                                        }
                                    } else {
                                        img(src = "../static/check.svg", alt = "") {
                                            style = "height:45px"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!event.report.isNullOrEmpty()) {
                    div(classes="report__area"){
                        div(classes = "report__title") {
                            span { +"Как прошло" }
                        }
                        div(classes = "report__text") {
                            span { +event.report }
                        }
                    }

                }
            }

            if (doclist.isNotEmpty()) {
                div(classes = "files__area") {
                    div(classes = "files__area-title") {
                        span { +"Файлы мероприятия" }
                    }
                    doclist.forEach { d ->
                        div(classes = "files__area-file") {
                            img(src = "../static/file.svg")
                            a(href = "../docs/${event.id}/${d}", target = "_blank") {
                                +"${d}"
                            }
                        }
                    }
                }
            }
        }

            if(event.eventdate.isBefore(Instant.now())){
                if(photolist.isNotEmpty()) {
                    div(classes="photos__area") {
                        div(classes = "container") {
                            div(classes = "photos__area-title") {
                                span { +"Фотогалерея" }
                            }
                        }
                        div(classes = "itc-slider") {
                            attributes["data-slider"] = "itc-slider"

                            div(classes = "itc-slider-wrapper") {
                                div(classes = "itc-slider-items") {
                                    photolist.forEach { p ->
                                        println(p)
                                        div(classes = "itc-slider-item") {
                                            img(src = "../images/reports/${event.id}/${p}")
                                        }
                                    }
                                }
                            }

                            button(classes = "itc-slider-btn itc-slider-btn-prev")
                            button(classes = "itc-slider-btn itc-slider-btn-next")

                            /*ol(classes="itc-slider-indicators"){
                            photolist.forEach { p ->
                                li(classes="itc-slider-indicator",){

                                }
                            }

                        }*/
                        }
                    }
                }
            }

        div(classes = "container") {

            if(speakerlist.size!=0) {
                div(classes = "entity__title") {
                    span { +"Спикеры" }
                }

                div(classes = "entity__area") {
                    speakerlist.forEach {s->
                        div(classes = "entity__card") {
                            if(!s.imgpath.isNullOrEmpty()) {
                                style="grid-template-columns: 2fr 4fr;"
                                div(classes = "entity__card-left") {
                                    img(
                                        src = "../images/users/${s.imgpath}",
                                        alt = "avatar",
                                        classes = "entity__card-img"
                                    )
                                }
                            }
                            div(classes = "entity__card-text") {
                                span(classes = "entity__card-text-name") { +"${s.secondname} ${s.firstname} ${s.fathername} "}
                                span(classes = "entity__card-text-description") { +"студент группы БО941САП Дальневосточного государственного университета путей сообщения" }
                            }
                        }
                    }
                }
            }

            div(classes="entity__title"){
                span{+"Организатор"}
            }
            div(classes="entity__area"){
                div(classes="entity__card"){
                    if(!organizer.imgpath.isNullOrEmpty()) {
                        style="grid-template-columns: 2fr 4fr;"
                        div(classes="entity__card-left"){
                            img(src="../images/users/${organizer.imgpath}", alt="avatar",classes="entity__card-img")
                        }
                    }


                    div(classes="entity__card-text"){
                        span(classes="entity__card-text-name"){+organizer.orgname}
                        a(href="mailto:${organizer.email}"){+organizer.email}
                        if(organizer.phone!=null)
                            span(classes = "entity__card-text-description") { +organizer.phone }
                        if(organizer.site!=null)
                            a(href=organizer.site) { +organizer.site}
                    }
                }
            }

            if(partnerlist.size!=0) {
                div(classes = "entity__title") {
                    span { +"Партнеры" }
                }
                partnerlist.forEach { p->
                    div(classes = "entity__area") {
                        div(classes = "entity__card") {
                            if(!p.imgpath.isNullOrEmpty()) {
                                style = "grid-template-columns: 2fr 4fr;"
                                div(classes = "entity__card-left") {
                                    img(
                                        src = "../images/users/${p.imgpath}",
                                        alt = "avatar",
                                        classes = "entity__card-img"
                                    )
                                }
                            }
                            div(classes = "entity__card-text") {
                                span(classes = "entity__card-text-name") { +p.orgname }
                                span(classes = "entity__card-text-description") { +p.email }
                                if(p.phone!=null)
                                span(classes = "entity__card-text-description") { +p.phone }
                                if(p.site!=null)
                                span(classes = "entity__card-text-description") { +p.site }
                            }
                        }
                    }
                }
            }
        }

        div(classes="map"){
            a(href=""){

            }
            a(href=""){

            }
            iframe{
                //src="https://static.maps.2gis.com/1.0?s=880x450&c=${event.latitude},${event.longitude}&z=16&pt=${event.latitude},${event.longitude}"
                src="https://yandex.ru/map-widget/v1/?ll=${event.longitude}%2C${event.latitude}&mode=poi&poi%5Bpoint%5D=${event.longitude}%2C${event.latitude}&z=16"
            }

        }
    }

    fun becomeFollower(value:Boolean) = createHTML().input(classes = "activities-checkbox", type = InputType.checkBox, name = "heartCheckbox"){
        id="heartCheckbox"
        onClick="followCheckboxClicked(this)"
        this.checked=value
        if(value) {
            img(src = "../static/heart2.svg", alt = "") {
                style = "height:45px"
            }
        }
        else{
            img(src = "../static/heart.svg", alt = "") {
                style = "height:45px"
            }
        }
    }

    fun takePart(value:Boolean) = createHTML().input(classes = "activities-checkbox",type = InputType.checkBox, name = "checkCheckbox"){
        id="checkCheckbox"
        onClick="takingPartCheckboxClicked(this)"
        this.checked=value
        if(value){
            img(src = "../static/check2.svg", alt = "") {
                style = "height:45px"
            }
        } else {
            img(src = "../static/check.svg", alt = "") {
                style = "height:45px"
            }
        }
    }

    fun mainArchive(eventlist: MutableList<FullEvent>,citylist: MutableList<City>,eventcategorylist:MutableList<EventCategory>,eventtypelist:MutableList<EventType>)  = createHTML().main(classes="main"){
        div(classes = "container"){
                div(classes = "main__search"){
                    form (classes = "main__search"){
                        input(classes = "main__search-input") {
                            id="searchInput"
                            onInput="valueFilterChangeArchive()"
                        }
                        img(src="static/search.svg", alt="")
                    }
                }
                div(classes = "main__filters"){
                    div(classes = "main__filters-select-wrapper"){
                        div(classes="main__filters-select-item"){
                            span{+"Город"}
                            select(classes="main__filters-select"){
                                id="cityFilter"
                                onInput="valueFilterChangeArchive()"
                                option{+"Все города"}
                                citylist.forEach {option{+it.city}}
                            }
                        }
                        div(classes="main__filters-select-item") {
                            span { +"Категория" }
                            select(classes = "main__filters-select") {
                                id = "categoryFilter"
                                onInput = "valueFilterChangeArchive()"
                                option { +"Все категории" }
                                eventcategorylist.forEach { option { +it.category } }
                            }
                        }

                        div(classes="main__filters-select-item") {
                            span { +"Дата от" }
                            input(classes = "main__filters-select-time", type = InputType.date) {
                                id = "timeStart"
                                value=eventlist.last().eventdate.minusSeconds(86400).parseDateForInput()
                                onChange="valueFilterChangeArchive()"
                            }
                        }
                        div(classes="main__filters-select-item") {
                            span { +"Дата до" }
                            input(classes = "main__filters-select-time", type = InputType.date) {
                                id = "timeEnd"
                                value=Instant.now().parseDateForInput()
                                max=Instant.now().parseDateForInput()
                                onChange="valueFilterChangeArchive()"
                            }
                        }

                        div(classes="main__filters-select-item") {
                            span { +"Вид" }
                            select(classes = "main__filters-select") {
                                id = "typeFilter"
                                onInput = "valueFilterChangeArchive()"
                                option { +"Все виды" }
                                eventtypelist.forEach { option { +it.eventtype } }
                            }
                        }
                    }
                    div(classes = "main__filters-checkbox-wrapper"){
                        label(classes = "main__filters-checkbox-label" ){
                            id="onlineFilterLabel"
                            input(classes = "main__filters-checkbox", type = InputType.checkBox, name="onlineFilter"){
                                onClick="onlineCheckboxClicked(this)"
                                id="onlineFilter"
                            }
                            span{+"Онлайн"}
                        }
                    }
                }
                div(classes = "card__wrapper"){
                    id="cardWrapper"
                    eventlist.forEach {
                            a("/events/${it.id}", classes = "card") {
                                div(classes = "card__header") {
                                    img(src = "images/events/${it.id}.jpg", classes = "card__img")
                                }
                                div(classes = "card__main") {

                                    div(classes = "card__body") {
                                        div(classes = "card__body_header") {
                                            div(classes = "card__type") {
                                                span {
                                                    +it.eventtype
                                                }
                                            }
                                            div(classes = "card__type") {
                                                span {
                                                    +it.category
                                                }
                                            }
                                        }

                                        div(classes = "card__title_area") {
                                            h2(classes = "card__title") {
                                                +it.name
                                            }
                                        }
                                    }
                                    div(classes = "card__footer") {
                                        div(classes = "card__date") {
                                            +it.eventdate.parseDateToString()
                                        }
                                        div(classes = "card__place") {
                                            if ((it.placename.length + it.city.length) > 30) +it.city
                                            else {
                                                +"${it.placename}, ${it.city}"
                                            }
                                        }
                                    }
                                }
                            }
                    }
                }
            }
    }

    fun mainRegistrationIndividual()  = createHTML().main(classes="main"){
        div(classes = "container") {
                div(classes = "container__area"){
                    div(classes = "main__title") {
                        span { +"Регистрация пользователя" }
                    }
                    div(classes = "main__switch") {
                        a("/registrationjur") {
                            +"Я – юридическое лицо"
                        }
                    }
                    div(classes = "form__example") {
                        form(
                            action = "/registration",
                            encType = FormEncType.applicationXWwwFormUrlEncoded,
                            method = FormMethod.post,
                            classes = "form__example"
                        )
                        {
                            div(classes="form__example-area"){
                                div {
                                    div{label(classes="form__name") { +"Фамилия" }}
                                    input(type = InputType.text, name = "secondname",classes="form__example-input") {
                                    }
                                }
                                div {
                                    div{label(classes="form__name") { +"Имя" }}
                                    input(type = InputType.text, name = "firstname",classes="form__example-input") {
                                    }
                                }
                                div {
                                    div{label(classes="form__name") { +"Отчество" }}
                                    input(type = InputType.text, name = "fathername",classes="form__example-input") {
                                    }
                                }
                                div {
                                    div{label(classes="form__name") { +"E-mail" }}
                                    input(type = InputType.email, name = "email",classes="form__example-input") {
                                    }
                                }
                                div {
                                    div{label(classes="form__name") { +"Пароль" }}
                                    input(type = InputType.password, name = "password",classes="form__example-input") {
                                    }
                                }
                                div {
                                    div{label(classes="form__name") { +"Подтвердите пароль" }}
                                    input(type = InputType.password, name = "passwordConfirm",classes="form__example-input") {
                                    }
                                }
                            }
                            div {
                                input(type = InputType.submit, classes="form__example-button") {
                                    value = "Зарегистрироваться"
                                }
                            }


                        }
                    }
                    div(classes = "main__switch") {
                        a("/login") {
                            +"Войдите, если уже зарегестрированы"
                        }
                    }
                }

            }
    }

    fun mainRegistrationJuridical()  = createHTML().main(classes="main"){
        div(classes = "container") {
            div(classes = "container__area"){
                div(classes = "main__title") {
                    span { +"Регистрация организации" }
                }
                div(classes = "main__switch") {
                    a("/registration") {
                        +"Я – физическое лицо"
                    }
                }
                div(classes = "form__example") {
                    form(
                        action = "/registrationjur",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.post,
                        classes = "form__example"
                    )
                    {
                        div(classes="form__example-area"){
                            div {
                                div{label(classes="form__name") { +"ИНН" }}
                                input(type = InputType.text, name = "ITN",classes="form__example-input") {
                                }
                            }
                            div {
                                div{label(classes="form__name") { +"Название организации" }}
                                input(type = InputType.text, name = "orgname",classes="form__example-input") {
                                }
                            }
                            div {
                                div{label(classes="form__name") { +"E-mail" }}
                                input(type = InputType.email, name = "email",classes="form__example-input") {
                                }
                            }
                            div {
                                div{label(classes="form__name") { +"Пароль" }}
                                input(type = InputType.password, name = "password",classes="form__example-input") {
                                }
                            }
                            div {
                                div{label(classes="form__name") { +"Подтвердите пароль" }}
                                input(type = InputType.password, name = "passwordConfirm",classes="form__example-input") {
                                }
                            }
                        }
                        div {
                            input(type = InputType.submit, classes="form__example-button") {
                                value = "Зарегистрироваться"
                            }
                        }


                    }
                }
                div(classes = "main__switch") {
                    a("/login") {
                        +"Войдите, если уже зарегестрированы"
                    }
                }
            }

        }
    }

    fun mainLogin()  = createHTML().main(classes="main"){
        div(classes="container") {
                div(classes = "container__area") {
                    div(classes = "main__title") {
                        span { +"Авторизация" }
                    }
                    form(
                        action = "/login",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.post,
                        classes = "form__example"
                    ) {
                        div(classes = "form__example-area") {
                            div {
                                div { label(classes = "form__name") { +"E-mail" } }
                                input(
                                    type = InputType.text,
                                    name = "username",
                                    classes = "form__example-input"
                                ) {
                                }
                            }
                            div {
                                div { label(classes = "form__name") { +"Пароль" } }
                                input(
                                    type = InputType.password,
                                    name = "password",
                                    classes = "form__example-input"
                                ) {
                                }
                            }
                        }

                        div {
                            input(type = InputType.submit, classes = "form__example-button") {
                                value = "Войти"
                            }
                        }
                        div(classes = "main__switch") {
                            a("/registration") {
                                +"Зарегистрироваться"
                            }
                        }
                    }
                }
            }
    }

    fun mainVacancies()  = createHTML().main(classes="main"){
        div(classes="container"){
            span{
                +"Раздел находится в разработке"
            }
        }
    }

    fun pageFooter() = createHTML().footer(classes = "footer") {

        div(classes = "container"){
                div(classes = "footer__up"){
                    div(classes = "footer__up-left"){
                        div(classes = "footer__logo"){
                            img(classes="footer__logo-img", src="static/logo-footer.svg", alt="logo")
                        }
                        div(classes = "footer__socials"){
                            a(){
                                img(src="static/vk.svg")
                            }
                            a(){
                                img(src="static/telegram2.svg")
                            }
                            a(){
                                img(src="static/youtube.svg")
                            }
                        }
                    }
                    div(classes = "footer__up-right"){
                        div(classes = "footer__up-right-item"){
                            span(classes = "footer__up-right-item-title") {
                                +"Навигация"
                            }
                            ul{
                                li{
                                    a("/events"){+"Все мероприятия"}
                                }
                                li{
                                    a("/archive"){+"Архив"}
                                }
                                li{
                                    a("/vacancies"){+"Вакансии"}
                                }
                            }
                        }
                        div(classes = "footer__up-right-item"){
                            span(classes = "footer__up-right-item-title") {
                                +"О нас"
                            }
                            ul{
                                li{
                                    a("/events"){+"О проекте"}
                                }
                                li{
                                    a("/archive"){+"Частые вопросы"}
                                }
                                li{
                                    a("/vacancies"){+"Обратная связь"}
                                }
                            }
                        }
                        div(classes = "footer__up-right-item"){
                            span(classes = "footer__up-right-item-title") {
                                +"Поддержка"
                            }
                            ul{
                                li{
                                    a("tel:88005553535"){+"8-800-555-35-35"}
                                }
                                li{
                                    a("mailto:support@wantinIT.ru"){+"support@wantinIT.ru"}
                                }
                                li{
                                    a("/vacancies"){+"Обратная связь"}
                                }
                            }
                            span(classes = "footer__up-right-item-title") {
                                +"Чат-бот"
                            }
                            div(classes = "footer__socials"){
                                a(){
                                    img(src="static/telegram2.svg", alt="")
                                }

                            }
                        }
                    }

                }
                div(classes = "footer__bottom"){
                    div(classes = "footer__bottom-copyright"){
                        span { +"©2023 Все правы защищены." }
                        ul {
                            li{
                                a() {
                                    +"Пользовательское соглашение"
                                }
                            }
                            li{
                                a() {
                                    +"Политика обработки персональных данных"
                                }
                            }
                        }
                    }
                }
            }
    }

    fun pageFooterDown() = createHTML().footer(classes = "footer") {
        div(classes = "container"){
            div(classes = "footer__up"){
                div(classes = "footer__up-left"){
                    div(classes = "footer__logo"){
                        img(classes="footer__logo-img", src="../static/logo-footer.svg", alt="logo")
                    }
                    div(classes = "footer__socials"){
                        a(){
                            img(src="../static/vk.svg")
                        }
                        a(){
                            img(src="../static/telegram2.svg")
                        }
                        a(){
                            img(src="../static/youtube.svg")
                        }
                    }
                }
                div(classes = "footer__up-right"){
                    div(classes = "footer__up-right-item"){
                        span(classes = "footer__up-right-item-title") {
                            +"Навигация"
                        }
                        ul{
                            li{
                                a("/events"){+"Все мероприятия"}
                            }
                            li{
                                a("/archive"){+"Архив"}
                            }
                            li{
                                a("/vacancies"){+"Вакансии"}
                            }
                        }
                    }
                    div(classes = "footer__up-right-item"){
                        span(classes = "footer__up-right-item-title") {
                            +"О нас"
                        }
                        ul{
                            li{
                                a("/events"){+"О проекте"}
                            }
                            li{
                                a("/archive"){+"Частые вопросы"}
                            }
                            li{
                                a("/vacancies"){+"Обратная связь"}
                            }
                        }
                    }
                    div(classes = "footer__up-right-item"){
                        span(classes = "footer__up-right-item-title") {
                            +"Поддержка"
                        }
                        ul{
                            li{
                                a("tel:88005553535"){+"8-800-555-35-35"}
                            }
                            li{
                                a("mailto:support@wantinIT.ru"){+"support@wantinIT.ru"}
                            }
                            li{
                                a("/vacancies"){+"Обратная связь"}
                            }
                        }
                        span(classes = "footer__up-right-item-title") {
                            +"Чат-бот"
                        }
                        div(classes = "footer__socials"){
                            a(){
                                img(src="../static/telegram2.svg", alt="")
                            }

                        }
                    }
                }

            }
            div(classes = "footer__bottom"){
                div(classes = "footer__bottom-copyright"){
                    span { +"©2023 Все правы защищены." }
                    ul {
                        li{
                            a() {
                                +"Пользовательское соглашение"
                            }
                        }
                        li{
                            a() {
                                +"Политика обработки персональных данных"
                            }
                        }
                    }
                }
            }
        }
    }

    fun pageScriptAxios() = createHTML().script(src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js") {
    }

    fun pageScriptEvents() = createHTML().script(src="scripts/eventsscript.js") {
    }

    fun pageScriptArchive() = createHTML().script(src="scripts/archivescript.js") {
    }

    fun pageScriptSingleEvent() = createHTML().script(src="../scripts/singleeventscript.js") {
    }

    fun pageScriptModal() = createHTML().script(src="scripts/hystmodal.js") {
    }

    fun pageScriptProfileIndividual() = createHTML().script(src="scripts/profileindividualscript.js") {
    }

    fun pageScriptProfileJuridical() = createHTML().script(src="scripts/profilejuridicalscript.js") {
    }

    fun pageScriptProfileAdmin() = createHTML().script(src="scripts/profileadminscript.js") {
    }

    fun pageScriptSlider() = createHTML().script(src="../scripts/itc-slider.js") {
    }

    fun pageScriptCreateNew() = createHTML().script(src="../scripts/createnew.js") {
    }

}