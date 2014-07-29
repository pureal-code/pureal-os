package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.math.*
import net.pureal.traits.math.sets.*

public class SetSpecs : Spek() {{
    given("some integers to test") {
        array<Number>(0,205,1.2E4,-40, basicReal("42")).forEach {
            on("checking if $it is an Integer") {
                it("should be true") {
                    shouldBeTrue(it in IntegerSet)
                }
            }
        }
        array<Number>(Infinity, .02, -1.5E-12, 87.5, basicReal("-0.912E+2")).forEach {
            on("checking if $it is an Integer") {
                it("should be false") {
                    shouldBeFalse(it in IntegerSet)
                }
            }
        }
    }
    given("some numbers to test in a range") {
        array<Number>(.3, ee.intReal(25.2), 67, 112E5).forEach {
            on("checking if $it is positive") {
                it("should Be True") {
                    shouldBeTrue(it in RealSet.Positive)
                }
            }
        }
        array<Number>(0, -33, ee.intReal("0.00"), Infinity, -44.4).forEach {
            on("checking if $it is positive") {
                it("should Be False") {
                    shouldBeFalse(it in RealSet.Positive)
                }
            }
        }
        on("checking if Infinities are in ther Set of Real Numbers") {
            it("should be false") {
                shouldBeFalse(Infinity in RealSet)
                shouldBeFalse(-Infinity in RealSet)
            }
        }
    }
    given("the set (-2,2)") {
        val e = openRealSet(-2,2)
        on("testing with [-2,2]") {
            val f = realSet(-2,2)
            it("should be in [-2,2] but not the other way round") {
                shouldBeTrue(e in f)
                shouldBeFalse(f in e)
            }
        }
        on("testing with RealSets and IntegerSets") {
            it("should be in Full RealSet but not in positive or negative ones and IntegerSets") {
                shouldBeTrue(e in RealSet)
                shouldBeFalse(e in RealSet.PositiveAndZero)
                shouldBeFalse(e in RealSet.NegativeAndZero)
                shouldBeFalse(e in IntegerSet)
            }
        }
        on("testing with SetIntersections")
    }




}}