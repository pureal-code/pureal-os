package net.pureal.traits.math.operations

import net.pureal.traits.math.*

public trait SubtractionValue : RealOperation {

    override val priority : Int
        get() = 0

    override val description : String
        get() = "Minus"


    val operandOne: Real
    val operandTwo: Real

    override fun toString() : String {
        val o1str = operandOne.getEncapsulatedString(priority)
        val o2str = operandTwo.getEncapsulatedString(priority)

        return "${o1str} - ${o2str}"
    }

    override fun toMathematicalString() : String {
        val o1str = operandOne.getEncapsulatedMathString(priority)
        val o2str = operandTwo.getEncapsulatedMathString(priority)

        return "${o1str} - ${o2str}"
    }

    override fun approximate(accuracy : Int) : Real {
        return real(operandOne.toDouble() - operandTwo.toDouble())
    }

    // TODO: remove after symPy is imported
    override fun simplify() : Real {
        val s1 : Real = operandOne.simplify()
        val s2 : Real = operandTwo.simplify()
        if(s1 is RealPrimitive && s2 is RealPrimitive) return real(s1.value-s2.value)

        // return this if no simplification is possible
        return this
    }

    override fun minus() : SubtractionValue = subtractionValue(-operandOne,-operandTwo)

}

fun subtractionValue(a : Real, b : Real) : SubtractionValue = object : SubtractionValue
{
    override val operandOne = a
    override val operandTwo = b
}

