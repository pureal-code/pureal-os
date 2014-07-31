package net.pureal.android.backend

import net.pureal.traits.*
import net.pureal.traits.graphics.*
import android.opengl.GLES20
import java.nio.FloatBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.ShortBuffer
import java.util.ArrayList


abstract class GlElement(open val original: Element<*>, val screen: GlScreen) : Element<Any?> {
    override val shape: GlShape get() = glShape(original.shape)
    override val changed: Observable<Unit> get() = original.changed
    override val content: Any? get() = original.content;
    abstract fun draw(parentTransform: Transform2)
}

fun glElement(original: Element<*>, screen: GlScreen): GlElement {
    return when (original) {
        is GlElement -> original
        is Composed<*> -> GlComposed(original, screen)
        is TextElement -> GlTextElement(original, screen)
        is ColoredElement<*> -> GlColoredElement(original, screen)
        else -> throw UnsupportedOperationException("No OpenGL implementation for element '${original}'.")
    }
}

trait GlTransformedElement : TransformedElement<Any?> {
    override val element: GlElement
    override val transform: Transform2
}

fun glTransformedElement(element : GlElement, transform : Transform2 = Transforms2.identity) = object : GlTransformedElement {
    override val element = element
    override val transform = transform
}

fun glTransformedElement(element : TransformedElement<*>, screen : GlScreen) = glTransformedElement(glElement(element.element, screen), element.transform)

class GlComposed(override val original: Composed<*>, screen: GlScreen) : GlElement(original, screen), Composed<Any?> {
    val elements = (original mapObservable { glTransformedElement(it, screen) }).toArrayList()
    override fun iterator() = elements.iterator()
    override val added = trigger<GlTransformedElement>()
    override val removed = trigger<GlTransformedElement>();
    {
        original.added addObserver {(addedElement) ->
            val glElement = glTransformedElement(addedElement, screen)
            elements.add(glElement)
            added(glElement)
        }
        original.removed addObserver {(removedElement) ->
            val glElement = (elements.singleOrNull { element -> element.element.original === removedElement })
            if (glElement != null) {
                elements.remove(glElement)
                removed(glElement)
            }
        }
    }
    override fun draw(parentTransform: Transform2) = elements.reverse() forEach { it.element.draw(it.transform before parentTransform) }
}



open class GlColoredElement(override val original: ColoredElement<*>, screen: GlScreen) : GlElement(original, screen), ColoredElement<Any?> {
    override val shape: GlShape  get () = glShape
    override val fill: Fill get() = original.fill
    override fun draw(parentTransform: Transform2) {
        val transform = parentTransform
        val matrixHandle = GLES20.glGetUniformLocation(screen.program!!, "matrix");
        val m = transform.matrix
        val glMatrix = floatArray(
                m.a.toFloat(), m.d.toFloat(), 0f, m.g.toFloat(),
                m.b.toFloat(), m.e.toFloat(), 0f, m.h.toFloat(),
                0f, 0f, 1f, 0f,
                m.c.toFloat(), m.f.toFloat(), 0f, m.i.toFloat())
        GLES20.glUniformMatrix4fv(matrixHandle, 1, false, glMatrix, 0);
        val positionHandle = GLES20.glGetAttribLocation(screen.program!!, "position")
        GLES20.glEnableVertexAttribArray(positionHandle)
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, vertexBuffer)
        val colorHandle = GLES20.glGetUniformLocation(screen.program!!, "color")
        val color = fill.colorAt(vector(0, 0))
        GLES20.glUniform4fv(colorHandle, 1, floatArray(
                color.r.toFloat(),
                color.g.toFloat(),
                color.b.toFloat(),
                color.a.toFloat()), 0)
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLE_FAN, shape.drawOrder.size,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer)
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
    private var glShape : GlShape = glShape(original.shape)
    private var vertexBuffer = buildVertexBuffer()
    private var drawListBuffer = buildDrawListBuffer()
    fun buildVertexBuffer(): FloatBuffer {
        // 4 bytes per float
        val byteBuffer = ByteBuffer.allocateDirect(shape.coordinates.size * 4)!! order ByteOrder.nativeOrder()!!;
        val floatBuffer = byteBuffer.asFloatBuffer()!!
        floatBuffer put shape.coordinates position 0
        return floatBuffer
    }
    fun buildDrawListBuffer(): ShortBuffer {
        // 2 bytes per short
        val byteBuffer = ByteBuffer.allocateDirect(shape.coordinates.size * 2)!! order ByteOrder.nativeOrder()!!;
        val shortBuffer = byteBuffer.asShortBuffer()!!
        shortBuffer put shape.drawOrder position 0
        return shortBuffer
    }
    {
        changed addObserver {
            glShape = glShape(original.shape)
            vertexBuffer = buildVertexBuffer()
            drawListBuffer = buildDrawListBuffer()
            // how? redraw()
        }
    }
}
