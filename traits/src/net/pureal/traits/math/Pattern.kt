package net.pureal.traits.math

public trait Pattern {
    val pattern: Real
    fun match(r: Real): Boolean = pattern.matchWithThisPattern(r)
}

fun pattern(r: Real): Pattern = object : Pattern {
    override val pattern: Real = r
}