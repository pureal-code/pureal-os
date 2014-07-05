package net.pureal.traits.math

import java.math.BigInteger
import java.lang.Math.*
import kotlin.math.*


public trait BasicReal {
    

    /*********** CONVERSIONS **************/
    final fun toDouble() : Double = number.doubleValue() * exponentialFactor()

    final fun toFloat() : Float = number.floatValue() * exponentialFactor().toFloat()

    final fun toLong() : Long = number.longValue() * exponentialFactor().toLong()

    final fun toInt() : Int = toLong().toInt()
    final fun toShort() : Short = toLong().toShort()
    final fun toByte() : Byte = toLong().toByte()
    final fun toChar() : Char = toLong().toChar()

    fun toBigInteger() : BigInteger = number * BigInteger(exponentialFactor().toString())

    /*********** COMPARATOR FUNCTIONS ***********/
    open fun compareExponentTo(other : BasicReal) : Long {
        return this.exponent - other.exponent
    }

    open fun compareTo(other : Any?) : Int {
        when (other) {
            is Byte -> return compareTo(BasicReal(other))
            is Short -> return compareTo(BasicReal(other))
            is Int -> return compareTo(BasicReal(other))
            is Long -> return compareTo(BasicReal(other))
            is Float -> return compareTo(BasicReal(other))
            is Double -> return compareTo(BasicReal(other))
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

    open fun exponentialFactor() : Double = pow(10.0,exponent.toDouble())




    /********* BASIC OPERATIONS *********/
    open fun plus(other : Any?) : BasicReal {
        when (other) {
            is Byte -> return this + BasicInt(other)
            is Short -> return this + BasicInt(other)
            is Int -> return this + BasicInt(other)
            is Long -> return this + BasicInt(other)
            is Double -> return this + BasicReal(other)
            is Float -> return this + BasicReal(other)
            is BasicReal -> {
                return this
            }
            else -> throw IllegalArgumentException()
        }
    }

    open fun minus(other : Any?) : BasicReal {
        when (other) {
            is Byte -> return this - BasicInt(other)
            is Short -> return this - BasicInt(other)
            is Int -> return this - BasicInt(other)
            is Long -> return this - BasicInt(other)
            is Double -> return this - BasicReal(other)
            is Float -> return this - BasicReal(other)
            is BasicReal -> {
                return this
            }
            else -> throw IllegalArgumentException()
        }
    }

    open fun times(other : Any?) : BasicReal {
        when (other) {
            is Byte -> return this * BasicInt(other)
            is Short -> return this * BasicInt(other)
            is Int -> return this * BasicInt(other)
            is Long -> return this * BasicInt(other)
            is Double -> return this * BasicReal(other)
            is Float -> return this * BasicReal(other)
            is BasicReal -> {
                return this
            }
            else -> throw IllegalArgumentException()
        }
    }

    open fun div(other : Any?) : BasicReal {
        when (other) {
            is Byte -> return this / BasicInt(other)
            is Short -> return this / BasicInt(other)
            is Int -> return this / BasicInt(other)
            is Long -> return this / BasicInt(other)
            is Double -> return this / BasicReal(other)
            is Float -> return this / BasicReal(other)
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
    val number : BigInteger
    val exponent : Long

    final val sign : Int get() = number.signum()


    open fun minus() : BasicReal = BasicReal(-number, exponent)

    override fun equals(other : Any?) : Boolean
    {
        when (other) {
            null -> return false
            is Number -> return compareTo(other)==0
            is BigInteger -> return other==toBigInteger()
            else -> return false
        }
    }

    open fun isInteger() : Boolean = exponent >= 0

    final fun signum() : Int = number.signum()

    final fun setToExponent(exp : Long) : BasicReal {
        if (exp == exponent) return this
        return BasicReal(number / BigInteger(pow(10.0,(exp-exponent).toDouble()).toString()))
    }
}



fun BasicReal(s : String) : BasicReal {
    var str : String = s.capitalize()
    var estr : String
    // with regex - remove illegal characters and whitespace
    if(str.matches("[^0-9\\.\\-\\+E\\s]")) throw IllegalArgumentException("There are forbidden characters in the expression")



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

    return BasicReal (num, exp)
}

fun BasicReal(num : BigInteger, exp : Long) = object : BasicReal {
    override val number : BigInteger = num
    override val exponent : Long = exp
}

fun BasicReal(num : BigInteger) : BasicReal = BasicReal (num, 0)
fun BasicReal(bi : BasicInt, exp : Long) : BasicReal = BasicReal(bi.number, exp)



fun BasicReal(d : Double) : BasicReal = BasicReal(d.toString())
fun BasicReal(f : Float) : BasicReal = BasicReal(f.toString())
fun BasicReal(l : Long) : BasicReal = BasicReal(l.toString())
fun BasicReal(i : Int) : BasicReal = BasicReal(i.toString())
fun BasicReal(s : Short) : BasicReal = BasicInt(s.toString())
fun BasicReal(b : Byte) : BasicReal = BasicInt(b.toString())

/*
fun BigInteger(i : Int) : BigInteger = BigInteger(i.toString())
fun BigInteger(l : Long) : BigInteger = BigInteger(l.toString())
*/
