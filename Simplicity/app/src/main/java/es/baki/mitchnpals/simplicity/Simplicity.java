package es.baki.mitchnpals.simplicity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Simplicity extends Activity {
    private float lastDownTime = -1;
    private GLSurface mGLView;
    /*

    http://developer.android.com/reference/android/app/Activity.html
    
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("Simplicity", "Activity Created");

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new GLSurface(this);
        setContentView(mGLView);

        // TODO Show splash screen
    }

    @Override
    protected void onStart() {

        Log.i("Simplicity", "Activity Started");


        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Simplicity", "Activity Resumed");

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("Simplicity", "Activity Paused");

        // we can just pause the game at this point
    }

    @Override
    protected void onStop() { 
        super.onStop();

        Log.i("Simplicity", "Activity Stopped");


        // can we reover the game from this point? 
    }

    @Override
    protected void onDestroy() {

        Log.i("Simplicity", "Activity Destroyed");

        super.onDestroy();
    }

    @Override
    protected void onRestart() {

        Log.i("Simplicity", "Activity Restarted");

        super.onRestart();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (lastDownTime != event.getDownTime()) {
            Log.i("Simplicity", "New Touch!");
            lastDownTime = event.getDownTime();
        }
//        Log.i("Simplicity", String.format("%6f %6f %d", event.getX(), event.getY(), event.getDownTime()));
        return false;
    }
}
