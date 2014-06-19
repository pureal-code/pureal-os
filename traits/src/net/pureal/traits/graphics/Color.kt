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

object Colors {
    val black = colorOf()
    val red = colorOf(r=1)
    val green = colorOf(g=1)
    val blue = colorOf(b=1)
    val gray = colorOf(.75, .75, .75)
    val white = colorOf(1, 1, 1)
    val transparent = colorOf(a=0)
}