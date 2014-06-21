package net.pureal.tests.traits

import org.spek.Spek
import net.pureal.traits.*

class Matrix2Specs : Spek() {{
    given("a 2 square matrix") {
        val m = matrixOf(1,2,-4, 3)

        on("getting the determinant") {
            val d = m.determinant()

            it("should be correct") {
                assert(-8.0 == d)
            }
        }

        on("getting the inverse") {
            val i = m.inverse()

            it("should be correct") {
                assert(matrixOf(-3/8, 1/4, -1/2, -1/8) == i)
            }
        }
    }
}}