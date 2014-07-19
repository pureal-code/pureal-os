package net.pureal.traits.interaction

import net.pureal.traits.*

trait PointerInput {
    val click: Observable<Vector2>
}

fun pointerInput(click: Observable<Vector2>) = object : PointerInput {
    override val click = click
}