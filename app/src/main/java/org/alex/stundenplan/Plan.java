package org.alex.stundenplan;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

//import android.support.v7.app.ActionBarActivity;

/**
 * @author Alex Severin
 * 
 */
public class Plan extends Activity {

    static CustomAdapter customAdapter;
    private static Context context;
    protected ListView listView;

    //================================onCreate====================================
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //--------------------Activity view----------------------------------
        //Adding our layout to parent class frame layout.
        //getLayoutInflater().inflate(R.layout.listview, frameLayout);
        //Setting title and itemChecked
       // mDrawerList.setItemChecked(position, true);
       // setTitle(listArray[position]);
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
        ConnectivityManager connMgr = (ConnectivityManager) 
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        //if connection exist go in asynctask to init adapter
        if (networkInfo != null && networkInfo.isConnected()) {
        	AdapterTask adapterTask = new  AdapterTask(this, customAdapter, listView);
            adapterTask.execute();
        } else {
        	Toast.makeText(this, "Leider keine Internetverbindung... Für den Stundenplan brauchen Sie Internet ", Toast.LENGTH_LONG).show();
			}
    	
   	 
    }
}



