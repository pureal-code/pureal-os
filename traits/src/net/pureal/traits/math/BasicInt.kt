package net.pureal.traits.math

import java.math.BigInteger
import kotlin.math.*
import java.lang.Math.*


/** Basic Int:
 *  Subtype of BasicReal that is always Integer with exp 0
 *  to facilitate calculations ...
 */
public trait BasicInt : BasicReal {

    fun toBasicReal(): BasicReal = BasicReal(this, 0)

    override fun minus(): BasicInt = BasicInt(-number)

    final override fun isInteger(): Boolean = true

    final override val exponent: Long get() = 0


    override fun exponentialFactor(): BigInteger = BigInteger.ONE
    override fun doubleExponentialFactor(): Double = 1.0
}

/** MAIN CONSTRUCTOR **/
fun BasicInt(num: BigInteger) = object : BasicInt {
    override val number: BigInteger = num
}

fun BasicInt(i: Int): BasicInt = BasicInt(i.toString())

fun BasicInt(s: Short): BasicInt = BasicInt(s.toString())

fun BasicInt(b: Byte): BasicInt = BasicInt(b.toString())

fun BasicInt(l: Long): BasicInt = BasicInt(l.toString())

fun BasicInt(s: String): BasicInt = BasicInt(BigInteger(s))

fun BigInteger(b: Byte): BigInteger = BigInteger(b.toString())
fun BigInteger(s: Short): BigInteger = BigInteger(s.toString())
fun BigInteger(i: Int): BigInteger = BigInteger(i.toString())
fun BigInteger(l: Long): BigInteger = BigInteger(l.toString())

fun BigInteger(f: Float): BigInteger = BigInteger(f.toString())
fun BigInteger(d: Double): BigInteger = BigInteger(d.toString())


