package net.pureal.traits.execution

import java.util.HashMap
import net.pureal.traits.math.Real

public trait ParameterList : HashMap<String, Real> {
    val operand1 : Real? get() = null
    val operand2 : Real? get() = null
}

fun parameterList() : ParameterList = object : ParameterList {

}

fun parameterList(op1 : Real) : ParameterList = object : ParameterList {
    override val operand1 = op1
    override val operand2 = op1
}

fun parameterList(op1 : Real, op2 : Real) : ParameterList = object : ParameterList {
    override val operand1 = op1
    override val operand2 = op2
}