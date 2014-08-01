package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public trait SetUnion : Set {
    val subset1: Set
    val subset2: Set

    fun simplifySets(): Set {
        // TODO
        return this
    }

    override fun hasCommonElementsWith(other: Set): Boolean {
        // TODO
        return false
    }
}