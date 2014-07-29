package net.pureal.traits.math

import net.pureal.traits.Vector2
import net.pureal.traits.math.BoundedShape

trait Rectangle : BoundedShape {
    override fun contains(location: Vector2) = Math.abs(location.x.toDouble()) <= halfSize.x.toDouble() && Math.abs(location.y.toDouble()) <= halfSize.y.toDouble()
}

fun rectangle(size: Vector2) = object : Rectangle {
    override val size = size
}