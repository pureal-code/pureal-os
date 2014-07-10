package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.vector

// TODO brings reference barf: import org.jetbrains.jet.codegen.*

class Shell(val screen: Screen) {
    {
        val halfWidth = screen.size.x.toDouble() / 2
        screen.content = composed(elements = setOf(button(
                shape = rectangle(vector(halfWidth, halfWidth)),
                fill = Fills.solid(Colors.gray),
                onClick = { println("button clicked!")}
        )))
    }
}