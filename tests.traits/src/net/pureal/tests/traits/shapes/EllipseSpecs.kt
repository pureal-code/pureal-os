package net.pureal.tests.traits.shapes

import org.jetbrains.spek.api.*
import net.pureal.traits.*
import net.pureal.traits.math.*

class EllipseSpecs : Spek() {{
    given("an ellipse") {
        val x = ellipse(vector(1, 3))

        on("calling contains for a point inside the ellipse") {
            val c = x.contains(vector(-0.4, -0.1))

            it("should be true") {
                shouldBeTrue(c)
            }
        }

        on("calling contains for a point outside the ellipse") {
            val c = x.contains(vector(0.49, 1.49))

            it("should be false") {
                shouldBeFalse(c)
            }
        }
    }
}
}