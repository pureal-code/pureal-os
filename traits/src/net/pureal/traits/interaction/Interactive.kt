package net.pureal.traits.interaction

import net.pureal.traits.graphics.*
import net.pureal.traits.*
import net.pureal.traits.math.*

trait Interactive<T> {
    val content : T
}

trait Visual<T> : Interactive<T>, Element {
    fun onClick(location : Vector2) {}
}

fun visual<T>(content : T,
              transform: Transform2 = Transforms2.identity,
              shape : Shape,
              changed : Observable<Unit> = observable()) = object : Visual<T> {
    override val content = content
    override val transform = transform
    override val shape = shape
    override val changed = changed
}