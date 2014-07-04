package net.pureal.traits.math

import com.sun.javaws.exceptions.InvalidArgumentException

public trait BasicInt : Number, Comparable<BasicInt> {
    override fun toDouble() : Double {
        return 0.0
    }
    override fun toFloat() : Float {
        return toDouble().toFloat()
    }
    override fun toLong() : Long {
        return 0
    }
    override fun toInt() : Int {
        return toLong().toInt()
    }
    override fun toShort() : Short {
        return toLong().toShort()
    }
    override fun toByte() : Byte {
        return toLong().toByte()
    }
    override fun toChar() : Char {
        return toLong().toChar()
    }

    override fun compareTo(other : BasicInt) : Int {
        if(other is BasicReal) return other.compareTo(this)
        if(this.sign != other.sign){
            return when {
                this.equals(0) -> 0
                this.sign -> 1
                else -> -1
            }
        }
        return 0
    }

    fun compareTo(other : Number) : Int {
        when (other) {
            is Byte -> return compareTo(basicInt(other))
            is Short -> return compareTo(basicInt(other))
            is Int -> return compareTo(basicInt(other))
            is Long -> return compareTo(basicInt(other))
            is Float -> return compareTo(basicReal(other))
            is Double -> return compareTo(basicReal(other))
            else -> throw IllegalArgumentException()
        }
    }

    /**
     * Explanation: value stores large int as array of 32 bit Integers
     * Each stands for 9 decimal digits (values 0 to 999999999 aka maxSimpleInt)
     * This makes 1. conversions to/from String easy and
     *            2. there will no problems with Integer signs
     *            (even 2 * maxSimpleInt is still positive)
     * to be exact: only 2^31 * 9 digits are possible
     */
    val number : IntArray

    val sign: Boolean

    fun toBasicReal() : BasicReal = basicReal(this, basicInt(0))

    fun minus() : BasicInt = basicInt(number, !sign)

    fun plus(other : Number) : BasicInt {
        when (other) {
            is Byte -> return this + basicInt(other)
            is Short -> return this + basicInt(other)
            is Int -> return this + basicInt(other)
            is Long -> return this + basicInt(other)
            is Double -> return basicReal(other) + this
            is Float -> return basicReal(other) + this
            is BasicReal -> return other.plus(this) // BasicReal + BasicInt is overridden there
            is BasicInt -> {
                return this // TODO:
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun minus(other : Number) : Number {
        when (other) {
            is Byte -> return this - basicInt(other)
            is Short -> return this - basicInt(other)
            is Int -> return this - basicInt(other)
            is Long -> return this - basicInt(other)
            is Double -> return basicReal(other) - this
            is Float -> return basicReal(other) - this
            is BasicReal -> return other.minus(this) // BasicReal + BasicInt is overridden there
            is BasicInt -> {
                return this // TODO:
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun times(other : Number) : Number {
        when (other) {
            is Byte -> return this * basicInt(other)
            is Short -> return this * basicInt(other)
            is Int -> return this * basicInt(other)
            is Long -> return this * basicInt(other)
            is Double -> return basicReal(other) * this
            is Float -> return basicReal(other) * this
            is BasicReal -> return other.times(this) // BasicReal + BasicInt is overridden there
            is BasicInt -> {
                return this // TODO:
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun div(other : Number) : Number {
        when (other) {
            is Byte -> return this / basicInt(other)
            is Short -> return this / basicInt(other)
            is Int -> return this / basicInt(other)
            is Long -> return this / basicInt(other)
            is Double -> return basicReal(other) / this
            is Float -> return basicReal(other) / this
            is BasicReal -> return other.div(this) // BasicReal + BasicInt is overridden there
            is BasicInt -> {
                return this // TODO:
            }
            else -> throw IllegalArgumentException()
        }
    }


    override fun equals(other: Any?) : Boolean {
        when(other){
            null -> return false
            is Number -> return compareTo(other)==0
            else -> return false
        }
    }

    fun isInteger() : Boolean = true
}

// It must still be evaluated if the signed Int makes problems and if wae can use an unsigned Int or if we need w/e
fun basicInt(i : Int) : BasicInt = basicInt(i.toLong())

fun basicInt(s : Short) : BasicInt = basicInt(s.toLong())

fun basicInt(b : Byte) : BasicInt = basicInt(b.toLong())

fun basicInt(l : Long) : BasicInt {
    var l = l
    var arr: IntArray = IntArray(0)
    var sgn: Boolean = l < 0
    if(sgn) l = -l
    var index : Int = 0
    while(l > maxSimpleInt){
        val dig : Int = (l % basicDivisor).toInt()
        arr[index] = dig
        l = l / basicDivisor
        index++
    }
    arr[index] = l.toInt()

    return object : BasicInt {
        override val number : IntArray = arr
        override val sign : Boolean = sgn
    }
}

fun basicInt(s : String) : BasicInt {
    // TODO: control w/ regex if s only has numbers
    return basicInt(s.toLong())
}

fun basicInt(num : IntArray, sgn: Boolean = false) : BasicInt {
    // TODO: check if num has desired format
    return object : BasicInt {
        override val number = num
        override val sign = sgn
    }
}

val maxSimpleInt =  999999999.toInt()
val basicDivisor = 1000000000.toInt()