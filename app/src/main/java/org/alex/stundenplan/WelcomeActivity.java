package org.alex.stundenplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import org.alex.stundenplan.stundenplan.PlanActivity;

/**
 * @author Alex Severin
 * 
 */
/*
 * TODO
 * 1 mensa
 * 2 cache
 * daca ai accesat odata pastreaza in cache pe azi. Data viitoare utilizeaza aceasta daca nu e internet
 *  3 design
 * 
 */
public class WelcomeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		SharedPreferences sharedPref = getSharedPreferences("LoginNew",Context.MODE_PRIVATE);

		if(sharedPref.contains("URL")){
			Intent myIntent = new Intent(this, PlanActivity.class);
			this.startActivity(myIntent);
		    finish();
		}

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
	}
	
	public void openSettings(View view){

		Intent myIntent = new Intent(this, LoginActivity.class);
		this.startActivity(myIntent);
	    finish();
	}

}
