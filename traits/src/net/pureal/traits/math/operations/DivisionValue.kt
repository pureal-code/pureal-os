package net.pureal.traits.math.operations

import net.pureal.traits.math.Real
import net.pureal.traits.math.real
import net.pureal.traits.math.RealBinaryOperation
import net.pureal.traits.math.RealPrimitive

public trait DivisionValue : RealBinaryOperation {
    final override val priority : Int
        get() = +5

    override val description : String
        get() = "Division"

    final override val operationSign : String
        get() = "/"

    final override val isOrderDependent : Boolean
        get() = true


    override val value1 : Real
    override val value2 : Real

    override fun approximate(): Real {
        return real(value1.toDouble() / value2.toDouble())
    }

    // TODO: remove after symPy is imported
    override fun simplify() : Real {
        val s1 : Real = value1.simplify()
        val s2 : Real = value2.simplify()
        if(s1 is RealPrimitive && s2 is RealPrimitive){
            try {
                val res = s1.value / s2.value
                return real(res)
            } catch (e : RuntimeException) {
                return this
            }
        }

        // return this if no simplification is possible
        return this
    }


    // Code easter-egg: The first factor gets discriminated by this function, it always feels negative afterwards
    override fun minus() : DivisionValue = divisionValue(-value1, value2)

}

fun divisionValue(a : Real, b : Real) : DivisionValue = object : DivisionValue {
    override val value1 = a
    override val value2 = b
}