package net.pureal.tests.traits

import org.spek.Spek
import net.pureal.traits.*

class Matrix3Specs : Spek() {{
    given("a 3 square matrix with determinant 0") {
        val m = matrixOf(
                1,2,3,
                4,5,6,
                7,8,9)

        on("getting the determinant") {
            val d = m.determinant

            it("should be correct") {
                assert(0 == d)
            }
        }

        on("getting the inverse") {
            val i = m.inverse

            it("should fail") {
                assert(false)
            }
        }
    }
}}