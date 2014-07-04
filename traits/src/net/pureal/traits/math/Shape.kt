package net.pureal.traits.math

import net.pureal.traits.*

trait Shape {
    fun contains(location : Vector2) : Boolean
}

trait BoundedShape : Shape {
    val size : Vector2
}

trait Ellipse : BoundedShape {
    override fun contains(location : Vector2) : Boolean {
        return if(size.x == 0) location.x == 0 && Math.abs(location.y.toDouble()) <= size.y.toDouble() / 2
        else {
            val transformedLocation = vector((location.x.toDouble() / size.x.toDouble()) * size.y.toDouble(), location.y.toDouble())

            return transformedLocation.lengthSquared.toDouble() <= size.y.toDouble() * size.y.toDouble()
        }
    }
}

trait Circle : Ellipse {
    val radius : Number
    override val size : Vector2 get() = vector(2 * radius.toDouble(), 2 * radius.toDouble())
}

trait Rectangle : BoundedShape {
    override fun contains(location : Vector2) = Math.abs(location.x.toDouble()) <= size.x.toDouble() / 2 && Math.abs(location.y.toDouble()) <= size.y.toDouble() / 2
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