package databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import world.UserRaceTypes


object Users : IntIdTable() {

    val name = varchar("name", length = 64)

    val race = enumeration("type", UserRaceTypes::class).default(UserRaceTypes.Invalid)

}


class User(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<User>(Users)


    var name by Users.name

    var race by Users.race


    val items by Item referrersOn Items.owner


    override fun toString() = "[ id=$id, name=$name, items=${items.joinToString { it.name }} ]"

}