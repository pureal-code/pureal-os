package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.math.*
import java.math.BigInteger

public class BasicRealSpecs : Spek() {{
    given("several Strings"){

        on("creating a basic Real of '212'") {
            val str : String = "212"
            val br : BasicReal = BasicReal(str)
            it("should be 212E+0") {
                shouldEqual(0L,br.exponent)
                shouldEqual(BigInteger(212.toString()),br.number)
            }
        }
        on("creating a basic Real of '1.23'") {
            val str : String = "1.23"
            val br : BasicReal = BasicReal(str)
            it("should be 123E-2") {
                shouldEqual(-2L,br.exponent)
                shouldEqual(BigInteger(123.toString()),br.number)
            }
        }
        on("creating a basic Real of ' 400,2'") {
            val str : String = " 400.2"
            it("should throw an exception") {
                var threw : Boolean = false
                try {
                    BasicReal(str)
                } catch (e : IllegalArgumentException) {
                    threw = true
                }
                shouldBeTrue(threw)
            }
        }
    }


}}