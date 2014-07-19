package net.pureal.traits.interaction

import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*

trait Clickable<T> : ColoredElement<T> {
    fun onClick(location: Vector2) {
    }
}

fun clickable<T>(content: T,
                 transform: Transform2 = Transforms2.identity,
                 shape: Shape,
                 fill: Fill,
                 changed: Observable<Unit> = observable()) = object : Clickable<T> {
    override val content = content
    override val transform = transform
    override val shape = shape
    override val fill = fill
    override val changed = changed
}