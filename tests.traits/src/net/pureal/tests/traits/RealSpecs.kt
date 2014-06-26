package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.*
import kotlin.test.assertEquals

public class RealSpecs : Spek() {{
    given("the real numbers 2 and 3"){
        val a = 2.toReal()
        val b = 3.toReal()
        on("addition of these two")
        {
            val sum = a + b
            it("should be the real number 5", {
                shouldEqual(5.toReal(), sum)
            })
        }
    }


}}