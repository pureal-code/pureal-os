package net.pureal.traits

import javax.naming.OperationNotSupportedException

trait Matrix3 {
    val a: Number; val b: Number; val c: Number
    val d: Number; val e: Number; val f: Number
    val g: Number; val h: Number; val i: Number

    fun times(other : Matrix3) = matrix3Of {(x, y) -> row(x) * other.column(y)}
    fun times(other : Vector2) = vector2Of {
        val c = column(it)
        other[it].toDouble() * (c[0].toDouble() + c[1].toDouble() + c[2].toDouble())
    }
    fun times(other : Number) = matrix3Of({(x,y) -> other.toDouble() * get(x, y).toDouble()})
    fun div(other : Number) = times(1.0/other.toDouble())

    fun get(x : Int, y : Int) : Number = when(x) {
        0 -> when(y) {0 -> a; 1 -> b; 2 -> c else -> throw IllegalArgumentException()}
        1 -> when(y) {0 -> d; 1 -> e; 2 -> f else -> throw IllegalArgumentException()}
        2 -> when(y) {0 -> g; 1 -> h; 2 -> i else -> throw IllegalArgumentException()}
        else -> throw IllegalArgumentException()
    }

    val determinant : Number get() =
    (a.toDouble() * e.toDouble() * i.toDouble() + b.toDouble() * f.toDouble() * g.toDouble() + c.toDouble() * d.toDouble() * h.toDouble()) -
    (c.toDouble() * e.toDouble() * g.toDouble() + a.toDouble() * f.toDouble() * h.toDouble() + b.toDouble() * d.toDouble() * i.toDouble())

    fun transpose() = matrix3Of{(x,y) -> this[y,x]}
    fun inverse() : Matrix3 {
        val det = determinant

        if(det == 0.0) throw ArithmeticException("The matrix has no inverse.")

        return matrix3Of{(x,y) -> transpose().subMatrix(x,y).determinant()}.adjugate() / det
    }
    fun adjugate() = matrix3Of{(x, y) -> this[x,y].toDouble() * if((x+y) mod 2 == 0) 1 else -1}

    fun row(x : Int) = vectorOf(get(x,0), get(x,1), get(x,2))
    fun column(y : Int) = vectorOf(get(0,y), get(1,y), get(2, y))

    fun subMatrix(exceptX : Int, exceptY : Int) : Matrix2 = matrix2Of{(x,y)-> this[(0..2).filter{it != exceptX}[x], (0..2).filter{it != exceptY}[y]]}

    override fun equals(other : Any?) = other is Matrix3 && (a == other.a && b == other.b && c == other.c && d == other.d && e == other.e && f == other.f && g == other.g && h == other.h && i == other.i)

    override fun toString() = "[[${a}, ${b}, ${c}], [${d}, ${e}, ${f}], [${g}, ${h}, ${i}]]"
}

fun matrixOf(a: Number, b: Number, c: Number, d: Number, e: Number, f: Number, g: Number, h: Number, i: Number) = object : Matrix3 {
    override val a = a; override val b = b; override val c = c;
    override val d = d; override val e = e; override val f = f;
    override val g = g; override val h = h; override val i = i
}

fun matrix3Of(get: (Int, Int) -> Number): Matrix3 = matrixOf(get(0, 0), get(0, 1), get(0, 2), get(1, 0), get(1, 1), get(1, 2), get(2, 0), get(2, 1), get(2, 2))

val identityMatrix3 = matrixOf(1, 0, 0, 0, 1, 0, 0, 0, 1)