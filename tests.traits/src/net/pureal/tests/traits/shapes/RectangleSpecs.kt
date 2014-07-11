package net.pureal.tests.traits.shapes

import org.spek.*
import net.pureal.traits.*
import net.pureal.traits.math.*

class RectangleSpecs : Spek() {{
    given("a rectangle") {
        val x = rectangle(vector(1, 3))

        on("calling contains for a point inside the rectangle") {
            val c = x.contains(vector(-0.4, 1))

            it("should be true") {
                shouldBeTrue(c)
            }
        }

        on("calling contains for a point outside the rectangle") {
            val c = x.contains(vector(0, 1.6))

            it("should be false") {
                shouldBeFalse(c)
            }
        }
    }
}}