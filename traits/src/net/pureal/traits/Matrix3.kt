package net.pureal.traits

trait Matrix3 {
    fun element(x : Int, y : Int) : Number

    fun times(other : Matrix3) : Matrix3 =
}

fun matrixOf(
        a : Number, b : Number, c : Number,
        d : Number, e : Number, f : Number,
        g : Number, h : Number, i : Number) = object : Matrix3 {
    val all = array(array(a, b, c), array(d, e, f), array(g, h, i))

    override fun element(x: Int, y: Int) = all[x][y]

    override fun times(other: Matrix3): Matrix3 {
        throw UnsupportedOperationException()
    }
}