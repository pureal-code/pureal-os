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
            array("2990", "50.1", "-6E+20", "-1.01010101E-16").forEach {
                it("should be the kotlin Code to create this \"${it}\" ") {
                    shouldEqual("BasicReal(\"${it}\")", BasicReal(it).toString())
                }
            }
        }

        on("creating some strings and checking toMathematicalString()'") {
            array("2990", "1.05", "-6E+20", "-1.01010101E-20", "-120030", ".022", "-.44","0").forEach {
                it("should be a mathematical String \"${it}\" ") {
                    shouldEqual("${it}", BasicReal(it).toMathematicalString())
                }
            }
        }
    }
    given("basicReals that are to be added / substracted") {
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
    given("some basic Reals that are to be multiplied")
    {
        on("multiplying .2 with -.03")
        {
            val res = BasicReal(".2") * BasicReal("-.03")
            it("should be -6E-3") {
                shouldEqual(BigInteger(-6), res.number)
                shouldEqual(-3L, res.exponent)
            }
        }
        on("multiplying 2.4E+7 with 1.1E+4") {
            val res = BasicReal("2.4E+7") * BasicReal("1.1E+4")
            it("should be 2.64E+11") {
                shouldEqual(BigInteger(264), res.number)
                shouldEqual(+9L, res.exponent)
            }
        }
        on("multiplying 3001 with 0") {
            val res = BasicReal(3001) * BasicReal(0)
            it("should be 0E+0") {
                shouldEqual(BigInteger(0), res.number)
                shouldEqual(0L, res.exponent)
            }
        }
    }
    given("some basic Reals to divide") {
        on("dividing 2 by -5") {
            val res = BasicReal(2) / BasicReal(-5)
            it("should be -4E-1") {
                shouldEqual(BigInteger(-4), res.number)
                shouldEqual(-1L, res.exponent)
            }
        }
        on("dividing 160 by .1") {
            val res = BasicReal("160") / BasicReal(".1")
            it("should be 16E+2") {
                shouldEqual(BigInteger(16), res.number)
                shouldEqual(2L, res.exponent)
            }
        }
        on("dividing 63 by 9000") {
            val res = BasicReal(63) / BasicReal(9000)
            it("should be 7E-3") {
                shouldEqual(BigInteger(7), res.number)
                shouldEqual(-3L, res.exponent)
            }
        }
        on("dividing 1001 by 0") {
            it("should throw an Exception 'Division by 0'") {
                shouldThrow<ArithmeticException> { BasicReal(1001) / BasicReal(0) }
            }
        }
        on("dividing 2 by 3") {
            it("should throw an Exception 'Inaccurate Division'") {
                shouldThrow<RuntimeException> { BasicReal(2) / BasicReal(3) }
            }
        }
    }
    given("some basic Reals that are to be minimized")
    {
        on("minimizing 10000E+0") {
            val b = BasicReal(BigInteger(10000), 0).minimize()
            it("should be 1E+4") {
                shouldEqual(BigInteger(1), b.number)
                shouldEqual(4L, b.exponent)
            }
        }
        on("minimizing 0E+4") {
            val b = BasicReal(BigInteger(0), 4).minimize()
            it("should be 0E+0") {
                shouldEqual(BigInteger(0), b.number)
                shouldEqual(0L, b.exponent)
            }
        }
        on("minimizing -1.3200E-1") {
            val b = BasicReal(BigInteger(-13200), -5).minimize()
            it("should be -132E-3") {
                shouldEqual(BigInteger(-132), b.number)
                shouldEqual(-3L, b.exponent)
            }
        }
    }
    given("basic Reals for comparison test")
    {
        on("testing if 10 == 10") {
            it("should be true") {
                shouldBeTrue(BasicReal(10).equals(BasicReal(10)))
            }
        }
        on("testing if -1 < +1") {
            it("should be true") {
                shouldBeTrue(BasicReal(-1) < BasicReal(+1))
            }
        }
        on("testing if -4 < -2") {
            it("should be true") {
                shouldBeTrue(BasicReal(-4) < BasicReal(-2))
            }
        }
    }



}}