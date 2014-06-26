package net.pureal.traits.math.operators

import net.pureal.traits.math.*



public object PlusOperator : MathOperator {
    override fun executeOn(operands: Array<Number>): MathValue {
        val iCount : Int = operands.count()
        var oCount : Int = 0
        var oOperands : Array<Number> = Array<Number>(0,{0})
        var v : MathValue = 0.toReal()
        for (i in oOperands)
        {
            i = i.simplify()
            try {
                v = i + v
            }
            catch(e : UnsupportedOperationException)
            {
                try {
                    v = v + i
                }
                catch (e : UnsupportedOperationException) {
                    throw e
                }
            }
        }
    }

    override fun getStringRepresentation(operands: Array<Number>, outerPriority: Number): String {
        throw UnsupportedOperationException()
    }

    override val description : String = "Plus"

    override val priority : Int = 1
}