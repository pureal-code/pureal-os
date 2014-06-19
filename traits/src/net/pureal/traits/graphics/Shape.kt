package net.pureal.traits.graphics

import net.pureal.traits.Vector2

trait Shape : Element {
    val size : Vector2
    val fill : Fill
    val stroke : Stroke
}

trait Ellipse : Shape
trait Rectangle : Shape