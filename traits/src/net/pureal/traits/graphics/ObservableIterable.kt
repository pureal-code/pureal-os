package net.pureal.traits.graphics

import net.pureal.traits.Observable

trait ObservableIterable<out T> : Iterable<T> {
    val added: Observable<T>
    val removed: Observable<T>
}