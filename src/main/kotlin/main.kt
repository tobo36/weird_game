import databases.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import webui.Landing
import webui.UserInfo
import world.*

fun main() {

    Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")


    transaction {

        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Items, Spells, Users)


        val chadUser = User.new {
            name = "chad"
            race = UserRace.Human
        }

        val thadUser = User.new {
            name = "thad"
            race = UserRace.Human
        }

        val ladUser = User.new {
            name = "lad"
            race = UserRace.Human
        }


        val swordItem = Item.new {
            owner = chadUser
            kind = ItemKind.Sword
            modifiers = listOf(ItemModifierFlags.Rusty)
        }


        val axeItem = Item.new {
            owner = chadUser
            kind = ItemKind.Axe
            modifiers = listOf(ItemModifierFlags.Rusty)
        }


        val fireballSpell = Spell.new {
            owner = chadUser
            usage = SpellUsage.Ranged
            element = SpellElement.Fire
        }

    }


    val server = embeddedServer(Netty, port = 8080) {
        routing {

            get("/") {
                val usr = transaction { User.findById(1) } ?: return@get

                call.respondHtmlTemplate(Landing()) {
                    content {
                        insert(UserInfo(usr)) {}
                    }
                }
            }

            // Item Information
            get("/item") {
                val itemsList = transaction { Item.all().joinToString() }
                call.respondText("items:\n$itemsList", ContentType.Text.Plain)
            }
            get("/item/{id}") {
                val itemId = call.parameters["id"]?.toIntOrNull() ?: return@get
                val item = transaction { Item.findById(itemId) } ?: return@get
                call.respondText(item.toString(), ContentType.Text.Plain)
            }

            // Spell Information
            get("/spell") {
                val spellList = transaction { Spell.all().joinToString() }
                call.respondText("spells:\n$spellList", ContentType.Text.Plain)
            }
            get("/spell/{id}") {
                val spellId = call.parameters["id"]?.toIntOrNull() ?: return@get
                val spell = transaction { Item.findById(spellId) } ?: return@get
                call.respondText(spell.toString(), ContentType.Text.Plain)
            }

            // User Information
            get("/user") {
                val usersList = transaction { User.all().joinToString() }
                call.respondText("users:\n$usersList", ContentType.Text.Plain)
            }
            get("/user/{id}") {
                val userId = call.parameters["id"]?.toIntOrNull() ?: return@get
                val user = transaction { User.findById(userId) }
                call.respondText(user.toString(), ContentType.Text.Plain)
            }

        }
    }

    server.start(wait = true)

}