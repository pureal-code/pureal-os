package net.pureal.traits.interaction

import net.pureal.traits.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import java.util.Date

trait Button : Clickable<Trigger<Unit>> {
    override fun onClick(location : Vector2) = content()
}

fun button(
        trigger : Trigger<Unit> = trigger<Unit>(),
        transform: Transform2 = Transforms2.identity,
        shape : Shape,
        fill : Fill,
        changed : Observable<Unit> = observable(),
        onClick : () -> Unit = {}) = object : Button {
    override val content = trigger
    override val transform = transform
    override val shape = shape
    override val fill = fill
    override val changed = changed
    {
        content += {onClick()}
    }
}