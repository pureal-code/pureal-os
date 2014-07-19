package net.pureal.traits.math

import net.pureal.traits.math.operations.additionValue
import net.pureal.traits.math.operations.multiplicationValue
import net.pureal.traits.math.operations.subtractionValue
import net.pureal.traits.math.operations.divisionValue

// TODO: cast to Number when compiler works correctly with it
public trait Real : Number {
    val isApproximate: Boolean get() = false

    fun simplify(): Real {
        // TODO: return sympy.simplify(toMathematicalString()) - is to be inherited by all sub-traits in the end
        return this
    }

    fun approximate(): InternalReal {
        throw UnsupportedOperationException()
    }

    override fun equals(other: Any?): Boolean {
        when (other) {
            null -> return false
            is Real -> {
                if (this is RealPrimitive && other is RealPrimitive) {
                    return this.approximate() == other.approximate()
                }
                return false
                // return this.approximate() == other.approximate()
                // TODO: actually pretty dirty checking, to be refined later
            }
            is Number -> {
                return this.approximate() == other
            }
        }
        return false
    }

    fun plus(other: Real): Real = additionValue(this, other)

    fun minus(): Real = subtractionValue(0.toReal(), this)

    fun minus(other: Real): Real = subtractionValue(this, other)

    fun times(other: Real): Real = multiplicationValue(this, other)

    fun div(other: Real): Real = divisionValue(this, other)

    fun invert(): Real = divisionValue(1.toReal(), this)


    override fun toDouble(): Double = approximate().toDouble()
    override fun toFloat(): Float = approximate().toFloat()
    override fun toLong(): Long = approximate().toLong()
    override fun toInt(): Int = approximate().toInt()
    override fun toShort(): Short = approximate().toShort()
    override fun toByte(): Byte = approximate().toByte()
    override fun toChar(): Char = approximate().toChar()

}