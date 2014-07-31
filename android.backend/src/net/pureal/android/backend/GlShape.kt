package net.pureal.android.backend

import net.pureal.traits.math.Shape
import net.pureal.traits.Vector2
import net.pureal.traits.math.Rectangle
import net.pureal.traits.math.TransformedShape
import net.pureal.traits.vector

abstract class GlShape(open val original: Shape? = null) : Shape {
    override fun contains(location: Vector2): Boolean = original?.contains(location) ?: false
    abstract val coordinates: FloatArray
    abstract val drawOrder: ShortArray
}

fun glShape(original: Shape): GlShape {
    return when (original) {
        is GlShape -> original
        is TransformedShape -> GlTransformedShape(original)
        is Rectangle -> GlRectangle(original)
        else -> throw UnsupportedOperationException("No OpenGL implementation for shape '${original}'.")
    }
}

class GlTransformedShape(override val original: TransformedShape) : GlShape(original) {
    val glOriginal = glShape(original.original)
    override val coordinates: FloatArray get() {
        val originalCoordinates = glOriginal.coordinates
        val result = FloatArray(originalCoordinates.size)
        val transformed = (glOriginal.coordinates.withIndices() groupBy { it.first / 3 }).values() flatMap {
            original.transform(vector(it[0].second, it[1].second)) + listOf(it[2].second) map { it.toFloat() }
        }
        for (i in transformed.withIndices()) {
            result[i.first] = i.second
        }
        return result
    }
    override val drawOrder: ShortArray get() = glOriginal.drawOrder
}

class GlRectangle(override val original: Rectangle) : GlShape(original) {
    override val coordinates: FloatArray get() {
        val x = original.size.x.toFloat() / 2
        val y = original.size.y.toFloat() / 2
        val z = 0f
        return floatArray(
                +x, +y, z, // 0 top right
                -x, +y, z, // 1 top left
                -x, -y, z, // 2 bottom left
                +x, -y, z  // 3 bottom right
        )
    }
    override val drawOrder = shortArray(0, 1, 2, 3)
}