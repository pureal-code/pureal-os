package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public trait SetIntersection<T> : Set<T> {
    val superset1: Set<T>
    val superset2: Set<T>
}