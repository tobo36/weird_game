package databases

import world.SpellElement
import world.SpellUsage


val Spell.prettyName: String
    get() = when {
        (usage == SpellUsage.Ranged) and (element == SpellElement.Fire) -> "Firebolt"
        (usage == SpellUsage.Ranged) and (element == SpellElement.Frost) -> "Ice Spear"
        (usage == SpellUsage.Ranged) and (element == SpellElement.Electricity) -> "Lightning"
        else -> "$usage Spell of $element"
    }