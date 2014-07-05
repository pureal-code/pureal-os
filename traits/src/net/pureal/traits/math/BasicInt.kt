package net.pureal.traits.math

import java.math.BigInteger
import kotlin.math.*


/** Basic Int:
 *  Subtype of BasicReal that is always Integer with exp 0
 *  to facilitate calculations ...
 */
public trait BasicInt : BasicReal {

    override val exponent : Long get() = 0

    fun toBasicReal() : BasicReal = basicReal(this, 0)

    override fun minus() : BasicInt = basicInt(-number)

    override fun isInteger() : Boolean = true
}

// It must still be evaluated if the signed Int makes problems and if wae can use an unsigned Int or if we need w/e
fun basicInt(i : Int) : BasicInt = basicInt(i.toString())

fun basicInt(s : Short) : BasicInt = basicInt(s.toString())

fun basicInt(b : Byte) : BasicInt = basicInt(b.toString())

fun basicInt(l : Long) : BasicInt = basicInt(l.toString())

fun basicInt(s : String) : BasicInt = object : BasicInt {
    override val number : BigInteger = BigInteger(s)
}

fun basicInt(num : BigInteger) : BasicInt = object : BasicInt {
    override val number = num
}

