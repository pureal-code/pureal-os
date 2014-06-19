package net.pureal.traits.graphics

trait Gradient : Fill {
    data class Stop(val position: Number, val color: Color)
    val stops : Set<Stop>
    val transform: Transform
}