package net.pureal.traits.math.operations

import net.pureal.traits.math.*

public trait SubtractionValue : RealOperation {

    final override val priority : Int
        get() = 0

    override val description : String
        get() = "Subtraction"


    val operandOne: Real
    val operandTwo: Real

    override fun toMathematicalString() : String {
        val f1str = if (operandOne.priority < priority) "(${operandOne.toMathematicalString()})"
        else operandOne.toMathematicalString()

        val f2str = if (operandTwo.priority < priority) "(${operandTwo.toMathematicalString()})"
        else operandTwo.toMathematicalString()
        return "${f1str} - ${f2str}"
    }

    override fun toString() : String {
        val f1str = if (operandOne.priority < priority) "(${operandOne.toString()})"
        else operandOne.toString()

        val f2str = if (operandTwo.priority < priority) "(${operandTwo.toString()})"
        else operandTwo.toString()
        return "${f1str} - ${f2str}"
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

