package net.pureal.traits.interaction

import net.pureal.traits.*

trait Pointer {
    val down: Observable<Vector2>
    val up: Observable<Vector2>
    val move: Observable<Vector2>
    val click: Observable<Vector2>
    val disappeared: Observable<Vector2>
    val position: Vector2
}

fun pointerInput(click: Observable<Vector2>) = object : Pointer {
    override val click = click
}