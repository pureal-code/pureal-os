package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.math.*

trait ColoredElement : Element {
    override val shape : ColoredShape
}

trait ColoredShape : Shape {
    override fun contains(location : Vector2) = colorAt(location) != null
    fun colorAt(location : Vector2) : Color?
}

fun singleColored(shape : Shape, color : Color) = object : ColoredShape {
    override fun colorAt(location: Vector2) = if(shape.contains(location)) color else null
}