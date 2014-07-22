package net.pureal.traits.interaction

import net.pureal.traits.*

trait PointerWithKeys : Pointer, Iterable<Key>

trait Pointer {
    val move: Observable<Vector2>
    val appeared: Observable<Vector2>
    val disappeared: Observable<Vector2>
    val position: Vector2?
}