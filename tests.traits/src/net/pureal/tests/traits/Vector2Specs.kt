package net.pureal.tests.traits.math

import org.jetbrains.spek.api.*
import net.pureal.traits.*

class Vector2Specs : Spek() {{
    given("a 2 vector") {
        val x = vector(1.5, -4)

        on("getting the string repesentation") {
            val s = x.toString()

            it("should be correct") {
                shouldEqual(s, "vector(1.5, -4.0)")
            }
        }
    }

    given("two unit vectors in x- and y-direction") {
        val eX = vector(1, 0)
        val eY = vector(0, 1)

        on("getting the dot product of the two") {
            val p = eX * eY

            it("should be 0.") {
                shouldEqual(0.0, p)
            }
        }
    }
}
}