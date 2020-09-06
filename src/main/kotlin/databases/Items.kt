package databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import world.ItemKind
import world.ItemModifierFlags


object Items : IntIdTable() {

    val owner = reference("owner", Users)

    val kind = enumeration("type", ItemKind::class)

    val modifiers = varchar("modifiers", 512).default("")

}


class Item(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<Item>(Items)


    var owner by User referencedOn Items.owner

    var kind by Items.kind


    private var _modifiers by Items.modifiers

    var modifiers: List<ItemModifierFlags>
        get() {
            return ItemModifierFlags.unpack(_modifiers).toList()
        }
        set(value) {
            _modifiers = ItemModifierFlags.pack(value.asSequence())
        }


    override fun toString() = "[ Item id=$id, kind=$kind, modifiers=${modifiers.joinToString(" ")} ]"

}