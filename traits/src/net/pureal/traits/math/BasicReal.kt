package net.pureal.traits.math

import java.math.BigInteger
import java.lang.Math.*
import kotlin.math.*


public trait BasicReal : InternalReal {
    class object {
        final fun getLowestExponent(o1: BasicReal, o2: BasicReal): Long = min(o1.exponent, o2.exponent)
        final fun exponentialFactor(exp: Long): BigInteger = BigInteger.TEN.pow(abs(exp.toInt()))

        final fun fromString(s : String) : BasicReal {
            var str: String = s.capitalize()
            var estr: String
            // with regex - remove illegal characters and whitespace
            if (str.matches("[^0-9\\.\\-\\+E\\s]")) throw IllegalArgumentException("There are forbidden characters in the expression")

            // get sign of number
            var sgnstr = ""
            if (str.first() == '-') {
                sgnstr = "-"
                str = str.substring(1)
            }


            // look if we have exponent
            var epos: Int = str.indexOf("E")
            var exp: Long = 0
            if (epos != -1) {
                estr = str.substring(epos + 1)
                exp = estr.toLong()
                str = str.substring(0, epos)
            }
            // now remove the "dot of the number"
            epos = str.indexOf(".")
            if (epos != -1) {
                exp -= (str.length - epos - 1) // reduce by number of characters after '.'
                str = str.replaceAll("[\\.]", "") // remove dot
            }
            // remove leading zeros
            while (str.first() == '0' && str.length > 1) {
                str = str.substring(1)
            }
            // remove tailing zeros
            while (str.last() == '0' && str.length > 1) {
                str = str.substring(0, str.length - 1)
                exp++
            }
            return basicReal (BigInteger (sgnstr + str), exp)
        }
    }

    /*********** CONVERSIONS **************/
    final override fun toDouble(): Double = number.doubleValue() * doubleExponentialFactor()

    final override fun toFloat(): Float = number.floatValue() * doubleExponentialFactor().toFloat()

    final override fun toLong(): Long = number.longValue() * doubleExponentialFactor().toLong()

    final override fun toInt(): Int = toLong().toInt()
    final override fun toShort(): Short = toLong().toShort()
    final override fun toByte(): Byte = toLong().toByte()
    final override fun toChar(): Char = toLong().toChar()

    override fun toString(): String {
        return "BasicReal(\"${toMathematicalString()}\")"
    }

    override fun toMathematicalString(): String {
        if (number == BigInteger.ZERO) return "0"
        var s: String = number.toString()
        val s_begin = when {
            s.first() == '-' -> 1
            else -> 0
        }
        if (exponent + s.length in -2..7) {
            if (exponent >= 0) return (number * exponentialFactor()).toString()
            var dotpos: Int = s.length + exponent.toInt()
            while (dotpos < s_begin) {
                dotpos++
                s = s.substring(0, s_begin) + "0" + s.substring(s_begin, s.length)
            }
            return s.substring(0, dotpos) + "." + s.substring(dotpos, s.length)
        }
        var dotpos = 1 + s_begin
        var exp = exponent + s.length - dotpos
        s = when {
            dotpos != s.length -> s.substring(0, dotpos) + "." + s.substring(dotpos, s.length)
            else -> s
        }
        val esgn: String = when {
            exp >= 0 -> "+"
            else -> "-"
        }
        return s + "E" + esgn + abs(exp).toString()
    }

    final fun toBigInteger(): BigInteger {
        return when {
            exponent > 0 -> number * exponentialFactor()
            else -> number / exponentialFactor()
        }
    }

    /*********** COMPARATOR FUNCTIONS ***********/
    open fun compareExponentTo(other: BasicReal): Long {
        return this.exponent - other.exponent
    }

    override fun compareTo(other: Any?): Int {
        when (other) {
            is Byte -> return compareTo(basicReal(other))
            is Short -> return compareTo(basicReal(other))
            is Int -> return compareTo(basicReal(other))
            is Long -> return compareTo(basicReal(other))
            is Float -> return compareTo(basicReal(other))
            is Double -> return compareTo(basicReal(other))
            is BigInteger -> return compareTo(basicReal(other))
            is BasicReal -> {
                if (this.signum() == 0) return -other.signum()
                if (this.signum() != other.signum()) {
                    return this.signum() - other.signum()
                }
                return (this - other).number.compareTo(BigInteger.ZERO)
            }
            is InternalReal -> return other.compareTo(this)
            else -> throw IllegalArgumentException()
        }
    }


    fun exponentialFactor(): BigInteger = BasicReal.exponentialFactor(exponent)
    fun doubleExponentialFactor(): Double = pow(10.0, exponent.toDouble())


