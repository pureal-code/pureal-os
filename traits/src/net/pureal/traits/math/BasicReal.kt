package net.pureal.traits.math

public trait BasicReal : Number, Comparable<BasicReal> {
    override fun toDouble() : Double {
        return 0.0
    }

    override fun toFloat() : Float = toDouble().toFloat()

    override fun toLong() : Long {
        return 0
    }

    override fun toInt() : Int = toLong().toInt()
    override fun toShort() : Short = toLong().toShort()
    override fun toByte() : Byte = toLong().toByte()
    override fun toChar() : Char = toLong().toChar()

    override fun compareTo(other : BasicReal) : Int {
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

    fun compareExponentTo(other: BasicReal) : Long {
        return this.highestPossibleExponent() - other.highestPossibleExponent()
    }

    fun compareTo(other : Number) : Int {
        when (other) {
            is Byte -> return compareTo(basicReal(other))
            is Short -> return compareTo(basicReal(other))
            is Int -> return compareTo(basicReal(other))
            is Long -> return compareTo(basicReal(other))
            is Float -> return compareTo(basicReal(other))
            is Double -> return compareTo(basicReal(other))
            else -> throw IllegalArgumentException()
        }
    }

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
     * Explanation: number stores large int as array of 32 bit Integers
     * Each stands for 9 decimal digits (values 0 to 999999999 aka maxSimpleInt)
     * This makes 1. conversions to/from String easy and
     *            2. there will no problems with Integer signs
     *            (even 2 * maxSimpleInt is still positive)
     * to be exact: only 2^31 * 9 digits are possible, should be enough for any purpose
     *
     * the 10-exponent is Long as we do not expect exponents near 2^63
     */
    val exponent : Long
    val number : IntArray

    val sign : Boolean

    fun minus() : BasicReal = basicReal(number, exponent, !sign)

    override fun equals(other : Any?) : Boolean
    {
        when (other) {
            null -> return false
            is Number -> return compareTo(other)==0
            else -> return false
        }
    }

    fun highestPossibleExponent() : Long {
        return exponent
    }

    fun highestSignificantDigit() : Long {
        return exponent + number.size * 9
    }

    fun isInteger() : Boolean = exponent >= 0

    // TODO : Evaluate if highestSignificantDigit & Co. should be calculated at object generation as they are needed for calculations

}



fun basicReal(s : String) : BasicReal = basicReal(0) // TODO: THE important generation function

fun basicReal(num : IntArray, exp : Long = 0, sgn : Boolean = false) : BasicReal = object : BasicReal {
    override val number : IntArray = num
    override val sign : Boolean = sgn
    override val exponent : Long = exp
}

fun basicReal(bi : BasicInt, exp : Long) : BasicReal = object : BasicReal {
    override val number : IntArray = bi.number
    override val sign : Boolean = bi.sign
    override val exponent : Long = exp
}

fun basicReal(d : Double) : BasicReal = basicReal(d.toString())

fun basicReal(f : Float) : BasicReal = basicReal(f.toString())

fun basicReal(l : Long) : BasicReal = basicInt(l)

fun basicReal(i : Int) : BasicReal = basicInt(i)

fun basicReal(s : Short) : BasicReal = basicInt(s)

fun basicReal(b : Byte) : BasicReal = basicInt(b)

val maxSimpleInt =  999999999.toInt()
val basicDivisor = 1000000000.toInt()
