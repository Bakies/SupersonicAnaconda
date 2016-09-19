package es.baki.mitchnpals.simplicity;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    public ArrayList<GLSquare> squares = new ArrayList<>();

    public MyGLRenderer() {
        squares.add(new GLSquare());
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        // #7B1FA2 | R:123 G:31 B:162 | 0.482, 0.122, 0.635
        GLES20.glClearColor(0.482f, 0.122f, 0.635f, 1.0f);

        Log.i("Simplicity", "GL Surface created");
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }
}