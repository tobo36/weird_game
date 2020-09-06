package databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import world.ItemKindTypes
import world.ItemModifierTypes


object Items : IntIdTable() {

    val owner = reference("owner", Users)

    val kind = enumeration("type", ItemKindTypes::class).default(ItemKindTypes.Invalid)

    val modifiers = varchar("modifiers", 512)

}


class Item(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<Item>(Items)


    val name: String
        get() {
            return "${modifiers.joinToString(" ")} $kind"
        }


    var owner by User referencedOn Items.owner

    var kind by Items.kind


    private var _modifiers by Items.modifiers

    var modifiers: List<ItemModifierTypes>
        get() {
            return ItemModifierTypes.unpack(_modifiers).toList()
        }
        set(value) {
            _modifiers = ItemModifierTypes.pack(value.asSequence())
        }



    override fun toString() = "[ id=$id, kind=$kind, modifiers=${modifiers.joinToString(" ")} ]"

}