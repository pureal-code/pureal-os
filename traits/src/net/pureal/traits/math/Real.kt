package net.pureal.traits.math

import net.pureal.traits.math.operations.summationValue
import net.pureal.traits.math.operations.multiplicationValue


public trait Real {
    val isApproximate : Boolean get() = false
    val isPrimitive : Boolean get() = false

    override fun toString(): String

    fun simplify() : Real {
        // TODO: return sympy.simplify(toString()) - is to be inherited by all sub-traits in the end
        return this
    }

    fun approximate() : Real {
        return this
    }

    fun getEncapsulatedString(outerPriority : Int) : String {
        return toString()
    }

    fun getPrimitiveValue() : Number {
        return approximate().Number()
    }

    override fun equals(other : Any?) : Boolean
    {
        when (other)
        {
            null -> return false
            is Number -> {
                return this.approximate().getPrimitiveValue() == other.toDouble()
            }
            is Real -> {
                if(isPrimitive && other.isPrimitive)
                {
                    return this.getPrimitiveValue() == other.getPrimitiveValue()
                }
                return false
                return this.approximate() == other.approximate() // TODO: actually pretty dirty checking, to be refined later
                // TODO: More condition checking to do
            }

        }
        return false
    }

    fun plus(other: Real) : Real = summationValue(this,other)

    fun minus() : Real

    fun minus(other: Real) : Real = summationValue(this,-other)

    fun times(other: Real) : Real = multiplicationValue(this,other)

    // TODO: for compatibility's sake - to be removed after we have our lossless number type
    fun toDouble() : Double = getPrimitiveValue().toDouble()

    fun Number() : Number
    {
        throw UnsupportedOperationException()
    }


}
