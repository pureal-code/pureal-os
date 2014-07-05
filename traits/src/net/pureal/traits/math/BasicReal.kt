package net.pureal.traits.math

import java.math.BigInteger
import java.lang.Math.*
import kotlin.math.*


public trait BasicReal : Number {
    /*********** CONVERSIONS **************/
    override fun toDouble() : Double = number.doubleValue() * exponentialFactor()

    override fun toFloat() : Float = number.floatValue() * exponentialFactor().toFloat()

    override fun toLong() : Long = number.longValue() * exponentialFactor().toLong()

    override fun toInt() : Int = toLong().toInt()
    override fun toShort() : Short = toLong().toShort()
    override fun toByte() : Byte = toLong().toByte()
    override fun toChar() : Char = toLong().toChar()

    fun toBigInteger() : BigInteger = number * BigInteger(exponentialFactor().toString())

    /*********** COMPARATOR FUNCTIONS ***********/
    fun compareExponentTo(other : BasicReal) : Long {
        return this.exponent - other.exponent
    }

    fun compareTo(other : Number) : Int {
        when (other) {
            is Byte -> return compareTo(basicReal(other))
            is Short -> return compareTo(basicReal(other))
            is Int -> return compareTo(basicReal(other))
            is Long -> return compareTo(basicReal(other))
            is Float -> return compareTo(basicReal(other))
            is Double -> return compareTo(basicReal(other))
            is BasicReal -> {
                if(this.sign == 0) return -other.sign
                if(this.sign != other.sign){
                    return this.sign-other.sign
                }
                if(compareExponentTo(other) != 0L) return compareExponentTo(other).toInt() * this.sign
                return (this-other).number.compareTo(BigInteger.ZERO) * this.sign
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun exponentialFactor() : Double = pow(10.0,exponent.toDouble())




    /********* BASIC OPERATIONS *********/
    fun plus(other : Number) : BasicReal {
        when (other) {
            is Byte -> return this + basicInt(other)
            is Short -> return this + basicInt(other)
            is Int -> return this + basicInt(other)
            is Long -> return this + basicInt(other)
            is Double -> return this + basicReal(other)
            is Float -> return this + basicReal(other)
            is BasicReal -> {
                return this
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun minus(other : Number) : BasicReal {
        when (other) {
            is Byte -> return this - basicInt(other)
            is Short -> return this - basicInt(other)
            is Int -> return this - basicInt(other)
            is Long -> return this - basicInt(other)
            is Double -> return this - basicReal(other)
            is Float -> return this - basicReal(other)
            is BasicReal -> {
                return this
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun times(other : Number) : BasicReal {
        when (other) {
            is Byte -> return this * basicInt(other)
            is Short -> return this * basicInt(other)
            is Int -> return this * basicInt(other)
            is Long -> return this * basicInt(other)
            is Double -> return this * basicReal(other)
            is Float -> return this * basicReal(other)
            is BasicReal -> {
                return this
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun div(other : Number) : BasicReal {
        when (other) {
            is Byte -> return this / basicInt(other)
            is Short -> return this / basicInt(other)
            is Int -> return this / basicInt(other)
            is Long -> return this / basicInt(other)
            is Double -> return this / basicReal(other)
            is Float -> return this / basicReal(other)
            is BasicReal -> {
                return this
            }
            else -> throw IllegalArgumentException()
        }
    }

    /**
     * This is real magic:
     *
     * number stores a big Integer ...
     * and exponent is the 10-exponent - is Long as we do not expect exponents near 2^63
     */
    val exponent : Long
    val number : BigInteger

    val sign : Int get() = number.signum()


    fun minus() : BasicReal = basicReal(-number, exponent)

    override fun equals(other : Any?) : Boolean
    {
        when (other) {
            null -> return false
            is Number -> return compareTo(other)==0
            is BigInteger -> return other==toBigInteger()
            else -> return false
        }
    }

    fun isInteger() : Boolean = exponent >= 0

    fun signum() : Int = number.signum()

    fun setToExponent(exp : Long) : BasicReal {
        if (exp == exponent) return this
        return basicReal(number / BigInteger(pow(10.0,(exp-exponent).toDouble()).toString()))
    }
}



fun basicReal(s : String) : BasicReal {
    var str : String = s.capitalize()
    var estr : String
    // with regex - remove illegal characters and whitespace
    str = str.replaceAll("[^0-9\\.\\-\\+E]","")



    // look if we have exponent
    var epos : Int = str.indexOf("E")
    var exp : Long = 0
    if(epos != -1){
        estr = str.substring(epos+1)
        exp = estr.toLong()
        str = str.substring(0,epos)
    }
    // now remove the "dot of the number"
    epos = str.indexOf(".")
    if(epos != -1){
        exp -= (str.length - epos - 1) // reduce by number of characters after '.'
        str = str.replaceAll("[\\.]","") // remove dot
    }
    // remove tailing zeros
    while(str.last()=='0')
    {
        str = str.substring(0,str.length-1)
        exp++
    }
    val num : BigInteger = BigInteger(str)

    return object : BasicReal {
        override val number : BigInteger = num
        override val exponent : Long = exp
    }
}

fun basicReal(num : BigInteger, exp : Long = 0) : BasicReal = object : BasicReal {
    override val number : BigInteger = num
    override val exponent : Long = exp
}

fun basicReal(bi : BasicInt, exp : Long) : BasicReal = object : BasicReal {
    override val number : BigInteger = bi.number
    override val exponent : Long = exp
}



fun basicReal(d : Double) : BasicReal = basicReal(d.toString())
fun basicReal(f : Float) : BasicReal = basicReal(f.toString())
fun basicReal(l : Long) : BasicReal = basicReal(l.toString())
fun basicReal(i : Int) : BasicReal = basicReal(i.toString())
fun basicReal(s : Short) : BasicReal = basicInt(s.toString())
fun basicReal(b : Byte) : BasicReal = basicInt(b.toString())

fun BigInteger(i : Int) : BigInteger = BigInteger(i.toString())
fun BigInteger(l : Long) : BigInteger = BigInteger(l.toString())
