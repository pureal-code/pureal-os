package net.pureal.traits.graphics

import net.pureal.traits.math.Shape
import net.pureal.traits.Vector2

trait ColoredShape : Shape {
    override fun contains(location : Vector2) = colorAt(location) != null
    fun colorAt(location : Vector2) : Color?
}

fun coloredShape(shape : Shape, fill : Fill) = object : ColoredShape {
    override fun colorAt(location: Vector2) = if(shape.contains(location)) fill.colorAt(location) else null
}

fun solidColoredShape(shape : Shape, color : Color) = coloredShape(shape, Fills.solid(color))