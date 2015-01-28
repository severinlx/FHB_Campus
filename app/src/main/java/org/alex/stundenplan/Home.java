package org.alex.stundenplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * @author Alex Severin
 * 
 */

public class Home extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		SharedPreferences myPrefs = getSharedPreferences("Login",Context.MODE_PRIVATE);
		String fach=myPrefs.getString("fach", "Default_Value");
		boolean preferIsSet = preferIsSet();
		/*if(preferIsSet){
			Intent myIntent = new Intent(this, Plan.class);
		    this.startActivity(myIntent);
		    finish();
		}
	
		else{*/
			Intent myIntent = new Intent(this, wellcome.class);
			this.startActivity(myIntent);
		    finish();
		//}
		
}
	public boolean preferIsSet(){
		SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
		String selectedFach = sharedPref.getString("fach", "no");
		String selectedSemester = sharedPref.getString("semester", "no");
		String selectedGroup = sharedPref.getString("group", "no");
				//start plan activity:
		if(!selectedFach.equals("no")&&!selectedSemester.equals("no")&&!selectedGroup.equals("no")){
		     return true;
		}
		else return false;
	}
}
