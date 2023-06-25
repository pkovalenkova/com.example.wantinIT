package com.example.plugins

import io.ktor.server.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow

fun Application.configureTemplating() {
    
    
    routing {
        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }
        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.darkBlue
                    margin(0.px)
                }
                rule("h1.page-title") {
                    color = Color.white
                }
            }
        }
        get("/events.css") {
            call.respondCss {

                rule("p.page") {
                    //color = Color.white
                    fontFamily = "arial"
                    fontSize=32.px
                }
                rule("div.header") {
                    background = "#F2F9FF"
                    //width=1920.px
                    margin(0.px)
                    padding(15.px,25.px)
                    boxShadow(Color.black,0.px,0.5.px,2.px, 0.px)
                    //border(LinearDimension("10px"), BorderStyle.solid, Color("#000"),)
                }
                a{
                    //color = Color.white
                    outline=Outline.none
                    fontFamily = "arial"
                    fontSize=18.px
                    margin(10.px)

                }
                a{
                    this.visited {
                        background = "#ABC123"
                    }
                    this.hover {
                        background = "#123ABC"
                    }
                }
                rule("a.main") {
                    //color = Color.white
                    fontFamily = "arial"
                    fontSize=18.px
                    margin(0.px,500.px,0.px,0.px)
                }
                visited {
                    outline=Outline.none
                    color=Color.black
                }

                rule("div.search") {
                    background = "#F5F5F5"
                    width=1050.px
                    height=20.px
                    borderRadius= 10.px;
                    alignContent = Align.end




                    padding(5.px)
                    border(2.px, BorderStyle.solid, Color("#727272"),)
                }

            }
        }
        
        get("/html-css-dsl") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                }
                body {
                    h1(classes = "page-title") {
                        +"Hello from Ktor!"
                    }
                }
            }
        }
    }
}
suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
   this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

