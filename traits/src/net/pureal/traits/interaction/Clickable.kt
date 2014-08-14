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

    fun onClick(pointerKey: PointerKey) {}
}