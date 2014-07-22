package net.pureal.traits.math

import net.pureal.traits.math.operations.additionValue
import net.pureal.traits.math.operations.multiplicationValue
import net.pureal.traits.math.operations.subtractionValue
import net.pureal.traits.math.operations.divisionValue

public trait Real : Calculatable {
    val isApproximate: Boolean get() = false

    fun matchWithThisPattern(other: Real) : Boolean = this == other // true if other matches the pattern defined by this

    final fun simplify() : Real {
        return ee.simplifier.simplify(this)
    }

    fun calculate(): Real {
        return this
    }

    fun approximate(): InternalReal {
        throw UnsupportedOperationException()
    }

    override fun equals(other: Any?): Boolean {
        // TODO: this is bullshit as well
        when (other) {
            null -> return false
            is Real -> {
                if (this is RealPrimitive && other is RealPrimitive) {
                    return this.approximate() == other.approximate()
                }
                return false
            }
            is Number -> {
                return this.approximate() == other
            }
        }
        return false
    }

    override fun compareTo(other: Any?): Int = approximate().compareTo(other)

    override fun plus() = this
    override fun minus(): Real = ee.subVal(0.toReal(), this)


    override fun plus(other: Any?): Real = ee.addVal(this, real(other))

    override fun minus(other: Any?): Real = ee.subVal(this, real(other))

    override fun times(other: Any?): Real = ee.mulVal(this, real(other))

    override fun div(other: Any?): Real = ee.divVal(this, real(other))

    fun invert(): Real = ee.divVal(1.toReal(), this)


    override fun toDouble(): Double = approximate().toDouble()
    override fun toFloat(): Float = approximate().toFloat()
    override fun toLong(): Long = approximate().toLong()
    override fun toInt(): Int = approximate().toInt()
    override fun toShort(): Short = approximate().toShort()
    override fun toByte(): Byte = approximate().toByte()
    override fun toChar(): Char = approximate().toChar()

}

fun real(v: Any?, isApprox : Boolean = false): Real {
    when (v) {
        is Real -> return v
        is InternalReal -> return object : RealPrimitive, Calculatable() {
            override val value : InternalReal = v
            override val isApproximate : Boolean = isApprox
        }
        null -> throw IllegalArgumentException("Cannot create a real out of nothing")
        else -> return real(ee.intReal(v), isApprox)
    }
}

fun Number.toReal(): Real = real(this)