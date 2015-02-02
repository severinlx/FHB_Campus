package org.alex.stundenplan.backendConnection;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ListView;

import org.alex.stundenplan.R;

//import android.support.v7.app.ActionBarActivity;

/**
 * @author Alex Severin
 */
public class Backend_Connect extends Activity {

    private static Context context;
    protected ListView listView;

    //================================onCreate====================================
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //--------------------Activity view----------------------------------
        setContentView(R.layout.abc_screen_simple);
        //-------------------------backend:--------------------------------------
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Francesco"));
        //checkBackend();
    }

    //----------------------------------------------------------------------------
    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    //===================================Auxiliar Methods================================
    public void checkBackend() {

        EndpointsAsyncTask Task = new EndpointsAsyncTask();
        Task.execute();


    }
}



