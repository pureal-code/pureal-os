package net.pureal.traits.math

import net.pureal.traits.Vector2
import net.pureal.traits

trait Ellipse : BoundedShape {
    override fun contains(location : Vector2) : Boolean {
        return if(bound.x == 0) location.x == 0 && Math.abs(location.y.toDouble()) <= bound.y.toDouble()
        else {
            val transformedLocation = traits.vector((location.x.toDouble() / bound.x.toDouble()) * bound.y.toDouble(), location.y.toDouble())

            return transformedLocation.lengthSquared.toDouble() <= bound.y.toDouble() * bound.y.toDouble()
        }
    }
}

fun ellipse(size : Vector2) = object : Ellipse {
    override val size = size
}