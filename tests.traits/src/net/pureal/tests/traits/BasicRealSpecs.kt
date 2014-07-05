package net.pureal.tests.traits.math


import org.spek.*
import net.pureal.traits.math.*
import java.math.BigInteger
import net.pureal.tests.traits.shouldThrow

public class BasicRealSpecs : Spek() {{
    given("Strings that are to be converted to BasicReal"){

        on("creating a basic Real of '212'") {
            val br = BasicReal("212")
            it("should be 212E+0") {
                shouldEqual(0L,br.exponent)
                shouldEqual(BigInteger(212),br.number)
            }
        }
        on("creating a basic Real of '1.23'") {
            val br = BasicReal("1.23")
            it("should be 123E-2") {
                shouldEqual(-2L,br.exponent)
                shouldEqual(BigInteger(123),br.number)
                shouldBeFalse(br.sign)
            }
        }
        on("creating a basic Real of '-0' and '000'") {
            val str1 = "000"
            val str2 = "-0"
            val br1 = BasicReal(str1)
            val br2 = BasicReal(str2)
            it("should be 0") {
                shouldEqual(0L,br1.exponent)
                shouldEqual(BigInteger(0),br1.number)
                shouldEqual(0L,br2.exponent)
                shouldEqual(BigInteger(0),br2.number)
            }
        }
        on("creating a basic Real of '-001010'") {
            val br = BasicReal("-001010")
            it("should be -101E+1") {
                shouldEqual(1L,br.exponent)
                shouldEqual(BigInteger(-101),br.number)
                shouldBeTrue(br.sign)
            }
        }
        on("creating a basic Real of '0.03302'") {
            val br = BasicReal("0.03302")
            it("should be 3302E-5") {
                shouldEqual(-5L,br.exponent)
                shouldEqual(BigInteger(3302),br.number)
                shouldBeFalse(br.sign)
            }
        }
        on("creating a basic Real of '6.0E-23'") {
            val br = BasicReal("6.0E-23")
            it("should be 6E-23") {
                shouldEqual(-23L,br.exponent)
                shouldEqual(BigInteger(6),br.number)
                shouldBeFalse(br.sign)
            }
        }
        on("creating a basic Real of '-44.36200E+15") {
            val br = BasicReal("-44.36200E+15")
            it("should be -44362E+12") {
                shouldEqual(12L, br.exponent)
                shouldEqual(BigInteger(-44362), br.number)
                shouldBeTrue(br.sign)
            }
        }


        on("creating some invalid strings'") {
            array(" 400,2", "23*E+8", "@202020", " -0 ").forEach {
                it("\"${it}\" should cause an exception") {
                    shouldThrow<IllegalArgumentException> { BasicReal(it) }
                }
            }
        }
    }
    given("basicReals that are to be added") {
        on("adding 2 + 200") {
            val res = BasicReal(2) + BasicReal(200)
            it("should be 202") {
                shouldEqual(BigInteger(202), res.number)
                shouldEqual(0L, res.exponent)
            }
        }
        on("adding 15 + .2 + 560") {
            val res = BasicReal("15") + BasicReal(".2") + BasicReal("560")
            it("should be 575.2") {
                shouldEqual(BigInteger(5752), res.number)
                shouldEqual(-1L, res.exponent)
            }
        }
    }


}}