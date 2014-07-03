package net.pureal.traits.graphics

import net.pureal.traits.*

trait Screen {
    fun show(content : Composed)
    val size : Vector2
}