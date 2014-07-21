package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public trait SetUnion<T> : Set<T> {
    val subset1: Set<T>
    val subset2: Set<T>
}