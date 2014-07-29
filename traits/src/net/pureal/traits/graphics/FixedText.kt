package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*

trait FixedText : ColoredElement<String> {
    val font: Font
    override val shape: BoundedShape get() = font.shape(content)
}

fun fixedText(content: String, font: Font, fill: Fill) = object : FixedText {
    override val content = content
    override val font = font
    override val fill = fill
}

trait Font {
    val size: Number
    fun shape(text: String): BoundedShape
}