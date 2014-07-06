package net.pureal.traits.interaction

import net.pureal.traits.graphics.Element
import net.pureal.traits.Vector2
import net.pureal.traits.Transform2
import net.pureal.traits.Transforms2
import net.pureal.traits.math.Shape
import net.pureal.traits.Observable
import net.pureal.traits.observable

trait Clickable<T> : Element<T> {
    fun onClick(location : Vector2) {}
}

fun clickable<T>(content : T,
                 transform: Transform2 = Transforms2.identity,
                 shape : Shape,
                 changed : Observable<Unit> = observable()) = object : Clickable<T> {
    override val content = content
    override val transform = transform
    override val shape = shape
    override val changed = changed
}