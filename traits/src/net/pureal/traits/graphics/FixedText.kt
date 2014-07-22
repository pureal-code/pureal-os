package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*

trait FixedText : Element<String> {
    val size: Vector2
    override val shape: Rectangle get() = rectangle(size)
}

fun fixedText(content: String, size : Vector2) = object : FixedText {
    override val size = size
    override val changed = observable<Unit>()
    override val content = content
}