package net.pureal.traits.math

import net.pureal.traits.math.operations.summationValue
import net.pureal.traits.math.operations.multiplicationValue
import net.pureal.traits.math.operations.subtractionValue
import net.pureal.traits.math.operations.divisionValue

// TODO: cast to Number when compiler works correctly with it
public trait Real {
    val isApproximate : Boolean get() = false
    val isPrimitive : Boolean get() = false // TODO: maybe remove - this is actually bullshit


    fun simplify() : Real {
        // TODO: return sympy.simplify(toMathematicalString()) - is to be inherited by all sub-traits in the end
        return this
    }

    fun toMathematicalString() : String

    fun approximate(accuracy : Int = 50) : Real = this

    fun getOuterMathString(outerPriority : Int) : String = toMathematicalString()
    fun getOuterString(outerPriority : Int) : String = toString()

    fun getPrimitive() : Number = approximate().Number() // TODO: make it basicReal or RealPrimitive

    override fun equals(other : Any?) : Boolean
    {
        when (other)
        {
            null -> return false
            is Number -> {
                return this.toDouble() == other.toDouble()
            }
            is Real -> {
                if(isPrimitive && other.isPrimitive)
                {
                    return this.getPrimitive() == other.getPrimitive()
                }
                return false
                // return this.approximate() == other.approximate()
                // TODO: actually pretty dirty checking, to be refined later
                // TODO: More condition checking to do
            }

        }
        return false
    }

    fun plus(other: Real) : Real = summationValue(this,other)

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