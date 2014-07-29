package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*

trait FixedText : Element<String> {
    val font: Font
    override val shape: Rectangle get() = font.bound(content)
}

fun fixedText(content: String, font: Font) = object : FixedText {
    override val font = font
    override val content = content
}

trait Font {
    val height: Number
    fun bound(text: String): Rectangle
}