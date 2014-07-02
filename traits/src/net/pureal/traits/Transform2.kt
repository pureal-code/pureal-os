package net.pureal.traits

import net.pureal.traits.*
import net.pureal.traits.math.*

trait Transform2 {
    val matrix : Matrix3

    fun invoke(vector : Vector2) : Vector2 {
        val v = matrix * vector(vector[0], vector[1], 1)
        return vector(v[0], v[1])
    }

    fun before(other : Transform2) : Transform2 = transform(matrix * other.matrix)
    fun at(location : Vector2) = Transforms2.translation(-location).before(this).before(Transforms2.translation(location))

    fun inverse() : Transform2 = transform(matrix.inverse()!!)

    override fun toString() = "transform(${matrix.toString()})"
}

object Transforms2 {
    val identity = transform(identityMatrix3)

    fun translation(vector : Vector2) : Transform2 = transform(matrix(
            1, 0, vector[0],
            0, 1, vector[1],
            0, 0, 1
    ))

    fun rotation(angle : Number) : Transform2 {
        val a = angle.toDouble()

        return linear(matrix(Math.cos(a), -Math.sin(a), Math.sin(a), Math.cos(a)))
    }
    fun scale(factor : Number) = linear(identityMatrix2*factor)
    fun reflection(axisAngle : Number) : Transform2 {
        val d = 2 * axisAngle.toDouble()
        return linear(matrix(Math.cos(d), Math.sin(d), Math.sin(d), -Math.cos(d)))
    }

    fun linear(matrix : Matrix2) = transform(matrix(
            matrix[0,0], matrix[1,0], 0,
            matrix[0,1], matrix[1,1], 0,
            0, 0, 1
    ))
}

fun transform(matrix : Matrix3) = object : Transform2 {
    {
        if(!matrix.isInvertible) throw IllegalArgumentException("A transformation matrix must be invertible.")
    }

    override val matrix = matrix
}