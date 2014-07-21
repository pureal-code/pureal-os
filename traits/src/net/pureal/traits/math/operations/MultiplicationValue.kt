package net.pureal.traits.math.operations

import net.pureal.traits.math.*

public trait MultiplicationValue : RealBinaryOperation {
    final override val priority: Int
        get() = +5

    override val description: String
        get() = "Multiplication"

    final override val operationSign: String
        get() = "*"

    final override val isOrderDependent: Boolean
        get() = false


    override val value1: Real
    override val value2: Real


    override fun approximate(): InternalReal {
        return value1.approximate() * value2.approximate()
    }

    override fun calculate(): Real {
        val s1: Real = value1.calculate()
        val s2: Real = value2.calculate()
        if (s1 is RealPrimitive && s2 is RealPrimitive) return real(s1.value * s2.value)

        // return this if no simplification is possible
        return this
    }


    // Code easter-egg: The first factor gets discriminated by this function, it always feels negative afterwards
    override fun minus(): MultiplicationValue = multiplicationValue(-value1, value2)

}

fun multiplicationValue(a: Real, b: Real): MultiplicationValue = object : MultiplicationValue, Number() {
    override val value1 = a
    override val value2 = b
}