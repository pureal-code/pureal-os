package net.pureal.traits.interaction

import net.pureal.traits.Observable

trait Key {
    val definition: KeyDefinition
    val isPressed : Boolean
    val pressed: Observable<Key>
    val released: Observable<Key>
}