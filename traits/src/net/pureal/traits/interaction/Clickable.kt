package net.pureal.traits.interaction

import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*

trait Clickable<T> : PointersElement<T> {
    private val pressedKeysThatCouldLeadToClick: MutableCollection<PointerKey>

    override fun onPointerKeyPressed(pointerKey: PointerKey) {
        pressedKeysThatCouldLeadToClick.add(pointerKey)
    }

    override fun onPointerKeyReleased(pointerKey: PointerKey) {
        if (pressedKeysThatCouldLeadToClick.contains(pointerKey)) {
            pressedKeysThatCouldLeadToClick.remove(pointerKey)
            onClick(pointerKey)
        }
    }

    override fun onPointerLeaved(pointer: Pointer) {
        if (pressedKeysThatCouldLeadToClick.contains(pointer)) {
            pressedKeysThatCouldLeadToClick.remove(pointer)
        }
    }

    fun onClick(pointerKey: PointerKey) {
    }
}

trait PointersElement<T> : Element<T> {
    fun onPointerKeyPressed(pointerKey: PointerKey) {
    }
    fun onPointerKeyReleased(pointerKey: PointerKey) {
    }
    fun onPointerMoved(pointer: Pointer) {
    }
    fun onPointerEntered(pointer: Pointer) {
    }
    fun onPointerLeaved(pointer: Pointer) {
    }
}

trait KeysElement<T> : Element<T> {
    fun onKeyPressed(key: Key) {
    }
    fun onKeyReleased(key: Key) {
    }
    fun onGotKeysFocus(keys: Iterable<Key>) {
    }
    fun onLostKeysFocus(keys: Iterable<Key>) {
    }
}