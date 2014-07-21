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

fun real(v: Any?, isApprox : Boolean = false): RealPrimitive {
    when (v) {
        null -> throw IllegalArgumentException("Cannot create a real out of nothing")
        is RealPrimitive -> return real(v.value, v.isApproximate)
        is Real -> throw IllegalArgumentException("A RealPrimitive cannot be created with an expression")
        is InternalReal -> return object : RealPrimitive, Number() {
            override val value : InternalReal = v
            override val isApproximate : Boolean = isApprox
        }
        else -> return real(ee.intReal(v), isApprox)
    }
}

fun Number.toReal(): RealPrimitive = real(this)

