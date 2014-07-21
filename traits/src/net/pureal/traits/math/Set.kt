package net.pureal.traits.math

public trait Set<T> {
    fun contains(other: Set<T>): Boolean
    fun contains(other: T): Boolean
}