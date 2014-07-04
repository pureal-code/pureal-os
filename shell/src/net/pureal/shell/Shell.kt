package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*

class Shell(val screen: Screen) {
    {
        val button = RectangleButton(vector(.5, .5), Colors.gray)
        button.content += {println("button clicked!")}

        screen.relativePointerInput(button.element).click += {(location) ->
            val relativeLocation = screen.absoluteTransform(button.element)(location)
            if(button.element.shape.contains(relativeLocation)) {
                button.content()
            }
        }

        screen.show(object : Composed {
            override val changed: Observable<Unit> = observable<Unit>()
            override val shape = screen.rectangle
            override val transform = Transforms2.identity
            override val elements = setOf(button.element)
            override val added = observable<Element>()
            override val removed = observable<Element>()
        })
    }
}

class RectangleButton(size : Vector2, color : Color) : Button {
    override val content = trigger<Unit>()
    override val element = object : ColoredElement {
        override val shape = singleColored(rectangle(size), color)
        override val transform = Transforms2.identity
        override val changed = observable()
    }
}