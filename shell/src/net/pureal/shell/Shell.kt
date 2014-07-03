package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.*

class Shell(val screen: Screen) {
    {
        val button = TestButton()
        button.value += {(Unit) -> { println("button clicked!") } }
        screen.show(object : Composed {
            override val elements: Set<Element> = setOf(button.element)
            override val added: Observable<Element> = observable<Element>()
            override val removed: Observable<Element> = observable<Element>()
        })
    }
}

class TestButton : Button {
    val clicked: Trigger<Unit> = trigger();
    override val value: Trigger<Unit> = clicked
    override val element: Element = object : Rectangle {
        override val size: Vector2 = vector(.5, .5)
        override val fill: Fill = object : SolidFill {
            override val color = Colors.gray
        }
        override val stroke: Stroke = object : FilledStroke {
            override val fill: Fill = object : SolidFill {
                override val color = Colors.black
            }
            override val width: Number = .1
        }
        override val transform: Transform2 = Transforms2.identity
        override val changed: Observable<Unit> = observable()
    }
    override val pointerInput: PointerInput = null!! // TODO...
}

