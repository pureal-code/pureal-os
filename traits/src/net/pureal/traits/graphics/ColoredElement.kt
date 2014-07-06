package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.math.*

trait ColoredElement<T> : Element<T> {
    override val shape : ColoredShape
}

fun coloredElement<T>(content : T, shape : ColoredShape, transform : Transform2 = Transforms2.identity, changed : Observable<Unit> = observable()) = object : ColoredElement<T> {
    override val content = content
    override val shape = shape
    override val transform = transform
    override val changed = changed
}