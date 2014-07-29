package net.pureal.traits.math

import net.pureal.traits.math.sets.*

public trait Set {
    fun contains(other: Set): Boolean {
        if(other is SetUnion) {
            return contains(other.subset1) && contains(other.subset2)
        }
        if(other is SetIntersection) {

        }
        return false
    }
    fun contains(other: Number): Boolean
    fun hasCommonElementsWith(other: Set): Boolean
}