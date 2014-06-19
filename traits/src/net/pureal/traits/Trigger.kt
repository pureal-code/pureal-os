package net.pureal.traits

trait Trigger<T> : Observable<T> {
    final fun invoke(info : T) { notifyObservers(info) }
}

fun triggerOf<T>() = object : Trigger<T> {
    override val observers = hashSetOf<(T) -> Unit>()
}

fun Trigger<Unit>.invoke() { this.invoke(Unit.VALUE) }