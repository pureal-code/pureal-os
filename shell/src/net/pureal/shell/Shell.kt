package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*

// TODO brings reference barf: import org.jetbrains.jet.codegen.*

class Shell(val screen: Screen, val pointers: ObservableIterable<PointerKeys>, val keys: ObservableIterable<Key>) {
    {
        registerInputs()
        val halfWidth = (screen.shape as Rectangle).size.x.toDouble() / 2
        fun logoRect(angle : Number) = button(
                shape = rectangle(vector(300, 100)),
                transform = Transforms2.translation(vector(-70, 60)) before Transforms2.rotation(angle) before Transforms2.scale(0.5 * angle.toDouble()),
                fill = Fills.solid(Colors.white),
                onClick = {println("This is da fucking Pureal logo!")})

        fun star(count : Int) = count.indices map {logoRect(it * 3.14159 * 2 / count - Math.PI / 2)}

        screen.content = composed(elements = observableIterable(star(7)))

        /* composed(elements = setOf(button(
                shape = rectangle(vector(halfWidth, halfWidth)),
                fill = Fills.solid(Colors.blue),
                onClick = { println("Biatch!")}
        ), button(
                shape = rectangle(vector(halfWidth, halfWidth)),
                transform = Transforms2.translation(vector(halfWidth / 2, 0)),
                fill = Fills.solid(Colors.red),
                onClick = { println("Yeah!")}
        ))) */
    }

    fun registerInputs() = {
        val touchKey = (keys filter {it.definition.command == Commands.Touch.touch}).single()
        pointers.forEach {it.click += {(location) -> content.elementsAt(location).forEach { if (it is Clickable<*>) it.onClick(absoluteTransform(it)(location)) } }
    }
}