package net.pureal.traits.math

import net.pureal.traits.Vector2
import net.pureal.traits

trait Circle : Ellipse {
    val radius: Number
    override val size: Vector2 get() = traits.vector(2 * radius.toDouble(), 2 * radius.toDouble())
}

fun circle(radius: Number) = object : Circle {
    override val radius = radius
}