package net.pureal.traits.math

import net.pureal.traits.InvalidateFun

public trait Infinity : InternalReal {
    public class object : Infinity, Calculatable() {
        public val positiveInfinity: Infinity = Infinity
        override fun toDouble() = java.lang.Double.POSITIVE_INFINITY
        override fun tryCompareTo(other: Calculatable): Int {
            when (other) {
                is InternalReal -> return if (other.toDouble() == java.lang.Double.POSITIVE_INFINITY) 0 else 1
                else -> return 1
            }
        }
        override fun minus() = Infinity.negativeInfinity
        override fun toString() = "Infinity"
        override fun signum() = 1

        public val negativeInfinity: Infinity = object : Infinity, Calculatable() {
            override fun toDouble() = java.lang.Double.NEGATIVE_INFINITY
            override fun tryCompareTo(other: Calculatable): Int {
                when (other) {
                    is InternalReal -> return if (other.toDouble() == java.lang.Double.NEGATIVE_INFINITY) 0 else -1
                    else -> return -1
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

    override fun tryCompareTo(other: Calculatable): Int
    override fun isInteger(): Boolean = false
}

val NegativeInfinity = Infinity.negativeInfinity
