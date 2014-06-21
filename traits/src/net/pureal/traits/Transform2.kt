package net.pureal.traits

import net.pureal.traits.*
trait Transform2 {
    fun invoke(vector : Vector2) = linearMatrix * vector + translation
    fun before(other : Transform2) = transformOf(matrix * other.matrix)

    val matrix : Matrix3
    val linearMatrix : Matrix2 get() = matrixOf(matrix[0,0], matrix[0,1], matrix[1,0], matrix[1,1])
    val translation : Vector2 get() = vectorOf(matrix[0,2], matrix[1,2])

    fun at(location : Vector2) = Transforms2.translation(-location).before(this).before(Transforms2.translation(location))

    val inverse : Transform2 get() = transformOf(matrix.inverse())
}

object Transforms2 {
    val identity = transformOf(matrix3Of({(x,y) -> if (x == y) 1 else 0}))

    fun translation(value : Vector2) = transformOf(translation = value)
    /*  fun rotation(angle : Number) : Transform2
  fun scale(factor : Number) : Transform2 */
}

fun transformOf(linearMatrix : Matrix2 = identityMatrix2, translation : Vector2 = zeroVector2) = transformOf(matrixOf(
        linearMatrix[0,0], linearMatrix[1,0], translation[0],
        linearMatrix[0,1], linearMatrix[0,1], translation[1],
        0,0,1
))

fun transformOf(matrix : Matrix3) = object : Transform2 {
    override val matrix = matrix
}