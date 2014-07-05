package net.pureal.traits.interaction

import net.pureal.traits.*

trait PointerInput {
    val click : Observable<Vector2>

    fun transform(transform : Transform2) : PointerInput = pointerInput(
        object : Observable<Vector2> {{
            click += {notifyObservers(transform(it))}
        }}
    )
}

fun pointerInput(click : Observable<Vector2>) = object : PointerInput {
    override val click = click
}