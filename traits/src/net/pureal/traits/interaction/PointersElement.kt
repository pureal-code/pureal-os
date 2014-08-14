package net.pureal.traits.interaction

import net.pureal.traits.graphics.Element

trait PointersElement<T> : Element<T> {
    fun onPointerKeyPressed(pointerKey: PointerKey) {}
    fun onPointerKeyReleased(pointerKey: PointerKey) {}
    fun onPointerMoved(pointer: Pointer) {}
    fun onPointerEntered(pointer: Pointer) {}
    fun onPointerLeaved(pointer: Pointer) {}
}