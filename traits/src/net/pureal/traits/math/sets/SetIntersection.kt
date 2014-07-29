package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public trait SetIntersection : Set {
    val superset1: Set
    val superset2: Set

    fun combineSets(): Set {
        // TODO
        return this
    }

    override fun hasCommonElementsWith(other: Set): Boolean {
        //TODO
        return false
    }
}