package net.pureal.traits.math


public trait Real : MathValue {

}

fun Number.toReal() : Real = object : Real{
    override val operator = null;
    override val operands = Array<Number>(1, {this})
}