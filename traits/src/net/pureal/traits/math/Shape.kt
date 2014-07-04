package net.pureal.traits.math

import net.pureal.traits.*

trait Shape {
    fun contains(location : Vector2) : Boolean
}

trait BoundedShape : Shape {
    val size : Vector2
    val bound : Vector2 get() = size / 2
}

trait Ellipse : BoundedShape {
    override fun contains(location : Vector2) : Boolean {
        return if(bound.x == 0) location.x == 0 && Math.abs(location.y.toDouble()) <= bound.y.toDouble()
        else {
            val transformedLocation = vector((location.x.toDouble() / bound.x.toDouble()) * bound.y.toDouble(), location.y.toDouble())

            return transformedLocation.lengthSquared.toDouble() <= bound.y.toDouble() * bound.y.toDouble()
        }
    }
}

trait Circle : Ellipse {
    val radius : Number
    override val size : Vector2 get() = vector(2 * radius.toDouble(), 2 * radius.toDouble())
}

trait Rectangle : BoundedShape {
    override fun contains(location : Vector2) = Math.abs(location.x.toDouble()) <= bound.x.toDouble() && Math.abs(location.y.toDouble()) <= bound.y.toDouble()
}

fun rectangle(size : Vector2) = object : Rectangle {
    override val size = size
}

fun ellipse(size : Vector2) = object : Ellipse {
    override val size = size
}

fun circle(radius : Number) = object : Circle {
    override val radius = radius
}

fun shape(contains : (Vector2) -> Boolean) = object : Shape {
    override fun contains(location : Vector2) = contains(location)
}

fun concatenatedShape(shapes : Iterable<Shape>) = shape({location -> shapes.any({it.contains(location)})})