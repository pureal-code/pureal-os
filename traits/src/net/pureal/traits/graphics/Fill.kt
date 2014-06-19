package net.pureal.traits.graphics

trait Fill

trait InvisibleFill : Fill

trait SolidFill : Fill {
    val color : Color
}

trait Gradient : Fill {
    val stops : Map<out Number, Color>
    val transform: Transform
}

trait LinearGradient : Gradient
trait RadialGradient : Gradient