package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.*

class Shell(val screen: Screen) {
    {
        val button = RectangleButton(vector(.5, .5), Colors.gray)
        button.content += {println("button clicked!")}
        screen.show(object : Composed {
            override val elements: Set<Element> = setOf(button.element)
            override val added: Observable<Element> = observable<Element>()
            override val removed: Observable<Element> = observable<Element>()
        })
    }
}

class RectangleButton(size : Vector2, color : Color) : Button {
    override val content = trigger<Unit>()
    override val element = object : Rectangle {
        override val size = size
        override val fill = solidFill(color)
        override val stroke = object : InvisibleStroke {}
        override val transform = Transforms2.identity
        override val changed = observable()
    }
}