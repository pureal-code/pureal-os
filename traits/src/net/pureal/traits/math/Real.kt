package net.pureal.traits.math

import net.pureal.traits.math.operations.additionValue
import net.pureal.traits.math.operations.multiplicationValue
import net.pureal.traits.math.operations.subtractionValue
import net.pureal.traits.math.operations.divisionValue

// TODO: cast to Number when compiler works correctly with it
public trait Real {
    val isApproximate : Boolean get() = false

    fun simplify() : Real {
        // TODO: return sympy.simplify(toMathematicalString()) - is to be inherited by all sub-traits in the end
        return this
    }

    fun approximate(accuracy : Int = 50) : Real = this

    fun getPrimitive() : Number = approximate().Number() // TODO: make it basicReal or RealPrimitive

    override fun equals(other : Any?) : Boolean
    {
        when (other)
        {
            null -> return false
            is Real -> {
                if(this is RealPrimitive && other is RealPrimitive)
                {
                    return this.getPrimitive() == other.getPrimitive()
                }
                return false
                // return this.approximate() == other.approximate()
                // TODO: actually pretty dirty checking, to be refined later
            }
            is Number -> {
                return this.getPrimitive() == other
            }
        }
        return false
    }

    fun plus(other: Real) : Real = additionValue(this,other)

    fun minus() : Real = subtractionValue(0.toReal(), this)

    fun minus(other: Real) : Real = subtractionValue(this,other)

    fun times(other: Real) : Real = multiplicationValue(this,other)

    fun div(other: Real) : Real = divisionValue(this,other)

    fun invert() : Real = divisionValue(1.toReal(),this)


    fun toDouble() : Double = getPrimitive().toDouble()
    fun toFloat() : Float = toDouble().toFloat()
    fun toLong() : Long = getPrimitive().toLong()
    fun toInt() : Int = toLong().toInt()
    fun toShort() : Short = toLong().toShort()
    fun toByte() : Byte = toLong().toByte()
    fun toChar() : Char = toLong().toChar()

    fun Number() : Number
    {
        throw UnsupportedOperationException()
    }


}