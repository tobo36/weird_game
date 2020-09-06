package databases

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import world.SpellElement
import world.SpellUsage


object Spells : IntIdTable() {

    val owner = reference("owner", Users)

    val usage = enumeration("usage", SpellUsage::class)

    val element = enumeration("element", SpellElement::class)

}


class Spell(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<Spell>(Spells)


    var owner by User referencedOn Spells.owner

    var usage by Spells.usage

    var element by Spells.element


    override fun toString() = "[ Spell usage=$usage ]"

}