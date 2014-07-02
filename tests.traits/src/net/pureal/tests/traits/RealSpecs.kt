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
            val sum = (a + b).simplify()
            it("should be the real number 5", {
                shouldEqual(5.toReal(), sum)
                shouldBeTrue(sum.isPrimitive)
                shouldEqual(sum, 5.0)
            })
        }
        on("adding all three")
        {
            val sum = (a + b + c).simplify()
            it("should be the real number 9", {
                shouldEqual(9.toReal(), sum)
                shouldBeTrue(sum.isPrimitive)
                shouldEqual(sum, 9.0)
            })
            val sum2 = (c + b + a).simplify()
            it("should be independent of the sequence", {
                shouldEqual(sum, sum2)
            })
        }
        on("substracting 2 and 3")
        {
            val sub1 = (a - b).simplify()
            val sub2 = (b - a).simplify()
            it("should be 1 or -1 respectively", {
                shouldEqual(sub1, -1.0)
                shouldEqual(sub2, 1.0)
            })
            it("should be negative if the order is swapped", {
                shouldEqual(-sub1,sub2)
            })
        }
        on("multiplying 2 and 4")
        {
            val mul1 = (a * c).simplify()
            it("should be 8", {
                shouldEqual(mul1, 8.0)
            })
        }
    }



}}