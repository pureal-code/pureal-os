package net.pureal.traits

import javax.naming.OperationNotSupportedException

trait Matrix3 {
    fun get(x : Int, y : Int) : Number

    fun times(other : Matrix3) : Matrix3 {throw OperationNotSupportedException()}
    fun times(other : Vector2) = vector2Of {
        val c = column(it)
        other[it].toDouble() * (c[0].toDouble() + c[1].toDouble() + c[2].toDouble())
    }

    fun row(x : Int) = vectorOf(get(x,0), get(x,1), get(x,2))
    fun column(y : Int) = vectorOf(get(0,y), get(1,y), get(2, y))
}

fun matrixOf(
            a: Number, b: Number, c: Number,
            d: Number, e: Number, f: Number,
            g: Number, h: Number, i: Number) = object : Matrix3 {
        val all = array(array(a, b, c), array(d, e, f), array(g, h, i))

        override fun get(x: Int, y: Int) = all[x][y]

        override fun times(other: Matrix3): Matrix3 = matrix3Of({(x, y) -> row(x) * other.column(y) })
    }

fun matrix3Of(get: (Int, Int) -> Number): Matrix3 = matrixOf(get(0, 0), get(0, 1), get(0, 2), get(1, 0), get(1, 1), get(1, 2), get(2, 0), get(2, 1), get(2, 2))

val identityMatrix3 = matrixOf(1, 0, 0, 0, 1, 0, 0, 0, 1)