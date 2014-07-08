package net.pureal.traits.math.operations

import net.pureal.traits.math.*

public trait SummationValue : RealOperation {

    override val priority : Int
        get() = 0

    override val description : String
        get() = "Plus"


    val summandOne : Real
    val summandTwo : Real

    override fun toString() : String {
        val s1str = summandOne.getOuterString(priority)
        val s2str = summandTwo.getOuterString(priority)

        return "${s1str} + ${s2str}"
    }

    override fun toMathematicalString() : String {
        val s1str = summandOne.getOuterMathString(priority)
        val s2str = summandTwo.getOuterMathString(priority)

        return "${s1str} + ${s2str}"
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

    override fun minus() : SummationValue = summationValue(-summandOne,-summandTwo)

}

fun summationValue(a : Real, b : Real) : SummationValue = object : SummationValue
{
    override val summandOne = a
    override val summandTwo = b
}