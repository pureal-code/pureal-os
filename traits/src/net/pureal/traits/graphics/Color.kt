package net.pureal.traits.graphics

trait Color {
    val r : Number
    val g : Number
    val b : Number
    val a : Number
}

fun colorOf(r: Number = 0, g: Number = 0, b: Number = 0, a: Number = 1) = object : Color {
    override val r = r
    override val g = g
    override val b = b
    override val a = a
}