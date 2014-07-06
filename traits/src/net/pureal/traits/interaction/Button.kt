package net.pureal.traits.interaction

import net.pureal.traits.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*

trait Button : Clickable<Trigger<Unit>> {
    override fun onClick(vector2 : Vector2) = content()
}

fun button(
        trigger : Trigger<Unit> = trigger<Unit>(),
        transform: Transform2 = Transforms2.identity,
        shape : ColoredShape,
        changed : Observable<Unit> = observable(),
        onClick : () -> Unit = {}) = object : Button {

    override val content = trigger
    override val transform = transform
    override val shape = shape
    override val changed = changed

    {
        content += {onClick()}
    }
}