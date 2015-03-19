package org.alex.stundenplan;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import android.support.v7.app.ActionBarActivity;

/**
 * @author Alex Severin
 * 
 */
public class Plan extends DrawerActivity {

    protected TextView mensaText;
    protected static TextView dayView;
    protected ListView listView;
    protected View footer;
    private static Context context;
    static CustomAdapter customAdapter;
    protected String fileName;
    //================================onCreate====================================
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = Plan.this;
//--------------------Activity view----------------------------------
//Adding our layout to parent class frame layout.
        getLayoutInflater().inflate(R.layout.listview, frameLayout);
//Setting title and itemChecked
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);
//setContentView(R.layout.listview);
//-------------------------adapter:--------------------------------------
        listView = (ListView)findViewById(R.id.listView1);
        makeAdapter();
    }
    //----------------------------------------------------------------------------
    protected void onPause(){
    	super.onPause();
    }
    protected void onResume(){
    	super.onResume();
    }
    //===================================Auxiliar Methods================================
    public void makeAdapter(){
        CacheManager cacheManager = new CacheManager(context);
        ConnectivityManager connMgr = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        //if connection exist go in asynctask to init adapter
        if (cacheManager.isCache()){
            AdapterTask adapterTask = new  AdapterTask(this, this.listView);
            adapterTask.execute();
            return;
        }
        if (networkInfo != null && networkInfo.isConnected() ) {

        	AdapterTask adapterTask = new  AdapterTask(this, this.listView);
            adapterTask.execute();
        } else {
        	Toast.makeText(this, "Leider keine Internetverbindung... Für den Stundenplan brauchen Sie Internet ", Toast.LENGTH_LONG).show();
			}
    	
   	 
    }
}



