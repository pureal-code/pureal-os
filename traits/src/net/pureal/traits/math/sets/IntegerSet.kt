package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public object IntegerSet : Set<Number> {
    override fun contains(other: Set<Number>): Boolean {
        return false
    }
    override fun contains(other: Number): Boolean {
        when (other) {
            is Long -> return true
            is Int -> return true
            is Short -> return true
            is Byte -> return true
            is InternalReal -> return other.isInteger()
            is Double -> return other % 1.0 == 0.0
            is Float -> return other % 1.0f == 0.0f
            else -> return false
        }
    }
}