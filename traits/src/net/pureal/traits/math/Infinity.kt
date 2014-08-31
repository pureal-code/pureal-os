package net.pureal.traits.math

import net.pureal.traits.InvalidateFun

public trait Infinity : InternalReal {
    public class object : Infinity, Calculatable() {
        public val positiveInfinity: Infinity = Infinity
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
        override fun minus() = Infinity.negativeInfinity
        override fun toString() = "Infinity"
        override fun signum() = 1

        public val negativeInfinity: Infinity = object : Infinity, Calculatable() {
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
            override fun minus() = Infinity.positiveInfinity
            override fun toString() = "-Infinity"
            override fun signum() = -1
        }
    }
    // Invalidate super functions
    override fun toMathematicalString(): String = toString()

    override fun toDouble(): Double = InvalidateFun()
    override fun toLong(): Long = InvalidateFun()


    override fun plus(other: Any?): InternalReal = if (-this == other) throw IllegalArgumentException() else this
    override fun minus(other: Any?): InternalReal = if (this == other) throw IllegalArgumentException() else this

    override fun times(other: Any?): InternalReal
            = if (other !is Number || other == 0) throw IllegalArgumentException() else if (other.asCalculatable() >= 0) this else -this

    override fun div(other: Any?, requireExact: Boolean): InternalReal
            = if (other !is Number || other is Infinity) throw IllegalArgumentException() else if (other.asCalculatable() >= 0) this else -this

    override fun mod(other: Any?): InternalReal = InvalidateFun()

    override fun floor(): InternalReal = this
    override fun ceil(): InternalReal = this
    override fun round(): InternalReal = this

    override fun abs(): InternalReal = Infinity

    override fun compareTo(other: Any?): Int
    override fun isInteger(): Boolean = false
}
