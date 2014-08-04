package net.pureal.traits.interaction

import net.pureal.traits.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*

trait Button : Clickable<Trigger<Unit>>, ColoredElement<Trigger<Unit>> {
    override fun onClick(pointerKey: PointerKey) = content()
}

fun button(
        trigger: Trigger<Unit> = trigger<Unit>(),
        shape: Shape,
        fill: Fill,
        changed: Observable<Unit> = observable(),
        onClick: () -> Unit = {}) = object : Button {
    override val content = trigger
    override val shape = shape
    override val fill = fill
    override val changed = changed

    {
        content addObserver {onClick()}
    }
}