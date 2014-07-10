package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.math.*

trait ColoredElement<T> : Element<T> {
    val fill : Fill
    fun colorAt(location: Vector2) = if(shape.contains(location)) fill.colorAt(location) else null
}

fun coloredElement<T>(content : T, shape : Shape, fill : Fill, transform : Transform2 = Transforms2.identity, changed : Observable<Unit> = observable()) = object : ColoredElement<T> {
    override val content = content
    override val shape = shape
    override val fill = fill
    override val transform = transform
    override val changed = changed
}