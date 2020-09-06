package world

enum class ItemModifierTypes {

    /**
     * Invalid type signifies that the given item has not been allocated a type, and should probably be deleted
     */
    Invalid,

    /**
     * The ordering of these items are important. Changing them will change items within the respective table
     */
    Rusty,

    ;

    companion object {

        fun pack(modifiers: Sequence<ItemModifierTypes>): String {
            return modifiers.joinToString("|")
        }

        fun unpack(string: String): Sequence<ItemModifierTypes> {
            return string.split("|").asSequence().map(::valueOf)
        }

    }

}