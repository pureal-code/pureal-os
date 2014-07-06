package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*
import net.pureal.traits.interaction.*

trait Element<T> : Interactive<T> {
    val transform: Transform2
    val shape : Shape
    val changed : Observable<Unit>
}