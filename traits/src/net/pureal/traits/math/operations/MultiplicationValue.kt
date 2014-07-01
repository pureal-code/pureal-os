package net.pureal.traits.math.operations

import net.pureal.traits.math.Real
import net.pureal.traits.math.real
import net.pureal.traits.math.RealOperation

public trait MultiplicationValue : RealOperation {
    override val priority : Int
        get() = 1

    override val description : String
        get() = "Times"


    val factorOne : Real
    val factorTwo : Real

    override fun toString() : String {
        val s1str = factorOne.getEncapsulatedString(priority)
        val s2str = factorTwo.getEncapsulatedString(priority)

        return "${s1str} + ${s2str}"
    }

    override fun approximate() : Real {
        return real(factorOne.toDouble() * factorTwo.toDouble())
    }

    // TODO: remove after symPy is imported
    override fun simplify() : Real {
        val s1 : Real = factorOne.simplify()
        val s2 : Real = factorTwo.simplify()
        if(s1.isPrimitive && s2.isPrimitive)
            return real(s1.toDouble()*s2.toDouble())

        // return this if no simplification is possible
        return this
    }


    // Code easter-egg: The first factor gets discriminated by this function, it always feels negative afterwards
    override fun minus() : MultiplicationValue = multiplicationValue(-factorOne, factorTwo)

}

fun multiplicationValue(f1 : Real, f2 : Real) : MultiplicationValue = object : MultiplicationValue {
    override val factorOne = f1
    override val factorTwo = f2
}