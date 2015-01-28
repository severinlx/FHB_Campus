package org.alex.stundenplan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ListView;

public  class AdapterTask extends AsyncTask<Object, Void, Void> {
	Context context;
	ListView listView;
	CustomAdapter customAdapter;
	ProgressDialog dialog;
	public AdapterTask(Context context, CustomAdapter customAdapt, ListView listview){
		this.context=context;
		this.customAdapter = customAdapt;
		this.listView = listview;
		
	}
	protected void onPreExecute() {
		 
		super.onPreExecute();
		dialog = ProgressDialog.show(context, "Herunterladen..", "Der Stundenplan wird heruntergeladen, warten sie bitte nur kurz..");
        
	}
	@Override
	protected Void doInBackground(Object... arg0) {
		initializeAdapter();
		// TODO Auto-generated method stub
		return null;
	}
	protected void onPostExecute(Void args) { 
	
		if(dialog.isShowing())
            dialog.dismiss();
		super.onPostExecute(args);
		listView.setAdapter(customAdapter);
	}
	//init adapter
	public  void initializeAdapter(){
		 
    	Integer[] preferences = getPreferences();
    	try {
			customAdapter = new CustomAdapter(context, preferences);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 
    }
	//get pref:
	public Integer[] getPreferences(){
    	SharedPreferences sharedPref = context.getSharedPreferences("Login",Context.MODE_PRIVATE);
        Integer fach = (Integer) sharedPref.getInt("fach", -1);
    	Integer semester = sharedPref.getInt("semester", -2);
		Integer selectedGroup = sharedPref.getInt("group", -3);
		
		Integer[] preferences = {fach, semester,selectedGroup};
		return preferences;
    }
}