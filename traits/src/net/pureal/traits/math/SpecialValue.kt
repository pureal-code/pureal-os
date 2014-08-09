package net.pureal.traits.math

import net.pureal.traits.InvalidateFun

public trait SpecialValue : InternalReal {
    public class object {
        public val positiveInfinity: SpecialValue = object : SpecialValue, Calculatable() {
            override fun toDouble() = java.lang.Double.POSITIVE_INFINITY
            override fun compareTo(other: Any?): Int {
                when (other) {
                    is Double -> return if (other == java.lang.Double.POSITIVE_INFINITY) 0; else -1
                    is Float -> return if (other == java.lang.Float.POSITIVE_INFINITY) 0; else -1
                    is InternalReal -> return compareTo(other.toDouble())
                    is Number -> return -1
                    else -> throw IllegalArgumentException()
                }
            }
            override fun minus() = SpecialValue.negativeInfinity
            override fun toString() = "Infinity"
            override fun signum() = 1
        }
        public val negativeInfinity: SpecialValue = object : SpecialValue, Calculatable() {
            override fun toDouble() = java.lang.Double.NEGATIVE_INFINITY
            override fun compareTo(other: Any?): Int {
                when (other) {
                    is Double -> return if (other == java.lang.Double.NEGATIVE_INFINITY) 0; else 1
                    is Float -> return if (other == java.lang.Float.NEGATIVE_INFINITY) 0; else 1
                    is InternalReal -> return compareTo(other.toDouble())
                    is Number -> return -1
                    else -> throw IllegalArgumentException()
                }
            }
            override fun minus() = SpecialValue.positiveInfinity
            override fun toString() = "-Infinity"
            override fun signum() = -1
        }
    }
    // Invalidate super functions
    override fun toString(): String = InvalidateFun()

    override fun toMathematicalString(): String = InvalidateFun()

    override fun toDouble(): Double = InvalidateFun()
    override fun toLong(): Long = InvalidateFun()


    override fun plus(other: Any?): InternalReal = InvalidateFun()
    override fun minus(other: Any?): InternalReal = InvalidateFun()
    override fun times(other: Any?): InternalReal = InvalidateFun()
    override fun div(other: Any?): InternalReal = InvalidateFun()
    override fun mod(other: Any?): InternalReal = InvalidateFun()

    override fun minus(): InternalReal = InvalidateFun()
    override fun signum(): Int = InvalidateFun()

    override fun floor(): InternalReal = InvalidateFun()
    override fun ceil(): InternalReal = InvalidateFun()
    override fun round(): InternalReal = InvalidateFun()
    override fun abs(): InternalReal = InvalidateFun()

    override fun compareTo(other: Any?): Int
    override fun isInteger(): Boolean = false
}

public val Infinity: SpecialValue get() = SpecialValue.positiveInfinity