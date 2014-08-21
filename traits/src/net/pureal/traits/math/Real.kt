package net.pureal.traits.math

import net.pureal.traits.Constructor1
import net.pureal.traits.Constructor2

public trait Real : Calculatable {

    public class object : Constructor1<Real, Any?>, Constructor2<Real, Any?, Boolean> {
        override fun invoke(it: Any?): Real = invoke(it, false)
        override fun invoke(v: Any?, isApprox: Boolean): Real {
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
    }

    val subReals: Array<Real> get() = array<Real>()

    fun replaceSubReals(vararg a: Real): Real = this

    fun filterRecursive(successCond: (Real) -> Boolean): Array<Real> {
        if(successCond(this)) return array(this)
        var a: Array<Real> = array()
        for (i in subReals.indices) a += subReals[i].filterRecursive(successCond)
        return a
    }

    fun filterRecursiveCond(continueCond: (Real) -> Boolean, successCond: (Real) -> Boolean): Array<Real> {
        if(!continueCond(this)) return array()
        if(successCond(this)) return array(this)
        var a: Array<Real> = array()
        for (i in subReals.indices) a += subReals[i].filterRecursiveCond(continueCond, successCond)
        return a
    }

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

    // TODO:
    override fun mod(other: Any?): Calculatable = approximate() % other

    fun invert(): Real = ee.divVal(1.toReal(), this)


    override fun toDouble(): Double = approximate().toDouble()
    override fun toFloat(): Float = approximate().toFloat()
    override fun toLong(): Long = approximate().toLong()
    override fun toInt(): Int = approximate().toInt()
    override fun toShort(): Short = approximate().toShort()
    override fun toByte(): Byte = approximate().toByte()
    override fun toChar(): Char = approximate().toChar()

    override fun floor(): Calculatable = approximate().floor()
    override fun ceil(): Calculatable = approximate().ceil()
    override fun round(): Calculatable = approximate().round()

    override fun abs(): Calculatable = approximate().abs() // TODO: we want an operator for this

    val isZero: Boolean get() = false
        // Means zero in the way it is written, not considering variables
    val isPositive: Boolean get() = true
        // Means positive in the way it is written, not considering variables (if it CAN be positive)
}

val real = Real

fun Number.toReal(): Real = real(this)