package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.*
import kotlin.test.assertEquals
import net.pureal.traits.math.*

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
        on("dividing 4 by 3")
        {
            val div1 = (c / b).simplify()
            it("should be 4/3 (as Double currently)")
            {
                shouldEqual(div1, 4.0/3.0)
            }
        }
        on("combining operations - 2+3*4")
        {
            val f1 = a + b * c
            it("should be '2.0 + 3.0 * 4.0'")
            {
                shouldEqual("2.0 + 3.0 * 4.0", f1.toString())
            }
            it("should be 14")
            {
                shouldEqual(f1.simplify(), 14.0)
            }
        }
        on("combining operations - (2+3)*4")
        {
            val f1 = (a+b)*c
            val f2 = c*(a+b)
            it("should be (2.0 + 3.0) * 4 and other way around")
            {
                shouldEqual("(2.0 + 3.0) * 4.0", f1.toString())
                shouldEqual("4.0 * (2.0 + 3.0)", f2.toString())
            }
            it("should be 18 in both cases")
            {
                shouldEqual(f1.simplify(), 20.0)
                shouldEqual(f2.simplify(), 20.0)
                shouldEqual(f1.simplify(), f2.simplify())
            }
        }
    }
    given("the constant pi as approximate")
    {
        val pi = realConstant("pi",3.141592.toReal())
        on("getting string representation")
        {
            it("should be 'pi'", {
                shouldEqual("pi",pi.toString())
            })
        }
    }


}}