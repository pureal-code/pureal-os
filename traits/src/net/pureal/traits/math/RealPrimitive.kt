package net.pureal.traits.math

import java.math.BigInteger


public trait RealPrimitive : Real {
    val value: InternalReal

    override fun toString() = "real(\"${value.toMathematicalString()}\")"

    override fun approximate() = value

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is RealPrimitive -> value == other.value
            is Real -> false
            is Number -> value == other
            else -> false
        }
    }

    override fun minus(): Real = real(-value)
}


