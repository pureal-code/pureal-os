package net.pureal.traits

trait Observable<T> {
    protected val observers : MutableSet<(T) -> Unit>
    final fun plusAssign(observer : (T) -> Unit) {
        observers.add(observer)
    }
    final fun minusAssign(observer : (T) -> Unit) {
        observers.remove(observer)
    }
    final protected fun notifyObservers(info : T) {
        for (observer in observers) {
            observer(info)
        }
    }
}

fun observable<T>(vararg observables : Observable<T>) = object : Observable<T> {
    {
        for (observable in observables) {
            observable += { ::notifyObservers }
        }
    }
    override val observers = hashSetOf<(T) -> Unit>()
}

fun observable() = observable<Unit>()