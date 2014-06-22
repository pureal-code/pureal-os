package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*

class Vector2Specs : Spek() {{
    given("two unit vectors in x- and y-direction") {
        val eX = vectorOf(1,0)
        val eY = vectorOf(0,1)

        on("getting the dot product of the two") {
            val p = eX * eY

            it("should be 0.") {
                shouldEqual(0.0, p)
            }
        }
    }
}}