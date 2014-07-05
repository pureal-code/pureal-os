package net.pureal.traits.graphics

import net.pureal.traits.math.Shape
import net.pureal.traits.Vector2

trait ColoredShape : Shape {
    override fun contains(location : Vector2) = colorAt(location) != null
    fun colorAt(location : Vector2) : Color?
}

trait SingleColoredShape : ColoredShape {
    protected val shape : Shape
    val color : Color
    override fun colorAt(location: Vector2) = if(shape.contains(location)) color else null
}

fun singleColored(shape : Shape, color : Color) = object : SingleColoredShape {
    override fun colorAt(location: Vector2) = if(shape.contains(location)) color else null
}