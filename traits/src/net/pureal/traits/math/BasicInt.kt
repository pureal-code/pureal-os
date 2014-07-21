package net.pureal.traits.math

import java.math.BigInteger
import kotlin.math.*
import java.lang.Math.*

public trait BasicInt : BasicReal {

    fun toBasicReal(): BasicReal = basicReal(this)

    override fun minus(): BasicInt = basicInt(-number)

    final override fun isInteger(): Boolean = true

    final override val exponent: Long get() = 0


    final override fun exponentialFactor(): BigInteger = BigInteger.ONE
    final override fun doubleExponentialFactor(): Double = 1.0
}

/** MAIN CONSTRUCTOR **/
fun basicInt(it: Any?): BasicInt {
    return when(it) {
        is BasicInt -> basicInt(it.number)
        is String -> basicInt(BigInteger(it))
        is Long -> basicInt(BigInteger(it))
        is Int -> basicInt(BigInteger(it))
        is Short -> basicInt(BigInteger(it))
        is Byte -> basicInt(BigInteger(it))
        is BigInteger -> object : BasicInt, Number() {
            override val number: BigInteger = it
        }
        else -> throw IllegalArgumentException("Cannot create a BasicInt of given '{$it}'")
    }
}

fun BigInteger(b: Byte): BigInteger = BigInteger(b.toString())
fun BigInteger(s: Short): BigInteger = BigInteger(s.toString())
fun BigInteger(i: Int): BigInteger = BigInteger(i.toString())
fun BigInteger(l: Long): BigInteger = BigInteger(l.toString())

fun BigInteger(f: Float): BigInteger = BigInteger(f.toString())
fun BigInteger(d: Double): BigInteger = BigInteger(d.toString())


