package net.pureal.traits.interaction

import net.pureal.traits.*

trait Pointer {
    val moved: Observable<Pointer>
    val position: Vector2
}

trait PointerKey  {
    val pointer: Pointer
    val key : Key
}

fun pointerKey(pointer: Pointer, key : Key) : PointerKey = object : PointerKey {
    override val pointer = pointer
    override val key = key
}

trait PointerKeys {
    val pointer : Pointer
    val keys : Iterable<Key>
    val pressed: Observable<PointerKey>
    val released: Observable<PointerKey>
}

fun pointerKeys(pointer: Pointer, keys: Iterable<Key>) = object : PointerKeys {
    override val pointer = pointer
    override val keys = keys
    override val pressed = observable((keys map {it.pressed})) { pointerKey(pointer, it) }
    override val released = observable((keys map {it.released})) { pointerKey(pointer, it)}
}

trait Scroll {
    val scrolled: Observable<Number>
}