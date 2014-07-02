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
        val s1str = summandOne.getEncapsulatedString(priority)
        val s2str = summandTwo.getEncapsulatedString(priority)

        return "${s1str} + ${s2str}"
    }

    override fun approximate() : Real {
        return real(summandOne.toDouble() + summandTwo.toDouble())
    }

    // TODO: remove after symPy is imported
    override fun simplify() : Real {
        val s1 : Real = summandOne.simplify()
        val s2 : Real = summandTwo.simplify()
        if(s1.isPrimitive && s2.isPrimitive)
            return real(s1.toDouble()+s2.toDouble())

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