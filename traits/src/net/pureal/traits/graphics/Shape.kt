package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.math.*

trait Shape : Element {
    val size : Vector2
    val fill : Fill
    val stroke : Stroke
}

trait Ellipse : Shape
trait Rectangle : Shape