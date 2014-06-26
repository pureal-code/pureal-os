package net.pureal.traits.math



public trait MathValue : Number {
    val operands : Array<Number>
    val operator : MathOperator?

    val isApproximate : Boolean // stores if that is an approximate value

    override fun toString(): String {
        if(operator == null)
        {
            return operands.first().toString()
        }
        return getStringRepresentation(0)
    }

    fun getStringRepresentation(outerPriority : Number) : String
    {
        if(isPrimitive()) return operands.first().toString();
        return operator!!.getStringRepresentation(operands,outerPriority)
    }

    /**
     * Function that simplifies the expression
     */
    fun simplify() : Number {
        if (isPrimitive())
        {
            return operands.first()
        }
        return operator!!.executeOn(operands);
    }

    override fun isPrimitive() : Boolean = operator == null



    /**
     * Function that calculates an approximate (primitive) value for a statement
     */
    fun approximate() : Number {
        if(isPrimitive()) return operands.first()
        throw UnsupportedOperationException()
    }

    override fun equals(other : Any?) : Boolean
    {
        when (other)
        {
            null -> return false
            !is MathValue -> {
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

    overide fun plus


}

fun Number.isPrimitive() : Boolean = true