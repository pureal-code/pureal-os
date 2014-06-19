package net.pureal.traits.graphics

import net.pureal.traits.*

trait Transform {
    val translation : Vector2
    val rotation : Number
    val scale : Vector2
}

fun transformOf(translation : Vector2 = vectorOf(0, 0),
                rotation : Number = 0,
                scale : Vector2 = vectorOf(1, 1)) = object : Transform {
    override val translation: Vector2 = translation
    override val rotation: Number = rotation
    override val scale: Vector2 = scale
}