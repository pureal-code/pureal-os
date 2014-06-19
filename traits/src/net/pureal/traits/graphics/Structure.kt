package net.pureal.traits.graphics

import net.pureal.traits.Observable

trait Element {
    val transform: Transform
    val changed : Observable<Unit>
}

trait Composed {
    val elements : Set<Element>
    val added : Observable<Element>
    val removed : Observable<Element>
}

trait ComposedElement : Composed, Element