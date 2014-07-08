package net.pureal.traits.math.operations

import net.pureal.traits.math.*

public trait AdditionValue : RealOperation {

    final override val priority : Int
        get() = 0

    override val description : String
        get() = "Addition"


    val summandOne : Real
    val summandTwo : Real

    override fun toMathematicalString() : String {
        val f1str = if (summandOne.priority <= priority) "(${summandOne.toMathematicalString()})"
        else summandOne.toMathematicalString()

        val f2str = if (summandTwo.priority <= priority) "(${summandTwo.toMathematicalString()})"
        else summandTwo.toMathematicalString()
        return "${f1str} + ${f2str}"
    }

    override fun toString() : String {
        val f1str = if (summandOne.priority <= priority) "(${summandOne.toString()})"
        else summandOne.toString()

        val f2str = if (summandTwo.priority <= priority) "(${summandTwo.toString()})"
        else summandTwo.toString()
        return "${f1str} + ${f2str}"
    }

    override fun approximate(accuracy : Int) : Real {
        return real(summandOne.toDouble() + summandTwo.toDouble())
    }

    // TODO: remove after symPy is imported
    override fun simplify() : Real {
        val s1 : Real = summandOne.simplify()
        val s2 : Real = summandTwo.simplify()
        if(s1 is RealPrimitive && s2 is RealPrimitive) return real(s1.value+s2.value)

        // return this if no simplification is possible
        return this
    }

    override fun minus() : AdditionValue = additionValue(-summandOne,-summandTwo)

}

fun additionValue(a : Real, b : Real) : AdditionValue = object : AdditionValue
{
    override val summandOne = a
    override val summandTwo = b
}