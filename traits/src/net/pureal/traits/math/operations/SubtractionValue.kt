package net.pureal.traits.math.operations

import net.pureal.traits.math.*

public trait SubtractionValue : RealBinaryOperation {

    final override val priority: Int
        get() = 0

    override val description: String
        get() = "Subtraction"

    final override val operationSign: String
        get() = "-"

    final override val isOrderDependent: Boolean
        get() = true

    override val value1: Real
    override val value2: Real

    override fun approximate(): InternalReal {
        return value1.approximate() - value2.approximate()
    }

    override fun calculate(): Real {
        val s1: Real = value1.calculate()
        val s2: Real = value2.calculate()
        if (s1 is RealPrimitive && s2 is RealPrimitive) return real(s1.value - s2.value)

        // return this if no simplification is possible
        return this
    }

    override fun minus(): SubtractionValue = subtractionValue(-value1, -value2)

}

fun subtractionValue(a: Real, b: Real): SubtractionValue = object : SubtractionValue, Calculatable() {
    override val value1 = a
    override val value2 = b
}

