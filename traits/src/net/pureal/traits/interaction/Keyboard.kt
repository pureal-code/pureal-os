package net.pureal.traits.interaction

import net.pureal.traits.*

trait Keyboard {
    val keys: Iterable<Key>
    val pressedKeys: Iterable<Key>
    val down: Observable<Key>
    val up: Observable<Key>
}

trait Key {
    val primaryMeaning: KeyMeaning
    val alternativeMeanings: Iterable<KeyMeaning> get() = listOf()
}

trait KeyMeaning {
    val character : Char?
    val specialMeaning : SpecialKeyMeaning?
}

object KeyMeanings {
    fun character(char : Char) = object : KeyMeaning {
        override val specialMeaning = null
        override val character = char
    }

    fun special(specialMeaning : SpecialKeyMeaning) = object : KeyMeaning {
        override val specialMeaning = specialMeaning
        override val character = null
    }
}

enum class SpecialKeyMeaning {
    Shift
    Control
    Alt
    Escape
    Return
    Delete
    Insert
    Home
    End
    Capslock
}