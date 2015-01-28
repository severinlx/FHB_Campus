package org.alex.stundenplan;
import org.alex.stundenplan.mensa.*;


import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * @author Alex Severin
 * 
 */
public class Plan extends DrawerActivity {
	protected TextView mensaText;
	protected static TextView dayView;
	protected ListView listView;
	protected View footer;
	private static  Context context;	
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
    
    /*//------------------------mensa-----------------------------
  		public void setMensaText(String str){
  			//set the textView for Meals
  		mensaText = (TextView)findViewById(R.id.mensaText);
  	    mensaText.setText(str);
  		}
  		public void showMensa(){
  			//on Mensautton Click method
  			Calendar c = Calendar.getInstance();
  			int hourNow = c.get(Calendar.HOUR_OF_DAY);
  			int minuteNow = c.get(Calendar.MINUTE);
  			int day = c.get(Calendar.DAY_OF_WEEK);
  			int timeNow = hourNow*100+minuteNow;
  			ConnectivityManager connMgr = (ConnectivityManager) 
  		  	getSystemService(Context.CONNECTIVITY_SERVICE);
  		  	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
  			 
  			 
  			
  			//if Sontag or Samstag show for Montag
  			if((day==1&&timeNow<1400)||day==7){
  				Intent myIntent = new Intent(this, MensaActivity.class);
  				myIntent.putExtra("day","montag");
  	  	        startActivity(myIntent);
  		}
  			//if afternoon, showing for tomorrow
  			if(timeNow>1400 && timeNow<1800 && (day!=1*//*for sunday afternoon we show menu*//*)){
  				Intent myIntent = new Intent(this, MensaActivity.class);
  				myIntent.putExtra("day","morgen");
  	  	        startActivity(myIntent);
  			}
  	       
  	        else if (networkInfo != null && networkInfo.isConnected()) {
  	        	Intent myIntent = new Intent(this, MensaActivity.class);
  	        	myIntent.putExtra("day","heute");
  	  	        startActivity(myIntent);
  	        } else {
  	        	Toast.makeText(this, "Leider keine Internetverbindung... Für den Mensaplan brauchen Sie Internet ", Toast.LENGTH_LONG).show();
				
  	        }
  			
  		}*/
  		//-----------------------------------------------------------------------
	
	//------------------------------------------Menu--------------------------------------------------
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_mensa:
                showMensa();
                return true;
            case R.id.action_settings:
                startSet();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
  //-----------------------Settings----------------------------
    public static void startSet(){
    	//OnClick for Fach Settings
    	//customAdapter.notifyDataSetInvalidated();
    	Intent myIntent = new Intent(context, Login.class);
        context.startActivity(myIntent);
        }
    
    //----------------------Prefferences-----------------------
    public String[] getPreferences(){
    	SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
    	Integer fach = sharedPref.getInt("fach", -1);
    	Integer semester = sharedPref.getInt("semester", -2);
		String selectedGroup = sharedPref.getString("group", "no");
		String[] preferences = {Integer.toString(fach), Integer.toString(semester), selectedGroup};
		return preferences;
    }
    */
}



