package net.pureal.traits.math

import java.math.BigInteger
import java.lang.Math.*
import kotlin.math.*


public trait BasicReal {
    class object {
        var accuracy : Int = 100
        final fun getLowestExponent(o1 : BasicReal, o2: BasicReal) : Long = min(o1.exponent,o2.exponent)
        final fun exponentialFactor(exp : Long) : BigInteger = BigInteger.TEN.pow(abs(exp.toInt()))
    }

    /*********** CONVERSIONS **************/
    final fun toDouble() : Double = number.doubleValue() * doubleExponentialFactor()

    final fun toFloat() : Float = number.floatValue() * doubleExponentialFactor().toFloat()

    final fun toLong() : Long = number.longValue() * doubleExponentialFactor().toLong()

    final fun toInt() : Int = toLong().toInt()
    final fun toShort() : Short = toLong().toShort()
    final fun toByte() : Byte = toLong().toByte()
    final fun toChar() : Char = toLong().toChar()

    override fun toString() : String {
        if(number == BigInteger.ZERO) return "BasicReal(0)"
        if(exponent == 0L) return "BasicReal(\"${number.toString()}\")"
        val esgn : String = when {
            exponent >= 0 -> "+"
            else -> "-"
        }
        return "BasicReal(\"${number.toString()}E${esgn}${abs(exponent).toString()}\")"
    }

    open fun toMathematicalString() : String {
        if(number == BigInteger.ZERO) return "0"
        var s : String = number.toString()
        val s_begin = when {
            s.first() == '-' -> 1
            else -> 0
        }
        if(exponent+s.length in -2..7) {
            if(exponent >= 0) return (number*exponentialFactor()).toString()
            var dotpos : Int = s.length + exponent.toInt()
            while(dotpos < s_begin) {
                dotpos++
                s = s.substring(0, s_begin) + "0" + s.substring(s_begin, s.length)
            }
            return s.substring(0, dotpos)  + "." + s.substring(dotpos, s.length)
        }
        var dotpos = 1 + s_begin
        var exp = exponent + s.length - dotpos
        s = when {
            dotpos != s.length -> s.substring(0, dotpos) + "." + s.substring(dotpos, s.length)
            else -> s
        }
        val esgn : String = when {
            exp >= 0 -> "+"
            else -> "-"
        }
        return s + "E" + esgn + abs(exp).toString()
    }

    final fun toBigInteger() : BigInteger {
        return when {
            exponent > 0 -> number * exponentialFactor()
            else -> number / exponentialFactor()
        }
    }

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
                if(this.signum() == 0) return -other.signum()
                if(this.signum() != other.signum()){
                    return this.signum()-other.signum()
                }
                if(compareExponentTo(other) != 0L) return compareExponentTo(other).toInt() * this.signum()
                return (this-other).number.compareTo(BigInteger.ZERO) * this.signum()
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun exponentialFactor() : BigInteger = BasicReal.exponentialFactor(exponent)
    fun doubleExponentialFactor() : Double = pow(10.0, exponent.toDouble())


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
                val minexp = getLowestExponent(this, other)
                val br1 = this.setToExponent(minexp)
                val br2 = other.setToExponent(minexp)
                return BasicReal(br1.number + br2.number, minexp)
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
                val minexp = getLowestExponent(this, other)
                val br1 = this.setToExponent(minexp)
                val br2 = other.setToExponent(minexp)
                return BasicReal(br1.number - br2.number, minexp)
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
                return BasicReal(
                        this.number * other.number,
                        this.exponent + other.exponent
                ).minimize()
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

    open fun minimize() : BasicReal {
        if(this.number == BigInteger.ZERO) return BasicReal(this.number, 0)
        var stepSize : Int = accuracy / 4
        var powers : Long = 0
        var tmp : BigInteger = this.number
        var done : Boolean = false
        do {
            var p = BigInteger.TEN.pow(stepSize)
            var c = array(tmp, BigInteger.ZERO)
            while(c[1] == BigInteger.ZERO){
                tmp = c[0]
                powers += stepSize
                c = tmp.divideAndRemainder(p)
            }
            powers -= stepSize
            if(stepSize == 1) done = true
            else {
                stepSize /= 2
            }
        } while (!done)
        return BasicReal(tmp, exponent + powers)
    }

    /**
     * This is real magic:
     *
     * number stores a big Integer ...
     * and exponent is the 10-exponent - is Long as we do not expect exponents near 2^63
     */
    val number : BigInteger
    val exponent : Long

    final val sign : Boolean get() = signum() < 0


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

    final fun getLowestExponent(o1: BasicReal, o2: BasicReal) = BasicReal.getLowestExponent(o1, o2)

    final fun setToExponent(exp : Long) : BasicReal {
        return when {
            exp == exponent -> this
            exp > exponent -> BasicReal(number / exponentialFactor(exp-exponent), exp)
            else -> BasicReal(number * exponentialFactor(exp-exponent), exp)
        }
    }
}



fun BasicReal(s : String) : BasicReal {
    var str : String = s.capitalize()
    var estr : String
    // with regex - remove illegal characters and whitespace
    if(str.matches("[^0-9\\.\\-\\+E\\s]")) throw IllegalArgumentException("There are forbidden characters in the expression")

    // get sign of number
    var sgnstr = ""
    if(str.first()=='-') {
        sgnstr = "-"
        str = str.substring(1)
    }


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
    // remove leading zeros
    while(str.first()=='0' && str.length > 1)
    {
        str = str.substring(1)
    }
    // remove tailing zeros
    while(str.last()=='0' && str.length > 1)
    {
        str = str.substring(0,str.length-1)
        exp++
    }
    return BasicReal (BigInteger (sgnstr + str), exp)
}

/** MAIN CONSTRUCTOR **/
fun BasicReal(num : BigInteger, exp : Long) = object : BasicReal {
    override val number : BigInteger = num
    override val exponent : Long = exp
}

fun BasicReal(num : BigInteger) : BasicReal = BasicReal (num, 0)
fun BasicReal(bi : BasicInt, exp : Long = 0) : BasicReal = BasicReal(bi.number, exp)



fun BasicReal(d : Double) : BasicReal = BasicReal(d.toString())
fun BasicReal(f : Float) : BasicReal = BasicReal(f.toString())

fun BasicReal(l : Long) : BasicReal = BasicReal(BigInteger(l), 0).minimize()
fun BasicReal(i : Int) : BasicReal = BasicReal(i.toLong())
fun BasicReal(s : Short) : BasicReal = BasicInt(s.toLong())
fun BasicReal(b : Byte) : BasicReal = BasicInt(b.toLong())


