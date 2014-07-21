package net.pureal.traits.math.operations

import net.pureal.traits.math.*

public trait AdditionValue : RealBinaryOperation {

    final override val priority: Int
        get() = 0

    override val description: String
        get() = "Addition"

    final override val operationSign: String
        get() = "+"

    final override val isOrderDependent: Boolean
        get() = false


    override fun approximate(): InternalReal {
        return value1.approximate() + value2.approximate()
    }

    override fun calculate(): Real {
        val s1: Real = value1.calculate()
        val s2: Real = value2.calculate()
        if (s1 is RealPrimitive && s2 is RealPrimitive) return real(s1.value + s2.value)

        // return this if no simplification is possible
        return this
    }

    override fun minus(): AdditionValue = additionValue(-value1, -value2)

}

fun additionValue(a: Real, b: Real): AdditionValue = object : AdditionValue, Number() {
    override val value1 = a
    override val value2 = b
}