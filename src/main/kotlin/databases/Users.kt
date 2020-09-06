package databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import world.UserRace


object Users : IntIdTable() {

    val name = varchar("name", length = 64)

    val race = enumeration("type", UserRace::class)

}


class User(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<User>(Users)


    var name by Users.name

    var race by Users.race


    val items by Item referrersOn Items.owner

    val spells by Spell referrersOn Spells.owner


    override fun toString() = "[ User id=$id, name=$name, race=$race, items=${items.joinToString()}, spells=${spells.joinToString()} ]"

}