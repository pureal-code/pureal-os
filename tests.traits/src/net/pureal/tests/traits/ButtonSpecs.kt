package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*
import net.pureal.traits.graphics.*
import net.pureal.traits.interaction.*
import net.pureal.traits.math.rectangle

class ButtonSpecs : Spek() {{
    given("a button") {
        val b = button(shape = rectangle(size = vector(0.04, 0.01)), fill=Fills.solid(Colors.black))

        enum class State {
            neverClicked
            clicked
        }

        var s = State.neverClicked

        on("appeding a handler and invoking the button") {
            b.content += { s = State.clicked }
            b.content.invoke()

            it("should be clicked") {
                shouldEqual(s, State.clicked)
            }
        }
    }
}
}
