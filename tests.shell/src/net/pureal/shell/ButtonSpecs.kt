package net.pureal.shell

import org.spek.*
import net.pureal.traits.*
import net.pureal.traits.math.*
import net.pureal.traits.graphics.*
import net.pureal.traits.interaction.*

class ButtonSpecs : Spek() {{
    given("a button") {
        val b = button(shape=singleColored(rectangle(size=vector(0.04, 0.01)), Colors.black))

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

