package net.pureal.traits.math

import net.pureal.traits.Vector2
import net.pureal.traits

trait Ellipse : BoundedShape {
    override fun contains(location: Vector2): Boolean {
        return if (halfSize.x == 0) location.x == 0 && Math.abs(location.y.toDouble()) <= halfSize.y.toDouble()
        else {
            val transformedLocation = traits.vector((location.x.toDouble() / halfSize.x.toDouble()) * halfSize.y.toDouble(), location.y.toDouble())

            return transformedLocation.lengthSquared.toDouble() <= halfSize.y.toDouble() * halfSize.y.toDouble()
        }
    }
}

fun ellipse(size: Vector2) = object : Ellipse {
    override val size = size
}