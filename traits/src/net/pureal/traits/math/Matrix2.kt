package net.pureal.traits

trait Matrix2 {
    val a : Number ; val b : Number
    val c : Number ; val d : Number

    fun times(other : Matrix2) = matrix2Of {(x, y) -> row(x) times other.column(y) }
    fun times(other : Vector2) = vector2Of{other * row(it)}

    fun times(other : Number) = matrix2Of({(x, y) -> other.toDouble() * get(x, y).toDouble() })
    fun div(other : Number) = times(1.0/other.toDouble())

    fun get(x : Int, y : Int) : Number = when(x) {
        0 -> when(y) {0 -> a; 1 -> b; else -> throw IllegalArgumentException()}
        1 -> when(y) {0 -> c; 1 -> d; else -> throw IllegalArgumentException()}
        else -> throw IllegalArgumentException()}

    fun determinant() = a.toDouble() * d.toDouble() - b.toDouble() * c.toDouble()
    fun inverse() = matrixOf(d.toDouble(), -b.toDouble(), -c.toDouble(), a.toDouble()) times (1/determinant().toDouble())

    fun row(x : Int) = vectorOf(get(x,0), get(x, 1))
    fun column(y : Int) = vectorOf(get(0, y), get(1, y))

    override fun equals(other : Any?) = other is Matrix2 && (a == other.a && b == b && c == c && d == other.d)

    override fun toString() = "[[${a}, ${b}], [${c}, ${d}]]"
}

fun matrixOf(a : Number, b : Number, c : Number, d : Number) = object : Matrix2 {
    override val a = a; override val b = b
    override val c = c; override val d = d
}

fun matrix2Of(get : (Int, Int) -> Number) : Matrix2 = matrixOf(get(0,0), get(0,1), get(1, 0), get(1,1))

val identityMatrix2 = matrixOf(1, 0, 1, 0)

