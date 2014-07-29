package net.pureal.traits.math.sets
import net.pureal.traits.math.*

public object EmptySet : Set {
    override fun contains(other: Number): Boolean = false
    override fun hasCommonElementsWith(other: Set): Boolean = false
}