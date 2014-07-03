package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.interaction.*

trait Screen {
    fun show(content : Composed)
    fun pointerInput(element : Element) : PointerInput
    val size : Vector2
    fun elementAt(location : Vector2) : Element
}