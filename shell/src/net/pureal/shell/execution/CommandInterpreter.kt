package net.pureal.shell.execution

import net.pureal.traits.execution.Command


object CommandInterpreter {
    var CommandList : Array<Command?> = Array<Command?>(0, {null}) // TODO: no better solution yet for initialization
}