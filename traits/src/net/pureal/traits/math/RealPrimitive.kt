package net.pureal.traits.math

import java.math.BigInteger


public trait RealPrimitive : Real {
    val value: InternalReal

    // TODO: replace with BasicReal later
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

fun real(v: Number): RealPrimitive {
    when (v) {
        is Real -> throw IllegalArgumentException("A RealPrimitive cannot be created with an expression")
        is Int -> return real(BasicReal(v))
        is Long -> return real(BasicReal(v))
        is Short -> return real(BasicReal(v))
        is Byte -> return real(BasicReal(v))
        is Float -> return real(BasicReal(v))
        is Double -> return real(BasicReal(v))
        is BigInteger -> return real(BasicReal(v))
        else -> throw IllegalArgumentException("Unrecognized parameter")
    }
}

fun real(r: InternalReal): RealPrimitive = object : RealPrimitive, Number() {
    override val value = r
}

fun real(s: String): RealPrimitive = real(BasicReal(s))

fun Number.toReal(): RealPrimitive = real(this)

