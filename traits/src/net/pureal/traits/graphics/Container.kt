package net.pureal.traits.graphics

import net.pureal.traits.Observable

trait Container {
    val elements : Set<Element>
    val added : Observable<Element>
    val removed : Observable<Element>
}