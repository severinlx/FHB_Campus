package org.alex.stundenplan.mensa;
import org.json.JSONException;


import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public  class Task extends AsyncTask<Object, Void, Void> {
	//public static ArrayAdapter<String> adapter;	
	ListView listView;
	Context context;
	//ArrayList<String> meals=new ArrayList<String>();
	private mensaAdapter mensaAdapter;
		public TextView mensaText;
		String myList="";
		String text;
		ProgressDialog dialog;
		String error;
		boolean today, tomorrow, connectionProblem;
		public File file;
		static SharedPreferences sharedPref;
		int startDay;
		
		
	public Task(Context context, mensaAdapter mensaAdapter, ListView listView){
			this.context = context;
			this.listView=listView;
			this.mensaAdapter = mensaAdapter;
		}
		
		
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(context, "Herunterladen..", "Der Mensaplan wird heruntergeladen, warten sie bitte nur kurz..");
        }
		
	protected Void doInBackground(Object... param) {
		 
		//meals = getMealsOnline();
		 
		//if(meals==null) return null;
		mensaAdapter  = new mensaAdapter(context);
		
		return null;
		}

	protected void onPostExecute(Void args) {
		if(dialog.isShowing())
            dialog.dismiss();
		super.onPostExecute(args);
		if (listView == null) {
			 
		} else {
			 
		}
		listView.setAdapter(mensaAdapter);
		 

		if (mensaAdapter.mensaDayList.size() == 0) {
			Toast.makeText(
					context,
					"Connection problem or meals are not online. Please try again later"
					/*"Connection to slow or Cache problems. Verify Connection or delete Cache and Data in App Settings..."*/,
					Toast.LENGTH_LONG).show();
		} else {
			listView.setAdapter(mensaAdapter);
			
		}
	}


}

