package net.pureal.shell

import org.spek.*
import net.pureal.traits.*
import net.pureal.traits.graphics.*

class DefaultButtonSpecs : Spek() {{
    given("a default button") {
        val b = defaultButton(Transform2.Identity, size(0.04, 0.01), Colors.black)

        on("clicking the button") {
            b.value()
        }
    }
}}