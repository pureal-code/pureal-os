package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.interaction.*
import net.pureal.traits.math.*

trait Screen {
    var content : Composed<*>

    fun absoluteTransform(element : Element<*>) : Transform2

    fun registerPointerInput(pointerInput : PointerInput) = {
        pointerInput.click += {(location) -> content.elementsAt(location).forEach {if(it is Clickable<*>) it.onClick(absoluteTransform(it)(location))}}
    }

    val rectangle : Rectangle
    val size : Vector2 get() = rectangle.size
}

//fun screen(content : Composed<*>, rectangle : Rectangle) = null!!