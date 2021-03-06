package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*
import net.pureal.traits.interaction.*

trait Element<out T> : Interactive<T> {
    val shape: Shape
    val changed: Observable<Unit> get() = observable()
}