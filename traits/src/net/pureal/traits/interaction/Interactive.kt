package net.pureal.traits.interaction

import net.pureal.traits.graphics.*

trait Interactive<T> {
    val value : T
    val element : Element
    val pointerInput : PointerInput
}

fun interactive<T>(value : T, element : Element, pointerInput : PointerInput) = object : Interactive<T> {
    override val value = value
    override val element = element
    override val pointerInput = pointerInput
}