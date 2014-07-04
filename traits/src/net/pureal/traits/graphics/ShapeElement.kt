package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.math.*

trait ShapeElement : Element {
    val shape : Shape
    val fill : Fill
    val stroke : Stroke
}