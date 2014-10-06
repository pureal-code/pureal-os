package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*
import java.util.Date

// TODO brings reference barf: import org.jetbrains.jet.codegen.*

class Shell(val screen: Screen, val pointers: ObservableIterable<PointerKeys>, val keys: ObservableIterable<Key>, defaultFont: Font) {
    private val exampleContent = object : Any() {
        fun rotatedScaledRectangles(): Composed<*> {
            fun logoRect(angle: Number) = button(
                    shape = rectangle(vector(300, 100)) transformed (Transforms2.translation(vector(-70, 60)) before Transforms2.rotation(angle) before Transforms2.scale(0.5 * angle.toDouble())),
                    fill = Fills.solid(Colors.white),
                    onClick = { println("This is da fucking Pureal logo!") })

            fun star(count: Int) = count.indices map { transformedElement(logoRect(it * 3.14159 * 2 / count - Math.PI / 2)) }

            return composed(elements = observableIterable(star(7)))
        }

        fun someText(font: Font): Composed<*> {
            val text = """Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam
nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat,
sed diam voluptua. At vero eos et accusam et justo duo dolores et ea
rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem
ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing
elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna
aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo
dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus
est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur
sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et
dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam
et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea
takimata sanctus est Lorem ipsum dolor sit amet. AYA �¶Ѽ†◊²³"""
            val t = transformedElement(
                    textElement(text, font, size = 60, fill = Fills.solid(Colors.white)),
                    object : Transform2 {
                        override val matrix: Matrix3 get() = (Transforms2.translation(vector(-1000, 400)) before Transforms2.rotation(Date().getTime() * 0.0005) before Transforms2.scale(.2 + Math.pow(1 + Math.pow(Math.sin(Date().getTime() * 0.0002), 5.0), 5.0))).matrix
                    }
            )
            //val k = transformedElement(textElement("Kotlin rocks!", font, size = 24, fill = Fills.solid(Colors.white)), Transforms2.rotation(-Math.PI / 10))
            //val h = transformedElement(textElement("like a hardcore banana", font, size = 24, fill = Fills.solid(Colors.white)), Transforms2.scale(0.5) before Transforms2.translation(vector(0,-(screen.shape as Rectangle).size.y.toDouble() / 3.0)))
            return composed(observableIterable(listOf(t)))//k, h)))
        }

        fun composedWithButton(): Composed<*> {
            fun randomColor() = color(Math.random(), Math.random(), Math.random())

            val text = textElement("not a button!", defaultFont, 60, Fills.solid(Colors.white))

            val size = vector(100, 100)
            fun b(x: Int, y: Int): TransformedElement<Any?> {
                var color = randomColor()
                return transformedElement(button(shape = rectangle(size), fill = object : Fill {
                    override fun colorAt(location: Vector2) = color
                }) {
                    color = randomColor()
                }, Transforms2.translation(vector(x * size.x.toDouble(), y * size.y.toDouble())))
            }

            val a = 3
            //transformedElement(text, Transforms2.translation(vector(0, -100)))
            return composed(observableIterable(-a..a flatMap { x -> -a..a map { y -> b(x, y) } }))
        }

        fun keyboardText() : Composed<*> {
            val textElement = object : TextElement {
                override val font: Font = defaultFont
                override val size: Number = 40
                override var content: String = "Hallo"
                override val fill: Fill = Fills.solid(Colors.white)
                override val changed = trigger<Unit>()
            }
            keys mapObservable { it.pressed } startKeepingAllObserved {
                textElement.content = it.definition.command.name
                textElement.changed()
            }
            return composed(observableIterable(listOf(transformedElement(textElement))))
        }
    }

    {
        screen.content = exampleContent.keyboardText()
        registerInputs()
    }

    fun registerInputs() {
        pointers mapObservable { it.pressed } startKeepingAllObserved { p ->
            screen.content.elementsAt(p.pointer.location) forEach {
                val element = it.element
                if (element is Clickable<*>) element.onClick(pointerKey(p.pointer relativeTo it.transform, p.key))
            }
        }
    }
}