    /********* BASIC OPERATIONS *********/
    override fun plus(other: Any?): BasicReal {
        when (other) {
            is Byte -> return this + basicInt(other)
            is Short -> return this + basicInt(other)
            is Int -> return this + basicInt(other)
            is Long -> return this + basicInt(other)
            is Double -> return this + basicReal(other)
            is Float -> return this + basicReal(other)
            is BasicReal -> {
                val minexp = getLowestExponent(this, other)
                val br1 = this.setToExponent(minexp)
                val br2 = other.setToExponent(minexp)
                return basicReal(br1.number + br2.number, minexp).minimize()
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun minus(other: Any?): BasicReal {
        when (other) {
            is Byte -> return this - basicInt(other)
            is Short -> return this - basicInt(other)
            is Int -> return this - basicInt(other)
            is Long -> return this - basicInt(other)
            is Double -> return this - basicReal(other)
            is Float -> return this - basicReal(other)
            is BasicReal -> {
                val minexp = getLowestExponent(this, other)
                val br1 = this.setToExponent(minexp)
                val br2 = other.setToExponent(minexp)
                return basicReal(br1.number - br2.number, minexp).minimize()
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun times(other: Any?): BasicReal {
        when (other) {
            is Byte -> return this * basicInt(other)
            is Short -> return this * basicInt(other)
            is Int -> return this * basicInt(other)
            is Long -> return this * basicInt(other)
            is Double -> return this * basicReal(other)
            is Float -> return this * basicReal(other)
            is BasicReal -> {
                return basicReal(
                        this.number * other.number,
                        this.exponent + other.exponent
                ).minimize()
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun div(other: Any?): BasicReal {
        when (other) {
            is Byte -> return this / basicInt(other)
            is Short -> return this / basicInt(other)
            is Int -> return this / basicInt(other)
            is Long -> return this / basicInt(other)
            is Double -> return this / basicReal(other)
            is Float -> return this / basicReal(other)
            is BasicReal -> {
                if (other.number == BigInteger.ZERO) throw ArithmeticException("A Division by Zero is not allowed")
                val targetExp = exponent - other.exponent
                val br1 = setToExponent(exponent - activeEnvironment.accuracy)
                val c = br1.number.divideAndRemainder(other.number)
                if (activeEnvironment.requireExactCalculation && c[1] != BigInteger.ZERO)
                    throw RuntimeException("Accurate Division is not possible")
                return basicReal(c[0], targetExp - activeEnvironment.accuracy).minimize()
            }
            else -> throw IllegalArgumentException()
        }
    }
    // TODO: do mod on the Calculatable class
    fun mod(other: Any?): BasicReal {
        when (other) {
            is Byte -> return this % basicInt(other)
            is Short -> return this % basicInt(other)
            is Int -> return this % basicInt(other)
            is Long -> return this % basicInt(other)
            is Double -> return this % basicReal(other)
            is Float -> return this % basicReal(other)
            is BasicReal -> {
                val minexp = getLowestExponent(this, other)
                val br1 = this.setToExponent(minexp)
                val br2 = other.setToExponent(minexp)
                return basicReal(br1.number mod br2.number, minexp)
            }
            else -> throw IllegalArgumentException()
        }
    }

    open fun minimize(): BasicReal {
        if (this.number == BigInteger.ZERO) return basicReal(this.number, 0)
        var stepSize: Int = activeEnvironment.accuracy / 4
        var powers: Long = 0
        var tmp: BigInteger = this.number
        var done: Boolean = false
        do {
            var p = BigInteger.TEN.pow(stepSize)
            var c = array(tmp, BigInteger.ZERO)
            while (c[1] == BigInteger.ZERO) {
                tmp = c[0]
                powers += stepSize
                c = tmp.divideAndRemainder(p)
            }
            powers -= stepSize
            if (stepSize == 1) done = true
            else {
                stepSize /= 2
            }
        } while (!done)
        return basicReal(tmp, exponent + powers)
    }

    /**
     * This is real magic:
     *
     * number stores a big Integer ...
     * and exponent is the 10-exponent - is Long as we do not expect exponents near 2^63
     */
    val number: BigInteger
    val exponent: Long

    override fun minus(): BasicReal = basicReal(-number, exponent)

    override fun equals(other: Any?): Boolean {
        when (other) {
            null -> return false
            is Number -> return compareTo(other) == 0
            is BasicReal -> return compareTo(other) == 0
            else -> return false
        }
    }

    override fun isInteger(): Boolean = exponent >= 0

    final override fun signum(): Int = number.signum()

    final fun getLowestExponent(o1: BasicReal, o2: BasicReal) = BasicReal.getLowestExponent(o1, o2)

    final fun setToExponent(exp: Long): BasicReal {
        return when {
            exp == exponent -> this
            exp > exponent -> basicReal(number / exponentialFactor(exp - exponent), exp)
            else -> basicReal(number * exponentialFactor(exp - exponent), exp)
        }
    }

    override fun floor(): BasicReal = if(isInteger()) this; else {
        if(sign) (setToExponent(0)-1).minimize(); else setToExponent(0).minimize() // TODO
    }
    override fun ceil(): BasicReal = if(isInteger()) this; else {
        if(sign) setToExponent(0).minimize(); else (setToExponent(0)+1).minimize() // TODO
    }
    override fun round(): BasicReal = if(isInteger()) this; else {
        if(this % basicInt(1) < basicReal(BigInteger(5),-1L)) floor(); else ceil()
    }

    override fun abs(): BasicReal = if(sign) -this; else this
}

/** MAIN CONSTRUCTOR **/
fun basicReal(num: BigInteger, exp: Long): BasicReal = object : BasicReal, Calculatable() {
    override val number: BigInteger = num
    override val exponent: Long = exp
}

fun basicReal(it: Any?): BasicReal {
    return when(it) {
        is BasicReal -> it.minimize()
        is String -> BasicReal.fromString(it)
        is Long -> basicReal(BigInteger(it), 0).minimize()
        is Int -> basicReal(it.toLong())
        is Short -> basicReal(it.toLong())
        is Byte -> basicReal(it.toLong())
        is BigInteger -> basicReal(it, 0).minimize()
        is RealPrimitive -> basicReal(it.value)
        is Number -> basicReal(it.toString())
        else -> throw IllegalArgumentException("Cannot create a BasicReal of given '{$it}'")
    }
}


