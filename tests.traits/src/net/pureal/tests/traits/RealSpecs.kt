package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.*
import kotlin.test.assertEquals
import net.pureal.traits.math.toReal

public class RealSpecs : Spek() {{
    given("the real numbers 2, 3 and 4"){
        val a = 2.toReal()
        val b = 3.toReal()
        val c = 4.toReal()
        on("adding 2 and 3")
        {
            val sum = a + b
            it("should be the real number 5", {
                shouldEqual(5.toReal(), sum)
                shouldBeTrue(sum.isPrimitive)
                shouldEqual(sum, 5)
            })
        }
        on("adding all three")
        {
            val sum = a + b + c
            it("should be the real number 9", {
                shouldEqual(9.toReal(), sum)
                shouldBeTrue(sum.isPrimitive)
                shouldEqual(sum, 9)
            })
            val sum2 = c + b + a
            it("should be independent of the sequence", {
                shouldEqual(sum, sum2)
            })
        }
    }



}}