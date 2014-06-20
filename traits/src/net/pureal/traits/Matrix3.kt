package net.pureal.traits

trait Matrix3 {
    fun element(x : Int, y : Int) : Number

    fun times(other : Matrix3) : Matrix3
}

trait Matrix2 {
    fun element(x : Int, y : Int) : Number

    fun times(other : Matrix2) : Matrix2
}