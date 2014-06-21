package net.pureal.traits

trait Matrix2 {
    fun get(x : Int, y : Int) : Number

    fun times(other : Matrix2) = matrix2Of {(x, y) -> row(x) * other.column(y)}
    fun times(other : Vector2) = vector2Of {
        val c = column(it)
        other[it].toDouble() * (c[0].toDouble() + c[1].toDouble())
    }

    fun row(x : Int) = vectorOf(get(x,0), get(x,1))
    fun column(y : Int) = vectorOf(get(0,y), get(1,y))
}

fun matrixOf(a : Number, b : Number, c : Number, d : Number) = object : Matrix2 {
    val all = array(array(a, b), array(c, d))

    override fun get(x: Int, y: Int) = all[x][y]

    override fun times(other : Matrix2) : Matrix2 = matrix2Of {(x, y) -> row(x) * other.column(y)}
}

fun matrix2Of(get : (Int, Int) -> Number) : Matrix2 = matrixOf(get(0,0), get(0,1), get(1,0), get(1,1))

val identityMatrix2 = matrixOf(1,0,1,0)