package net.pureal.traits.math

import net.pureal.traits.math.operations.*

public trait MathEnvironment {
    class object {
        public abstract class DefaultFunctions : MathEnvironment {
            override val intReal: (Any?) -> InternalReal = {basicReal(it)}
            override val addVal: (Real, Real) -> Real = {a,b -> additionValue(a,b)}
            override val subVal: (Real, Real) -> Real = {a,b -> subtractionValue(a,b)}
            override val mulVal: (Real, Real) -> Real = {a,b -> multiplicationValue(a,b)}
            override val divVal: (Real, Real) -> Real = {a,b -> divisionValue(a,b)}
        }
        val DefaultAccurate = object : DefaultFunctions() {
            override var accuracy: Int = 100
            override var requireExactCalculation: Boolean = true
        }
    }

    var accuracy: Int
    var requireExactCalculation: Boolean

    val intReal: (Any?) -> InternalReal
    val addVal: (Real, Real) -> Real
    val subVal: (Real, Real) -> Real
    val mulVal: (Real, Real) -> Real
    val divVal: (Real, Real) -> Real
    // TODO: make up more things that should be controlled by the environment
}

public var activeEnvironment: MathEnvironment = MathEnvironment.DefaultAccurate

public val ee: MathEnvironment get() = activeEnvironment // Read-Only Shortcut to activeEnvironment