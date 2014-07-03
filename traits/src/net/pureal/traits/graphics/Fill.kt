package net.pureal.traits.graphics

import net.pureal.traits.*

trait Fill

trait InvisibleFill : Fill

trait SolidFill : Fill {
    val color : Color
}

trait Gradient : Fill {
    val stops : Map<out Number, Color>
    val transform: Transform2
}

trait LinearGradient : Gradient
trait RadialGradient : Gradient

fun solidFill(color : Color) = object : SolidFill {
    override val color: Color = color
}