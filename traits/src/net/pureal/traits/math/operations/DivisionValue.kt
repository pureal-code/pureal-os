package net.pureal.traits.math.operations

import net.pureal.traits.math.Real
import net.pureal.traits.math.real
import net.pureal.traits.math.RealOperation
import net.pureal.traits.math.RealPrimitive

public trait DivisionValue : RealOperation {
    final override val priority : Int
        get() = 1

    override val description : String
        get() = "Division"


    val factorOne : Real
    val factorTwo : Real

    override fun toMathematicalString() : String {
        val f1str = if (factorOne.priority <= priority) "(${factorOne.toMathematicalString()})"
            else factorOne.toMathematicalString()

        val f2str = if (factorTwo.priority <= priority) "(${factorTwo.toMathematicalString()})"
            else factorTwo.toMathematicalString()
        return "${f1str} / ${f2str}"
    }

    override fun toString() : String {
        val f1str = if (factorOne.priority <= priority) "(${factorOne.toString()})"
        else factorOne.toString()

        val f2str = if (factorTwo.priority <= priority) "(${factorTwo.toString()})"
        else factorTwo.toString()
        return "${f1str} / ${f2str}"
    }

    override fun approximate(accuracy : Int) : Real {
        return real(factorOne.toDouble() / factorTwo.toDouble())
    }

    // TODO: remove after symPy is imported
    override fun simplify() : Real {
        val s1 : Real = factorOne.simplify()
        val s2 : Real = factorTwo.simplify()
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
    override fun minus() : DivisionValue = divisionValue(-factorOne, factorTwo)

}

fun divisionValue(a : Real, b : Real) : DivisionValue = object : DivisionValue {
    override val factorOne = a
    override val factorTwo = b
}