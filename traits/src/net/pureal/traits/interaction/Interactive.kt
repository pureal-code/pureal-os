package net.pureal.traits.interaction

import net.pureal.traits.graphics.*

trait Interactive<T> {
    val content : T
    val element : Element
    val pointerInput : PointerInput
}

fun interactive<T>(content : T, element : Element, pointerInput : PointerInput) = object : Interactive<T> {
    override val content = content
    override val element = element
    override val pointerInput = pointerInput
}