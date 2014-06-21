package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*

class Matrix3Specs : Spek() {{
    given("a 3 square matrix with determinant 0") {
        val m = matrixOf(
                1,2,3,
                4,5,6,
                7,8,9)

        on("getting the determinant") {
            val d = m.determinant

            it("should be 0") {
                shouldEqual(0.0, d)
            }
        }

        on("getting the inverse") {
            it("should fail") {
                shouldThrow<ArithmeticException> { m.inverse() }
            }
        }
    }

    given("a square matrix") {
        val m = matrixOf(
                1,-1,4,
                3,4,-5,
                -2,0,1)

        on("getting the determinant") {
            val d = m.determinant

            it("should be correct") {
                shouldEqual(29.0, d)
            }
        }

        on("getting the inverse") {
            val i = m.inverse()

            it("should fail") {
                shouldEqual(false, true)
            }
        }
    }
}}

fun shouldThrow<E : Exception>(exceptionAssertion : (E) -> Unit = {}, action : () -> Unit) {
    try {
        action()
        throw AssertionError("No exception thrown.")
    }
    catch(ex : E) {
        exceptionAssertion(ex)
    }
}