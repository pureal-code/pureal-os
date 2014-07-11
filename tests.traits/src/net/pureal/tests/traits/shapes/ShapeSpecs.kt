package net.pureal.tests.traits.shapes

import org.spek.*
import net.pureal.traits.*
import net.pureal.traits.math.*

class ShapeSpecs : Spek() {{
    given("a translated rectangle translated") {
        val x = rectangle(vector(1, 3)).transformed(Transforms2.translation(vector(5,5)))

        on("calling contains for a point inside the rectangle") {
            val c = x.contains(vector(4.6, 4))

            it("should be true") {
                shouldBeTrue(c)
            }
        }

        on("calling contains for a point outside the rectangle") {
            val c = x.contains(vector(0, 0))

            it("should be true") {
                shouldBeFalse(c)
            }
        }
    }
}}