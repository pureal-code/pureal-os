package net.pureal.android.backend

import android.opengl.GLSurfaceView
import android.opengl.GLSurfaceView.Renderer
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig
import android.opengl.GLES20
import android.app.Activity
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*

class GlScreen (activity: Activity, onReady: (GlScreen) -> Unit) : GLSurfaceView(activity), Screen {
    {
        setEGLContextClientVersion(2)
        setEGLConfigChooser(8, 8, 8, 8, 16, 0)
    }
    var program: Int? = null// = createProgram();
    {
        setRenderer(object : Renderer {
            override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
                program = createProgram();
                GLES20.glUseProgram(program!!);
                onReady(this@GlScreen);
            }
            override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
                GLES20.glViewport(0, 0, width, height)
            }
            override fun onDrawFrame(gl: GL10?) {
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
                glContent.draw(Transforms2.scale(1 / this@GlScreen.getWidth().toFloat(), 1 / this@GlScreen.getHeight().toFloat()))
            }
        })
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY)
    }
    private var glContent: GlComposed = GlComposed(composed(setOf()), this)
    override var content: Composed<out Any?>
        get() = glContent
        set(value) {
            glContent = value as? GlComposed ?: GlComposed(value, this)
        }
    override fun absoluteTransform(element: Element<out Any?>): Transform2 {
        throw UnsupportedOperationException()
    }
    override val rectangle: Rectangle get () = rectangle(vector(this@GlScreen.getWidth(), this@GlScreen.getHeight()))
}

fun createProgram(): Int {
    val vertexShaderCode = """
                    uniform mat4 matrix;
                    attribute vec4 position;
                    void main() {
                        gl_Position = matrix * position;
                    }
                """
    val fragmentShaderCode = """
                    precision mediump float;
                    uniform vec4 color;
                    void main() {
                        gl_FragColor = color;
                    }
                """
    val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
    val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
    val program = GLES20.glCreateProgram()
    GLES20.glAttachShader(program, vertexShader)
    GLES20.glAttachShader(program, fragmentShader)
    GLES20.glLinkProgram(program)
    return program
}

fun loadShader(shaderType: Int, shaderCode: String): Int {
    val shader = GLES20.glCreateShader(shaderType)
    GLES20.glShaderSource(shader, shaderCode)
    GLES20.glCompileShader(shader)
    return shader;
}
