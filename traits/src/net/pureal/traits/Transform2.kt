package net.pureal.traits

import net.pureal.traits.*
trait Transform2 {
    fun apply(vector : Vector2) : Transform2
    fun before(other : Transform2) : Transform2 //= transformOf(matrix * other.matrix)

    val matrix : Matrix3

    fun at(location : Vector2) : Transform2
}
/*
object Transforms {
    val identity = object : Transform2 {
        override val matrix = object : Matrix3 {
            override fun times(other: Matrix3): Matrix3 {
                throw UnsupportedOperationException()
            }

            override fun element(x: Int, y: Int): Number = if (x == y || y > 1) 1 else 0
        }
    }
}

   /* fun translation(value : Vector2) : Transform2
    fun rotation(angle : Number) : Transform2
    fun scale(factor : Number) : Transform2 */

/*fun transformOf(matrix : Matrix2, translation : Vector2) {
    override fun element(x: Int, y: Int): Number = if (x==y || y>1) 1 else 0
}

fun transformOf(matrix : Matrix3) = object : Transform2 {
    override val matrix = matrix
}