package net.pureal.traits.math

import net.pureal.traits.math.operations.summationValue


public trait Real {
    val isApproximate : Boolean // stores if that is an approximate value
        get() = false
    val isPrimitive : Boolean // stores if that value is a primitive or needs to be calculated out
        get() = false

    override fun toString(): String


    /**
     * Function that simplifies the expression
     */
    fun simplify() : Real {
        return this;
    }

    /**
     * Function that calculates an approximate (primitive) value for a statement
     */
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
                return this.approximate().getPrimitiveValue() == other.toDouble();
            }
            is Real -> {
                if(isPrimitive && other.isPrimitive)
                {
                    return this.getPrimitiveValue() == other.getPrimitiveValue();
                }
                return false;
                return this.approximate() == other.approximate(); // actually pretty dirty checking, to be refined later
                // TODO: More condition checking to do
            }

        }
        return false
    }

    fun plus(other: Real) : Real
    {
        return summationValue(this,other).simplify()
    }

    fun Number() : Number
    {
        throw UnsupportedOperationException()
    }


}
