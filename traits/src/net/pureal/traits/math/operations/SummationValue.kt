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

    override fun simplify() : Real
    {
        val s1 : Real = summandOne.simplify()
        val s2 : Real = summandTwo.simplify()
        if(s1.isPrimitive && s2.isPrimitive)
            return real(s1.getPrimitiveValue().toDouble()+s2.getPrimitiveValue().toDouble())

        // return this if no simplification is possible
        return this
    }

}

fun summationValue(a : Real, b : Real) : SummationValue = object : SummationValue
{
    override val summandOne = a
    override val summandTwo = b
}