package webui

import databases.User
import databases.prettyName
import io.ktor.html.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserInfo(val user: User) : Template<FlowContent> {

    override fun FlowContent.apply() {
        div(UserInfo::class.simpleName) {
            table {
                tr { // User Identifier
                    td { +"id" }
                    td { +user.id.toString() }
                }
                tr { // User Name
                    td { +"name" }
                    td { +user.name }
                }
                tr { // User Race
                    td { +"race" }
                    td { +user.race.toString() }
                }
            }
            ul { // Items
                transaction {
                    for (item in user.items)
                        li { +item.prettyName }
                }
            }
            ul { // Spells
                transaction {
                    for (spell in user.spells)
                        li { +spell.prettyName }
                }
            }
        }
    }

}