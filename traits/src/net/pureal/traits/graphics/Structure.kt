package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*

trait Element {
    val transform: Transform2
    val changed : Observable<Unit>
}

trait Composed {
    val elements : Set<Element>
    val added : Observable<Element>
    val removed : Observable<Element>
}

trait ComposedElement : Composed, Element