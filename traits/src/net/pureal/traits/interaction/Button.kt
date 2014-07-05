package net.pureal.traits.interaction

import net.pureal.traits.*
import net.pureal.traits.graphics.*

trait Button : Visual<Trigger<Unit>> {
    override fun addPointerInput(pointerInput : PointerInput) {
        pointerInput.click += {(location) ->
            if (element.shape.contains(location)) {
                content()
            }
        }
    }
}

fun button(element : Element, trigger : Trigger<Unit> = trigger<Unit>()) = object : Button {
    override val content = trigger
    override val element = element
}