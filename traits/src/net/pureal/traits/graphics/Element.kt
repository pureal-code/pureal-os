package net.pureal.traits.graphics

import net.pureal.traits.Observable

trait Element {
    val transform: Transform
    val changed : Observable<Unit>
}