package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.math.*

trait ColoredElement : Element {
    override val shape : ColoredShape
}

fun coloredElement(shape : ColoredShape, transform : Transform2 = Transforms2.identity, changed : Observable<Unit> = observable()) = object : ColoredElement {
    override val shape: ColoredShape = shape
    override val transform: Transform2 = transform
    override val changed: Observable<Unit> = changed
}