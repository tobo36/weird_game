package world

enum class UserRaceTypes {

    /**
     * Invalid type signifies that the given item has not been allocated a type, and should probably be deleted
     */
    Invalid,

    /**
     * The ordering of these items are important. Changing them will change items within the respective table
     */
    Human

}