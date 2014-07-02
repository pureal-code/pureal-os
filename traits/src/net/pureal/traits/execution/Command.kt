package net.pureal.traits.execution

import net.pureal.traits.math.Real


public trait Command {
    val pattern : CommandPattern
    val parameters : Array<Array<String>>
    val callback : (ParameterList) -> Real
    val
}