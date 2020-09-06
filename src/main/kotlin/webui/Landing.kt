package webui

import io.ktor.html.*
import kotlinx.html.*


class Landing : Template<HTML> {

    val content = Placeholder<FlowContent>()

    override fun HTML.apply() {
        head {
            title { +"weird_game Landing Page" }
        }
        body {
            insert(content)
        }
    }

}