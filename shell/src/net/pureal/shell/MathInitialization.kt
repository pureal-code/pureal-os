package net.pureal.shell

import net.pureal.shell.execution.CommandInterpreter
import net.pureal.traits.execution.*

public fun init_math() {
    CommandInterpreter.CommandList += object : Command {
        override val pattern = CommandPattern.Unary
        override val parameters = parameterList()
    }
}