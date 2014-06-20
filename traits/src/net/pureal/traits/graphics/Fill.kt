package net.pureal.traits.graphics

import net.pureal.traits.Transform2

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