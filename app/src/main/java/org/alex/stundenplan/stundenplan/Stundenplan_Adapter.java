package org.alex.stundenplan.stundenplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.alex.stundenplan.R;
import org.alex.stundenplan.backend.parserEndpoint.ParserEndpoint;
import org.alex.stundenplan.backend.parserEndpoint.model.ESubject;

import java.util.ArrayList;
import java.util.List;



//Adapter==============================Adapter=====================================
//@a Class to set Adapter with SubjectsList of Subject and indicating view for diferent components of a subject. The subject list is retrieved in Constructor from parse class
public class Stundenplan_Adapter extends BaseAdapter {

	protected String fileName;
	protected Context context;
    private static ParserEndpoint parser=null;
    String url="Stundenplan/ws1415/plan/acs-3.html";
    List<ESubject> subjectList = new ArrayList<>();
    SubjectsList list;

	// -------------------------------Constructor get the list-------------
	public Stundenplan_Adapter(Context context){
        this.context = context;

            list = new SubjectsList(context);
            subjectList = list.getSubjectList();


    }

	// --------------------------------------Design: --------------------------
	public View getView(int arg0, View arg1, ViewGroup arg2) {


        if (arg1 == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arg1 = inflater.inflate(R.layout.stundenplan_list_item, arg2, false);
		}
		// get Subject from list:
		ESubject subjItem = subjectList.get(arg0);
		// TextViews set layout:
		TextView dayView = (TextView) arg1.findViewById(R.id.day);
		TextView timeView = (TextView) arg1.findViewById(R.id.time);
		TextView subjView = (TextView) arg1.findViewById(R.id.subj);
		TextView roomView = (TextView) arg1.findViewById(R.id.room);


		// show day only in front of 1 lesson
		if (subjItem.getFirstLesson() == true) {
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

	// ----------------------------------------get and Set---------------------
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