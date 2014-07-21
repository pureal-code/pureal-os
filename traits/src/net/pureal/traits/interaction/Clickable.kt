package net.pureal.traits.interaction

import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*

trait Clickable<T> : ColoredElement<T> {
    fun onClick(location: Vector2) {}
}