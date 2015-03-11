package org.alex.stundenplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.alex.stundenplan.backend.parserEndpoint.ParserEndpoint;
import org.alex.stundenplan.backend.parserEndpoint.model.ESubject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



//Adapter==============================Adapter=====================================
//@a Class to set Adapter with List of Subject and indicating view for diferent components of a subject. The subject list is retrieved in Constructor from parse class
public class CustomAdapter extends BaseAdapter {

	protected String fileName;
	protected Context context;
    private static ParserEndpoint parser=null;
    String url="Stundenplan/ws1415/plan/acs-3.html";
    List<ESubject> subjectList = new ArrayList<>();
	// -------------------------------Constructor----------------------------------;
	public CustomAdapter(Context context, Integer[] preferences)
			throws Exception {

		super();
		this.context = context;
		// get list in Background:
        if(parser == null) { // Only do this once
            ParserEndpoint.Builder builder = new ParserEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://fhb-backend.appspot.com/_ah/api/");

            // options for running against local devappserver
            // - 10.0.2.2 is localhost's IP address in Android emulator
            // - turn off compression when running against local devappserver
            // .setRootUrl("http://10.0.2.2:8080/_ah/api/")
            // .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            //@Override
            // public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
            //      abstractGoogleClientRequest.setDisableGZipContent(true);
            // }
            //  });

            // end options for devappserver
            builder.setApplicationName("FHB");
            parser  = builder.build();
        }

        //----------------------main method:--------------------------------

        try {

            subjectList = parser.getList(url).execute().getItems();

        } catch (IOException e) {
            e.printStackTrace();
        }



            //todo remember old version of becoming list
			//subjectList = simpleParser.getList(context, preferences);

	}

	// --------------------------------------Adapter Get View--------------------------------
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arg1 = inflater.inflate(R.layout.listitem, arg2, false);
		}
		// get Subject from list:
		ESubject subjItem = subjectList.get(arg0);
		// TextViews set layout:
		TextView dayView = (TextView) arg1.findViewById(R.id.day);
		TextView timeView = (TextView) arg1.findViewById(R.id.time);
		TextView subjView = (TextView) arg1.findViewById(R.id.subj);
		TextView roomView = (TextView) arg1.findViewById(R.id.room);

		// show day only in front of 1 lesson
		if (true == true) {
			dayView.setText(subjItem.getDay());
			dayView.setVisibility(View.VISIBLE);
		} else {
			dayView.setText("");
			dayView.setVisibility(View.GONE);
		}
		// show time, subj and room:
		timeView.setText(subjItem.getTime());
		subjView.setText(subjItem.getSubj());
		roomView.setText(subjItem.getRoom());
		return arg1;
	}

    //___________________________________________________________________________________________

	// ----------------------------------------get and Set-----------------------------
	public List<ESubject> getSubjectList() {
		return subjectList;
	}



	public int getCount() {
		return subjectList.size();
	}

	public ESubject getItem(int arg0) {
		return subjectList.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public ESubject getCodeLearnsubjItem(int position) {
		return subjectList.get(position);
	}

}