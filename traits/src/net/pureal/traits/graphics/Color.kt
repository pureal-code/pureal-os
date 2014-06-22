package net.pureal.traits.graphics

trait Color {
    val r : Number
    val g : Number
    val b : Number
    val a : Number
}

fun color(r: Number = 0, g: Number = 0, b: Number = 0, a: Number = 1) = object : Color {
    override val r = r
    override val g = g
    override val b = b
    override val a = a
}

object Colors {
    val black = color()
    val red = color(r=1)
    val green = color(g=1)
    val blue = color(b=1)
    val gray = color(.75, .75, .75)
    val white = color(1, 1, 1)
    val transparent = color(a=0)
}