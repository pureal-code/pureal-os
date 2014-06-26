package net.pureal.traits.math



public trait Real {
    val isApproximate : Boolean // stores if that is an approximate value
    val isPrimitive : Boolean // stores if that value is a primitive or needs to be calculated out

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

    override fun equals(other : Any?) : Boolean
    {
        when (other)
        {
            null -> return false
            !is Real -> {
                if(other is Number)
                    return this.approximate() == other;
                return false
            }
            else -> {
                if(isPrimitive() && other.isPrimitive())
                {
                    return this.approximate() == other.approximate();
                }
                return false; // TODO: More condition checking to do
            }

        }
    }

    overide fun plus()


}
