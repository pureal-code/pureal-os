package net.pureal.android.backend

import net.pureal.traits.graphics.Composed
import net.pureal.traits.Trigger
import net.pureal.traits.trigger
import net.pureal.traits.graphics.Element
import net.pureal.traits.Transform2
import net.pureal.traits.Observable
import net.pureal.traits.graphics.ColoredElement
import android.opengl.GLES20
import java.nio.FloatBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.ShortBuffer


abstract class GlElement(open val original: Element<out Any?>, val screen: GlScreen) : Element<Any?> {
    override val transform: Transform2 get() = original.transform
    override val shape: GlShape get() = glShape(original.shape)
    override val changed: Observable<Unit> get() = original.changed
    override val content: Any? get() = original.content;
    abstract fun draw()
}

fun glElement(original: Element<out Any?>, screen: GlScreen): GlElement {
    return when (original) {
        is GlElement -> original
        is Composed -> GlComposed(original, screen)
        is ColoredElement -> GlColoredElement(original, screen)
        else -> throw UnsupportedOperationException("No OpenGL implementation for element '${original}'.")
    }
}

class GlComposed(override val original: Composed<out Any?>, screen: GlScreen) : GlElement(original, screen), Composed<Any?> {
    override val elements: MutableSet<GlElement> = (original.elements map { glElement(it, screen) }).toHashSet()
    override val added: Trigger<GlElement> = trigger()
    override val removed: Trigger<GlElement> = trigger();
    {
        original.added += {(addedElement) ->
            val glElement = glElement(addedElement, screen)
            elements.add(glElement)
            added(glElement)
        }
        original.removed += {(removedElement) ->
            val glElement = (elements.singleOrNull { element -> element.original === removedElement })
            if (glElement != null) {
                elements.add(glElement)
                added(glElement)
            }
        }
    }
    override fun draw() = elements forEach { it.draw() }
}

class GlColoredElement(override val original: ColoredElement<out Any?>, screen: GlScreen) : GlElement(original, screen), ColoredElement<Any?> {
    override fun draw() {
        val positionHandle = GLES20.glGetAttribLocation(screen.program!!, "vPosition")
        GLES20.glEnableVertexAttribArray(positionHandle)
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, vertexBuffer)
        val colorHandle = GLES20.glGetUniformLocation(screen.program!!, "vColor")
        GLES20.glUniform4fv(colorHandle, 1, floatArray(1f, 0f, 0f, 1f), 0)
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLE_FAN, shape.drawOrder.size,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer)
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
    private var vertexBuffer = buildVertexBuffer()
    private var drawListBuffer = buildDrawListBuffer()
    fun buildVertexBuffer(): FloatBuffer {
        // 4 bytes per float
        val byteBuffer = ByteBuffer.allocateDirect(shape.coordinates.size * 4) order ByteOrder.nativeOrder()!!;
        val floatBuffer = byteBuffer.asFloatBuffer()
        floatBuffer put shape.coordinates position 0
        return floatBuffer
    }
    fun buildDrawListBuffer(): ShortBuffer {
        // 2 bytes per short
        val byteBuffer = ByteBuffer.allocateDirect(shape.coordinates.size * 2) order ByteOrder.nativeOrder()!!;
        val shortBuffer = byteBuffer.asShortBuffer()
        shortBuffer put shape.drawOrder position 0
        return shortBuffer
    }
    {
        changed += {
            vertexBuffer = buildVertexBuffer()
            drawListBuffer = buildDrawListBuffer()
            // how? redraw()
        }
    }
}
