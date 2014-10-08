package net.pureal.android.backend

import net.pureal.traits.interaction.Command
import net.pureal.traits.interaction.Key
import net.pureal.traits.interaction.KeyDefinition
import net.pureal.traits.interaction.keyDefinition
import net.pureal.traits.trigger
import net.pureal.traits.graphics.ObservableIterable
import java.util.HashMap
import net.pureal.traits.interaction.Commands
import net.pureal.traits.interaction.Pointer
import net.pureal.traits.Vector2
import net.pureal.traits.zeroVector2
import android.view.KeyEvent

class AndroidKey(val command: Command) : Key {
    override val definition: KeyDefinition = keyDefinition(command)
    override var isPressed: Boolean = false
    override val pressed = trigger<Key>()
    override val released = trigger<Key>()

    fun press() {
        isPressed = true
        pressed(this)
    }

    fun release() {
        isPressed = false
        released(this)
    }
}

class AndroidKeyboard : ObservableIterable<AndroidKey> {
    private val keyMap = HashMap<Command, AndroidKey>()
    override val added = trigger<AndroidKey>()
    override val removed = trigger<AndroidKey>()
    override fun iterator() = keyMap.values().iterator()

    fun get(event: KeyEvent): AndroidKey? {
        val command = when {
            event.isPrintingKey() -> Commands.Keyboard.character(event.getUnicodeChar().toChar())
            else -> when (event.getKeyCode()) {
                KeyEvent.KEYCODE_SPACE-> Commands.Keyboard.space
                KeyEvent.KEYCODE_TAB-> Commands.Keyboard.tab
                KeyEvent.KEYCODE_ENTER-> Commands.Keyboard.enter
                KeyEvent.KEYCODE_DEL -> Commands.Keyboard.backspace
                KeyEvent.KEYCODE_BACK -> Commands.Navigation.backward
                KeyEvent.KEYCODE_DPAD_UP -> Commands.Navigation.up
                KeyEvent.KEYCODE_DPAD_DOWN -> Commands.Navigation.down
                KeyEvent.KEYCODE_DPAD_LEFT -> Commands.Navigation.left
                KeyEvent.KEYCODE_DPAD_RIGHT -> Commands.Navigation.right
                KeyEvent.KEYCODE_PAGE_UP -> Commands.Navigation.pageUp
                KeyEvent.KEYCODE_PAGE_DOWN -> Commands.Navigation.pageUp
                else -> return null
            }
        }
        val foundKey = keyMap[command]
        if (foundKey != null) return foundKey
        val newKey = AndroidKey(command)
        keyMap.put(command, newKey)
        added(newKey)
        return newKey

    }
}

class AndroidPointer : Pointer {
    override val moved = trigger<Pointer>()
    override var location: Vector2 = zeroVector2

    fun move(location: Vector2) {
        this.location = location
        moved(this)
    }
}