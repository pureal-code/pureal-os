package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*
import net.pureal.tests.traits.*
import net.pureal.traits.math.rectangle
import net.pureal.traits.graphics.textElement
import net.pureal.traits.graphics.Font
import net.pureal.traits.graphics.Fill
import net.pureal.traits.graphics.Fills
import net.pureal.traits.graphics.Colors
import net.pureal.traits.math.Shape

class FixedTextSpecs : Spek() {{
    given("a fixed text element") {
        val s = "Kotlin rocks on the rocks!"
        val x = textElement(
                content = s,
                font = object : Font {
                    override fun shape(text: String) = rectangle(vector(text.length(), 1))
                },
                fill = Fills.solid(Colors.black), size = 0.01)

        on("getting the content") {
            val c = x.content

            it("should be the orginal string") {
                shouldEqual(c, s)
            }
        }
    }
}
}