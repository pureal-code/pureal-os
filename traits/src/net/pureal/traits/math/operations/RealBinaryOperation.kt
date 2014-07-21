package net.pureal.traits.math.operations

import net.pureal.traits.math.*

public trait RealBinaryOperation : Real {
    val description: String
    val priority: Int
    val operationSign: String

    val isOrderDependent: Boolean

    val value1: Real
    val value2: Real

    override fun calculate(): Real

    override fun approximate(): InternalReal

    final fun getSubString(v: Real): String {
        if (v !is RealBinaryOperation) return v.toString()
        if (isOrderDependent) {
            return if (v.priority <= priority) "(${v.toString()})"
            else v.toString()
        } else {
            return if (v.priority < priority) "(${v.toString()})"
            else v.toString()
        }
    }

    override fun toString(): String {
        val v1str = getSubString(value1)
        val v2str = getSubString(value2)
        return "${v1str} ${operationSign} ${v2str}"
    }

    override fun equals(other: Any?) : Boolean =
            other is RealBinaryOperation && other.operationSign == operationSign
            && ((other.value1 == value1 && other.value2 == value2) || (!isOrderDependent && other.value2 == value1 && other.value1 == value2))
}