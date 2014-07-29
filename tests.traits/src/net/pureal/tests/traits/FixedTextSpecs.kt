package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*
import net.pureal.tests.traits.*
import net.pureal.traits.math.rectangle
import net.pureal.traits.graphics.fixedText
import net.pureal.traits.graphics.Font
import net.pureal.traits.math.BoundedShape
import net.pureal.traits.graphics.Fill
import net.pureal.traits.graphics.Fills
import net.pureal.traits.graphics.Colors

class FixedTextSpecs : Spek() {{
    given("a fixed text element") {
        val s = "Kotlin rocks on the rocks!"
        val x = fixedText(
                content = s,
                font = object : Font {
                    override val size = 0.01
                    override fun shape(text: String) = rectangle(vector(text.length(), 1))
                },
                fill = Fills.solid(Colors.black))

        on("getting the content") {
            val c = x.content

            it("should be the orginal string") {
                shouldEqual(c,s)
            }
        }
    }
}}