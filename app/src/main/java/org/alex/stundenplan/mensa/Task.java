package org.alex.stundenplan.mensa;

import java.io.File;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

public  class Task extends AsyncTask<Object, Void, Void> {

	ListView listView;
	Context context;
	private mensaAdapter mensaAdapter;
	public File file;

		
	public Task(Context context, ListView listView){

        this.context = context;
		this.listView=listView;

		}


	protected Void doInBackground(Object... param) {

		mensaAdapter  = new mensaAdapter(context);
		
		return null;

		}

	protected void onPostExecute(Void args) {

		super.onPostExecute(args);

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

