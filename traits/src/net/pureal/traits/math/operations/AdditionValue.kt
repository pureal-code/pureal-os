package net.pureal.traits.math.operations

import net.pureal.traits.math.*
import net.pureal.traits.Constructor2

public trait AdditionValue : RealBinaryOperation {

    public class object : Constructor2<AdditionValue, Real, Real> {
        override fun invoke(a: Real, b: Real): AdditionValue = object : AdditionValue, Calculatable() {
            override val subReals: Array<Real> = array(a,b)
        }
    }

    private val constructor: Constructor2<AdditionValue, Real, Real> get() = AdditionValue

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

    override fun minus(): AdditionValue = AdditionValue.invoke(-value1, -value2)

}