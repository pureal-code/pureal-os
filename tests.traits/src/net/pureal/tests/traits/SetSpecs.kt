package net.pureal.tests.traits.math


import org.spek.*
import net.pureal.traits.math.*
import java.math.BigInteger
import net.pureal.tests.traits.shouldThrow
import net.pureal.traits.math.sets.*

public class SetSpecs : Spek() {{
    given("some integers to test") {
        array<Number>(0,205,1.2E4,-40, basicReal("42")).forEach {
            on("checking if {$it} is an Integer") {
                it("should be true") {
                    shouldBeTrue(it in IntegerSet)
                }
            }
        }
        array<Number>(Infinity, .02, -1.5E-12, 87.5, basicReal("-0.912E+2")).forEach {
            on("checking if {$it} is an Integer") {
                it("should be false") {
                    shouldBeFalse(it in IntegerSet)
                }
            }
        }
    }


}}