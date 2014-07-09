package net.pureal.traits.math

import net.pureal.traits.*

trait Shape {
    fun contains(location : Vector2) : Boolean
}

trait BoundedShape : Shape {
    val size : Vector2
    val bound : Vector2 get() = size / 2
}

fun shape(contains : (Vector2) -> Boolean) = object : Shape {
    override fun contains(location : Vector2) = contains(location)
}

fun concatenatedShape(shapes : Iterable<Shape>) = shape({location -> shapes.any({it.contains(location)})})