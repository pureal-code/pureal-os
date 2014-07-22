package net.pureal.traits.interaction

import net.pureal.traits.*

trait KeyDefinition {
    val command: Command
    val alternativeCommands: Iterable<Command> get() = listOf()
    val name: String get() = command.name
}

fun keyDefinition(command: Command, alternativeCommands: Iterable<Command> = listOf(), name: String = command.name) = object : KeyDefinition {
    override val command = command
    override val alternativeCommands = alternativeCommands
    override val name = name
}

trait Key {
    val definition: KeyDefinition
    val pressed : Boolean
    val down: Observable<Unit>
    val up: Observable<Unit>
}

fun test () {
    val v = vector(2, 3)
    val r = object: Vector2 { override val x = 2; override val y = 3}
}

trait Command {
    val name: String

    override fun equals(other : Any?) = if(other is Command) name == other.name else false
}

fun command(name : String) = object : Command {
    override val name = name
}

trait CharacterCommand : Command {
    val character : Char
}

object Commands {
    object Keyboard {
        fun character(char: Char) = object : CharacterCommand {
            override val name = String(charArray(char))
            override val character = char
        }

        fun specialWithCharacter(name: String, character: Char) = object : CharacterCommand {
            override val name = name
            override val character = character
        }

        val control = command("control")
        val alt = command("alt")
        val shift = command("shift")
        val altGr = command("alt gr")
        val start = command("start")
        val menu = command("menu")
        val escape = command("escape")
        val printScreen = command("print screen")

        val scrollLock = command("scroll lock")
        val numLock = command("num lock")
        val capsLock = command("caps lock")

        val space = specialWithCharacter("space", character = ' ')
        val tab = specialWithCharacter("tab", character = '\t')
        val enter = specialWithCharacter("enter", character = '\n')
        val delete = command("delete")
        val backspace = command("backspace")
        val insert = command("insert")

        val f1 = command("f17")
        val f2 = command("f2")
        val f3 = command("f3")
        val f4 = command("f4")
        val f5 = command("f5")
        val f6 = command("f6")
        val f7 = command("f7")
        val f8 = command("f8")
        val f9 = command("f9")
        val f10 = command("f10")
        val f11 = command("f11")
        val f12 = command("f12")
    }

    object Mouse {
        val primary = command("primary")
        val secondary = command("secondary")
    }

    object Navigation {
        val left = command("left")
        val right = command("right")
        val up = command("up")
        val down = command("down")
        val home = command("home")
        val end = command("end")
        val pageUp = command("pag up")
        val pageDown = command("page down")
        val navigateBackward = command("navigate backward")
        val navigateForward = command("navigate forward")
    }

    object Media {
        val toggle = command("toggleMedia")
        val increaseVolume = command("increase volume")
        val decreaseVolume = command("decrease volume")
        val mute = command("mute")
    }

    object Power {
        val power = command("power")
        val sleep = command("sleep")
        val wake = command("wake")
    }
}

object KeyDefinitions {
    fun left(command: Command, alternativeCommands: Iterable<Command> = listOf()) = keyDefinition(command, alternativeCommands, "left ${command.name}")
    fun right(command: Command, alternativeCommands: Iterable<Command> = listOf()) = keyDefinition(command, alternativeCommands, "right ${command.name}")
    fun numPad(command: Command, alternativeCommands: Iterable<Command> = listOf()) = keyDefinition(command, alternativeCommands, "num pad ${command.name}")
}

trait KeyCombination {
    val keys: Iterable<Key>
    val meaning : Command?
}