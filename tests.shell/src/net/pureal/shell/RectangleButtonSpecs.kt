package net.pureal.shell

import org.spek.*
import net.pureal.traits.*
import net.pureal.traits.graphics.*

class RectangleButtonSpecs : Spek() {{
    given("a rectangle button") {
        val b = RectangleButton(size=vector(0.04, 0.01), color=Colors.black)

        enum class State {neverClicked; clicked}

        var s = State.neverClicked

        on("appeding a handler and invoking the button") {
            b.content += {s = State.clicked}

            b.content.invoke()

            it("should be clicked") {
                shouldEqual(s, State.clicked)
            }
        }
    }
}}

