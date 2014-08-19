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
                GLES20.glEnable(GLES20.GL_BLEND);
                GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
                program = createProgram();
                GLES20.glUseProgram(program!!);
                onReady(this@GlScreen);
            }
            override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
                GLES20.glViewport(0, 0, width, height)
            }
            override fun onDrawFrame(gl: GL10?) {
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
                glContent.draw(Transforms2.scale(1f / this@GlScreen.getWidth().toFloat(), 1f / this@GlScreen.getHeight().toFloat()))
            }
        })
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY)
    }
    private var glContent: GlComposed = GlComposed(composed(observableIterable(listOf())), this)
    override var content: Composed<*>
        get() = glContent
        set(value) {
            glContent = value as? GlComposed ?: GlComposed(value, this)
        }

    override val shape: Rectangle get() = rectangle(vector(this@GlScreen.getWidth(), this@GlScreen.getHeight()))
}

fun createProgram(): Int {
    val vertexShaderCode = """
                    uniform mat4 u_Matrix;
                    attribute vec4 a_Position;
                    attribute vec2 a_TexCoord;
                    varying vec2 v_TexCoord;
                    void main() {
                        v_TexCoord = a_TexCoord;
                        gl_Position = u_Matrix * a_Position;
                    }
                """
    val fragmentShaderCode = """
                    uniform vec4 u_Color;
                    varying vec2 v_TexCoord;
                    uniform sampler2D u_Texture;
                    const float smoothing = 1.0/8.0;
                    void main() {
                        float distance = texture2D(u_Texture, v_TexCoord).a;
                        float alpha = smoothstep(0.5 - smoothing, 0.5 + smoothing, distance);
                        gl_FragColor = vec4(u_Color.rgb, alpha);
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
