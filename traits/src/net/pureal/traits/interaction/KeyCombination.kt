package net.pureal.traits.interaction

trait KeyCombination {
    val keys: Iterable<Key>
    val meaning : Command?
}