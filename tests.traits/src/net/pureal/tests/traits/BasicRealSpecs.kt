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

        on("creating some strings and checking toString()'") {
            array("299E+1", "501E-1", "-6E+20", "-101010101E-20").forEach {
                it("should be the kotlin Code to create this \"${it}\" ") {
                    shouldEqual("BasicReal(\"${it}\")", BasicReal(it).toString())
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
        on("adding 15 + .02 + 560") {
            val res = BasicReal("15") + BasicReal(".02") + BasicReal("560")
            it("should be 575.02") {
                shouldEqual(BigInteger(57502), res.number)
                shouldEqual(-2L, res.exponent)
            }
        }
        on("adding 25.4 + (-33)") {
            val res = BasicReal(25.4) + BasicReal(-33)
            it("should be -7.6") {
                shouldEqual(BigInteger(-76), res.number)
                shouldEqual(-1L, res.exponent)
                shouldBeTrue(res.sign)
            }
        }
        on("subtracting -10 with -3000") {
            val res = BasicReal(-10) - BasicReal(-3000)
            it("should be 299E+1") {
                shouldEqual(BigInteger(299), res.number)
                shouldEqual(+1L, res.exponent)
                shouldBeFalse(res.sign)
            }
        }
    }


}}