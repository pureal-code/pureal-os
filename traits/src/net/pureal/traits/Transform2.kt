package net.pureal.traits

import net.pureal.traits.*

trait Transform2 {
    fun apply(vector : Vector2) : Transform2

    fun translate(value : Vector2) : Transform2
    fun rotate(angle : Number) : Transform2
    fun scale(factor : Number) : Transform2

    fun at(location : Vector2) : Transform2

    fun rotateAround(center : Vector2, angle : Number) : Transform2 = translate(-center).rotate(angle).translate(center)

    val matrix : Matrix3
}

trait Matrix3 {
    fun element(x : Int, y : Int) : Number
}

object Transforms {
    val identity = object : Transform2 {
        override val matrix: Matrix3 = object : Matrix3 {
            override fun element(x: Int, y: Int): Number = if (x==y || y>1) 1 else 0
        }
    }
}

fun transformOf(translation : Vector2 = vectorOf(0, 0),
                rotation : Number = 0,
                scale : Vector2 = vectorOf(1, 1)) = object : Transform2 {
    override val translation : Vector2 = transformOf.translation
    override val rotation : Number = transformOf.rotation
    override val scale : Vector2 = transformOf.scale
}