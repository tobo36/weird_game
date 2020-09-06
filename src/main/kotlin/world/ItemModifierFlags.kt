package world

enum class ItemModifierFlags {
    Rusty,
    ;

    companion object {

        fun pack(modifiers: Sequence<ItemModifierFlags>): String {
            return modifiers.joinToString("|")
        }

        fun unpack(string: String): Sequence<ItemModifierFlags> {
            return string.split("|").asSequence().map(::valueOf)
        }

    }

}