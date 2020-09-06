import databases.Item
import databases.Items
import databases.User
import databases.Users
import io.ktor.application.*
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
import world.ItemTypes

fun main() {

    Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")


    transaction {

        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Users, Items)

        val thomasUser = User.new {
            name = "thomas"
        }

        val toboUser = User.new {
            name = "tobo"
        }

        val kaineUser = User.new {
            name = "kaine"
        }


        val daSwordItem = Item.new {
            owner = toboUser
            type = ItemTypes.Sword
        }

    }


    val server = embeddedServer(Netty, port = 8080) {
        routing {

            get("/") {
                call.respondText("Welcome!", ContentType.Text.Plain)
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

            // Item Information
            get("/item") {
                val itemsList = transaction { Item.all().joinToString() }
                call.respondText("items:\n$itemsList", ContentType.Text.Plain)
            }
            get("/item/{id}") {
                val itemId = call.parameters["id"]?.toIntOrNull() ?: return@get
                val item = transaction { Item.findById(itemId) }
                call.respondText(item.toString(), ContentType.Text.Plain)
            }

        }
    }

    server.start(wait = true)

}