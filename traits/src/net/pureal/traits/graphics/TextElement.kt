package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*
import net.pureal.traits.interaction.KeysElement
import net.pureal.traits.interaction.PointersElement

trait TextElement : ColoredElement<String> {
    val font: Font
    val size: Number
    override val shape: Shape get() = font.shape(content).transformed(Transforms2.scale(size))
}

fun textElement(content: String, font: Font, size: Number, fill: Fill) = object : TextElement {
    override val content = content
    override val font = font
    override val size = size
    override val fill = fill
}

trait Font {
    fun shape(text: String): Shape
}

trait TextInput : Composed<String>, KeysElement<String>, PointersElement<String> {
    var text: String
    override val content: String get() = text

    var cursorPosition: Int
    val textChanged: Observable<String>
}
/*
fun textInput(content: String = "", bound: Rectangle, font: Font, fontFill: Fill, backgroundFill: Fill) = object : TextInput {
    {
        text = content
    }

    private fun textElement() = textElement(text, font, fontFill)
    private fun cursor() = coloredElement(rectangle(vector(0,0)), fontFill)
    private val background = coloredElement(bound, backgroundFill)

    private var elements: Iterable<Element<*>> = listOf()

    private fun refresh() {
        elements = listOf(textElement(), cursor(), background)
    }

    override fun iterator() = (elements map {transformedElement(it)}).iterator()
}*/