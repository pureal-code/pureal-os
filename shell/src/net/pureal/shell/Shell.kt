package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*

class Shell(val screen: Screen) {
    {
        val button = button(coloredElement(singleColored(rectangle(screen.size / 2), Colors.gray)))
        val trigger = button.content
        val element = button.element

        trigger += {println("button clicked!")}

        screen.relativePointerInput(element).click += {(location) ->
            val relativeLocation = screen.absoluteTransform(element)(location)
            if(element.shape.contains(relativeLocation)) {
                trigger()
            }
        }

        screen.show(composed(setOf(element)))
    }
}