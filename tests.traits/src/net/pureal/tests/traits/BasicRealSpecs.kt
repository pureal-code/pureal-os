package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.math.*

public class BasicRealSpecs : Spek() {{
    given("the String '212'"){
        val str : String = "212"
        on("creating a basic Real of it") {
            val br : BasicReal = basicReal(str)
            it("should be 212E+0") {
                shouldEqual(0L,br.exponent)
                shouldEqual(BigInteger(212),br.number)
            }
        }
    }


}}