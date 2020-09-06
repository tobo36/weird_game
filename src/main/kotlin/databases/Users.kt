package databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object Users: IntIdTable() {

    val name = varchar("name", length = 64)

}


class User(id: EntityID<Int>) : IntEntity(id) {

    companion object: IntEntityClass<User>(Users)


    var name by Users.name

    val items by Item referrersOn Items.owner


    override fun toString() = "[ id=$id, name=$name, items=${items.joinToString()} ]"

}