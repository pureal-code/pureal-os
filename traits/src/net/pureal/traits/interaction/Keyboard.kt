package net.pureal.traits.interaction

import net.pureal.traits.*

trait Keyboard {
    val keys: Iterable<Key>
    val pressedKeys: Iterable<Key>
}

trait Key {
    val primaryCommand: Command
    val alternativeCommands: Iterable<Command> get() = listOf()
    val down: Observable<Unit>
    val up: Observable<Unit>
}

trait Command {
    val id: String

    override fun equals(other : Any?) = if(other is Command) id == other.id else false
}

fun command(id : String) = object : Command {
    override val id = id
}

trait CharacterCommand : Command {
    val character : Char
}

object Commands {
    object Keyboard {
        fun character(char: Char) = object : CharacterCommand {
            override val id = String(charArray(char))
            override val character = char
        }

        fun specialWithCharacter(id: String, character: Char) = object : CharacterCommand {
            override val id = id
            override val character = character
        }

        val control = command("control")
        val alt = command("alt")
        val altGr = command("altGr")
        val start = command("start")
        val menu = command("menu")
        val escape = command("escape")
        val printScreen = command("printScreen")

        val scrollLock = command("scrollLock")
        val numLock = command("numLock")
        val capsLock = command("capsLock")

        val space = specialWithCharacter("space", character = ' ')
        val tab = specialWithCharacter("tab", character = '\t')
        val enter = specialWithCharacter("enter", character = '\n')
        val delete = command("delete")
        val backspace = command("backspace")
        val insert = command("insert")

        val f1 = command("f1")
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
        val pageUp = command("pageUp")
        val pageDown = command("pageDown")
        val navigateBackward = command("navigateBackward")
        val navigateForward = command("navigateForward")
    }

    object Media {
        val toggle = command("toggleMedia")
        val increaseVolume = command("increaseVolume")
        val decreaseVolume = command("decreaseVolume")
        val mute = command("mute")
    }

    object Power {
        val power = command("power")
        val sleep = command("sleep")
        val wake = command("wake")
    }
}

trait KeyCombination {
    val keys: Iterable<Key>
    val meaning : Command?
}