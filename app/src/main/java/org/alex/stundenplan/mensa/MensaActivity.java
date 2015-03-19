package org.alex.stundenplan.mensa;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.alex.stundenplan.helpClasses.DrawerActivity;
import org.alex.stundenplan.R;

public class MensaActivity extends DrawerActivity {

    ListView listView;

    protected void onPause(){
        super.onPause();

    }
    protected void onResume(){
        super.onResume();

    }
    protected void onStop(){
        super.onStop();
    }
    protected void onRestart(){
        super.onRestart();
    }

     //=======================           on Create             ==========================

     protected void onCreate(Bundle savedInstanceState) {

          super.onCreate(savedInstanceState);

         //Adding our layout to parent class frame layout.
         getLayoutInflater().inflate(R.layout.activity_mensa_list, frameLayout);

         //Setting title and itemChecked
         mDrawerList.setItemChecked(position, true);
         setTitle(listArray[position]);

          // Get ListView object from xml
          listView = (ListView) findViewById(R.id.listViewMensa);


          setMeals();
      }

     //=================       setMeals(invoke Task->Adapter)       ======================
 	public void setMeals(){
 		 Task task = new  Task(MensaActivity.this, listView);
 		  
 	     task.execute();
 	      
 	 }

}