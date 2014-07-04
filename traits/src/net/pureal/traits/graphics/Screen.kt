package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.interaction.*
import net.pureal.traits.math.*

trait Screen {
    fun show(content : Composed)
    val size : Vector2

    fun absoluteTransform(element : Element) : Transform2
    fun relativePointerInput(element : Element) : PointerInput

    val rectangle : Rectangle get() = rectangle(size)
}