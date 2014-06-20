package net.pureal.traits

trait Matrix2 {
    fun element(x : Int, y : Int) : Number

    fun times(other : Matrix2) : Matrix2
}

fun matrixOf(a : Number, b : Number, c : Number, d : Number) = object : Matrix2 {
    val all = array(array(a, b), array(c, d))

    override fun element(x: Int, y: Int) = all[x][y]

    override fun times(other: Matrix2): Matrix2 {
        throw UnsupportedOperationException()
    }
}