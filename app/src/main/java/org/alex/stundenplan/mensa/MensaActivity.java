package org.alex.stundenplan.mensa;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.alex.stundenplan.DrawerActivity;
import org.alex.stundenplan.R;

public class MensaActivity extends DrawerActivity {
    protected TextView errortext;
    ListView listView;
    mensaAdapter adapter;
     boolean isCache;
     String day;

    protected void onPause(){
        super.onPause();
/*
        if(adapter!=null){
            adapter.notifyDataSetChanged();
            adapter=null;
        }*/
    }
    protected void onResume(){
        super.onResume();
        /*if(adapter!=null){
            adapter.notifyDataSetChanged();
            adapter=null;
        }
        setMeals();*/
    }
    protected void onStop(){
        super.onStop();
    }
    protected void onRestart(){
        super.onRestart();
    }
     //=================================================on Create===========================================
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

         //Adding our layout to parent class frame layout.
         getLayoutInflater().inflate(R.layout.mensa_activity, frameLayout);
         //Setting title and itemChecked
         mDrawerList.setItemChecked(position, true);
         setTitle(listArray[position]);
         //setContentView(R.layout.listview);

          //setContentView(R.layout.mensa_activity);
          // Get ListView object from xml
          listView = (ListView) findViewById(R.id.listViewMensa);
          /*setContentView(R.layout.listview);
  		  //-------------------------adapter:--------------------------------------
          listView = (ListView)findViewById(R.id.listView1);*/
          setMeals();
      }
     //=======================================================================================================
     //================================================setMeals(invoke Task->Adapter)=========================
 	public void setMeals(){
 		 Task task = new  Task(MensaActivity.this, adapter, listView);
 		  
 	     task.execute();
 	      
 	 }
 	//=========================================================================================================
/*
 	//------------------------------------------Menu--------------------------------------------------
 	@Override
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
 	            //showMensa();
 	            return true;
 	        case R.id.action_calendar:
 	            openCalendar();
 	            return true;
 	        case R.id.action_settings:
 	            startSet();
 	            return true;
 	        default:
 	            return super.onOptionsItemSelected(item);
 	    }
 	}
 	private void openCalendar() {
 		// TODO Auto-generated method stub
 		Intent myIntent = new Intent(this, Plan.class);
 	    this.startActivity(myIntent);
 	     
 	}
 	public void startSet(){
 		//OnClick for Fach Settings
 		Intent myIntent = new Intent(this, Login.class);
 	    this.startActivity(myIntent);
 	    }*/
}