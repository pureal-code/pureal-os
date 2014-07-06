package net.pureal.traits.math.operations

import net.pureal.traits.math.Real
import net.pureal.traits.math.real
import net.pureal.traits.math.RealOperation
import net.pureal.traits.math.RealPrimitive

public trait MultiplicationValue : RealOperation {
    override val priority : Int
        get() = 1

    override val description : String
        get() = "Times"


    val factorOne : Real
    val factorTwo : Real

    override fun toMathematicalString() : String {
        val f1str = factorOne.getEncapsulatedMathString(priority)
        val f2str = factorTwo.getEncapsulatedMathString(priority)

        return "${f1str} * ${f2str}"
    }

    override fun toString() : String {
        val f1str = factorOne.getEncapsulatedString(priority)
        val f2str = factorTwo.getEncapsulatedString(priority)

        return "${f1str} * ${f2str}"
    }

    override fun approximate(accuracy : Int) : Real {
        return real(factorOne.toDouble() * factorTwo.toDouble())
    }

    // TODO: remove after symPy is imported
    override fun simplify() : Real {
        val s1 : Real = factorOne.simplify()
        val s2 : Real = factorTwo.simplify()
        if(s1 is RealPrimitive && s2 is RealPrimitive) return real(s1.value*s2.value)

        // return this if no simplification is possible
        return this
    }


    // Code easter-egg: The first factor gets discriminated by this function, it always feels negative afterwards
    override fun minus() : MultiplicationValue = multiplicationValue(-factorOne, factorTwo)

}

fun multiplicationValue(a: Real, b: Real) : MultiplicationValue = object : MultiplicationValue {
    override val factorOne = a
    override val factorTwo = b
}