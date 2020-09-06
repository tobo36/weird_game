package databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import world.ItemTypes


object Items : IntIdTable() {

    val owner = reference("owner", Users)

    val type = enumeration("type", ItemTypes::class).default(ItemTypes.Invalid)

}


class Item(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<Item>(Items)


    var owner by User referencedOn Items.owner

    var type by Items.type


    override fun toString() = "[ id=$id, type=$type ]"

}