package net.pureal.traits.interaction

import net.pureal.traits.*

trait PointerInput {
    val click : Observable<Vector2>

    fun transform(transform : Transform2) : PointerInput {
        val o = observable(click)

        null!!
        // TODO click += {o.notifyObservers(transform(it))}

        return pointerInput(o)
    }
}

fun pointerInput(click : Observable<Vector2>) = object : PointerInput {
    override val click = click
}