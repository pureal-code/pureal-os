package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*
import net.pureal.tests.traits.*
import net.pureal.traits.math.rectangle
import net.pureal.traits.graphics.fixedText

class FixedTextSpecs : Spek() {{
    given("a fixed text element") {
        val s = "Kotlin rocks on the rocks!"
        val x = fixedText(content = s, size = vector(0.05, 0.01))

        on("getting the content") {
            val c = x.content

            it("should be the orginal string") {
                shouldEqual(c,s)
            }
        }
    }
}}