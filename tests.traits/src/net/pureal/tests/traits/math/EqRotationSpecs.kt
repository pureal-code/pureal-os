package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.math.*
import net.pureal.traits.*
import net.pureal.tests.traits.*
import net.pureal.traits.math.simplifier.rotate

public class EqRotationSpecs : Spek() {{
    given("reals for addition/subtration rotation") {
        on("rotating 5 + 70 + .8") {
            val a = real(5) + real(70) + real(.8)
            it("should stay the same") {
                shouldEqual(a, a.rotate())
            }
        }
        on("rotating -2 + 5 - 4 + 7 - -.3") {
            val a = real(-2) + real(5) - real(4) + real(7) - real(-.3)
            it("should be (5 + 7 + .3) - (4 + 2)") {
                shouldEqual((real(5) + real(7) + real(.3))-(real(4) + real(2)), a.rotate())
            }
        }
    }
}}