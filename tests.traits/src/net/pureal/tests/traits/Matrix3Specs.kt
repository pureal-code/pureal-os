package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*

class Matrix3Specs : Spek() {{
    given("a square matrix") {
        val m = matrixOf(
                1,-1,4,
                3,4,-5,
                -2,0,1)

        on("getting the determinant") {
            val x = m.determinant

            it("should be correct") {
                shouldEqual(29.0, x)
            }
        }

        on("getting a submatrix") {
            val x = m.subMatrix(1,2)

            it("should be correct") {
                shouldEqual(matrixOf(1,-1,-2,0), x)
            }
        }

        on("getting the transpose") {
            val x = m.transpose()

            it("should be correct") {
                shouldEqual(matrixOf(
                        1,3,-2,
                        -1,4,0,
                        4,-5,1), x)
            }
        }

        on("getting the adjugate") {
            val x = m.adjugate()

            it("should be correct") {
                matrixOf(
                        1,1,4,
                        -3,4,5,
                        -2,0,1)

            }
        }

        on("getting the inverse") {
            val i = m.inverse()

            it("should fail") {
                shouldEqual(matrixOf(
                        4 , 1 , -11,
                        7 , 9 , 17,
                        8 , 2 , 7) / 29, i)
            }
        }
    }

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
                shouldThrow<ArithmeticException>({shouldEqual(it.getMessage(), "The matrix has no inverse.")}) {m.inverse()}
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