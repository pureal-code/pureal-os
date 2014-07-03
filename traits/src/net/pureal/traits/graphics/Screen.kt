package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.interaction.*

trait Screen {
    fun show(content : Composed)
    val size : Vector2
    fun elementAt(location : Vector2) : Element

    fun pointerInput(element : Element) : PointerInput
}