package databases


val Item.prettyName: String
    get() = when {
        else -> "${modifiers.joinToString(" ")} $kind"
    }