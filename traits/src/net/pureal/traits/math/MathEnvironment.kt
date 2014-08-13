package net.pureal.traits.math

import net.pureal.traits.math.operations.*
import net.pureal.traits.*

public trait MathEnvironment {
    class object {
        public abstract class DefaultFunctionEnv : MathEnvironment {
            override val intReal: Constructor1<InternalReal, Any?> = BasicReal
            override val addVal: Constructor2<RealBinaryOperation, Real, Real> = AdditionValue
            override val subVal: Constructor2<RealBinaryOperation, Real, Real> = SubtractionValue
            override val mulVal: Constructor2<RealBinaryOperation, Real, Real> = MultiplicationValue
            override val divVal: Constructor2<RealBinaryOperation, Real, Real> = DivisionValue
            override val simplifier: RealSimplifier = object : RealSimplifier {
                override fun simplify(r: Real): Real {
                    return net.pureal.traits.math.simplifier.simplify(r)
                }
            }
        }
        val DefaultAccurate = object : DefaultFunctionEnv() {
            override var accuracy: Int = 100
            override var requireExactCalculation: Boolean = true
        }
    }

    var accuracy: Int
    var requireExactCalculation: Boolean

    val intReal: Constructor1<InternalReal, Any?>
    val addVal: Constructor2<RealBinaryOperation, Real, Real>
    val subVal: Constructor2<RealBinaryOperation, Real, Real>
    val mulVal: Constructor2<RealBinaryOperation, Real, Real>
    val divVal: Constructor2<RealBinaryOperation, Real, Real>

    val simplifier: RealSimplifier
    // TODO: make up more things that should be controlled by the environment
}

public var activeEnvironment: MathEnvironment = MathEnvironment.DefaultAccurate

public val ee: MathEnvironment get() = activeEnvironment // Read-Only Shortcut to activeEnvironment