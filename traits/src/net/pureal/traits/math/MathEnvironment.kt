package net.pureal.traits.math

public trait MathEnvironment {
    class object {
        val DefaultAccurate = object : MathEnvironment {
            override var accuracy: Int = 100
            override var requireExactCalculation: Boolean = true
        }
    }

    var accuracy: Int
    var requireExactCalculation: Boolean

    // TODO: make up more things that should be controlled by the environment
}

public var activeEnvironment: MathEnvironment = MathEnvironment.DefaultAccurate

