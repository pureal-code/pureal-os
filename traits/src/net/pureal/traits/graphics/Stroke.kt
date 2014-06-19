package net.pureal.traits.graphics

trait Stroke {
    val width : Number
}

trait InvisibleStroke : Stroke {
    final override val width : Number get() = 0
}

trait FilledStroke : Stroke {
    val fill : Fill
}