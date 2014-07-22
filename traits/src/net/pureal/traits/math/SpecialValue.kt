package net.pureal.traits.math

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
                    is Double -> return if (other == java.lang.Double.NEGATIVE_INFINITY) 0; else -1
                    is Float -> return if (other == java.lang.Float.NEGATIVE_INFINITY) 0; else -1
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

    override fun toString(): String {
        throw UnsupportedOperationException()
    }

    override fun toMathematicalString(): String {
        throw UnsupportedOperationException()
    }

    override fun toDouble(): Double {
        throw UnsupportedOperationException()
    }
    override fun toLong(): Long {
        throw UnsupportedOperationException()
    }


    override fun plus(other: Any?): InternalReal {
        throw UnsupportedOperationException()
    }
    override fun minus(other: Any?): InternalReal {
        throw UnsupportedOperationException()
    }
    override fun times(other: Any?): InternalReal {
        throw UnsupportedOperationException()
    }
    override fun div(other: Any?): InternalReal {
        throw UnsupportedOperationException()
    }
    override fun minus(): InternalReal {
        throw UnsupportedOperationException()
    }
    override fun signum(): Int {
        throw UnsupportedOperationException()
    }

    override fun compareTo(other: Any?): Int
    override fun isInteger(): Boolean = false
}

public val Infinity: SpecialValue get() = SpecialValue.positiveInfinity