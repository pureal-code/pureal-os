package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*

class ComposedSpecs : Spek() {{
    given("a composed element") {
        val elements = listOf<Element<Unit>>(
                coloredElement(content = Unit.VALUE, shape = circle(0.6), fill = Fills.solid(Colors.red)),
                coloredElement(content = Unit.VALUE, shape = rectangle(vector(1,1)), fill = Fills.solid(Colors.blue)))
        val x = composed(observableIterable(elements))

        on("getting the shape") {
            val s = x.shape

            it("should be the concetenated shape") {
                shouldBeTrue(s.contains(vector(0, 0.59)))
                shouldBeTrue(s.contains(vector(0.499, 0.499)))
                shouldBeFalse(s.contains(vector(0.51, 0.35)))
            }
        }
    }
}}