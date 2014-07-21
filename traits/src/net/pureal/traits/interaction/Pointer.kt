package net.pureal.traits.interaction

import net.pureal.traits.*

trait ClickablePointer : Pointer, Keyboard

trait Pointer {
    val move: Observable<Vector2>
    val appeared: Observable<Vector2>
    val disappeared: Observable<Vector2>
    val position: Vector2?
}

fun pointerInput(click: Observable<Vector2>) = object : Pointer {
    override val click = click
}