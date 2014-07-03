package net.pureal.traits.interaction

import net.pureal.traits.graphics.*

trait Interactive<T> {
    val content : T
}

trait Visual<T> : Interactive<T> {
    val element : Element
}

fun visual<T>(content : T, element : Element) = object : Visual<T> {
    override val content = content
    override val element = element
}