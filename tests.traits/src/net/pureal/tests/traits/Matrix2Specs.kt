package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*

class Matrix2Specs : Spek() {{
    given("a 2 square matrix") {
        val m = matrixOf(1,2,-4, 3)

        on("getting a row") {
            val x = m.row(1)

            it("should be correct") {
                shouldEqual(vectorOf(-4,3), x)
            }
        }

        on("multiplying it with a vector") {
            val x = m * vectorOf(1,4)

            it("should be correct") {
                shouldEqual(vectorOf(9,8), x)
            }
        }

        on("getting the determinant") {
            val d = m.determinant()

            it("should be correct") {
                shouldEqual(11.0, d)
            }
        }

        on("getting the inverse") {
            val i = m.inverse()

            it("should be correct") {
                shouldEqual(matrixOf(3.0/11, -2.0/11, 4.0/11, 1.0/11), i)
            }
        }
    }
}}