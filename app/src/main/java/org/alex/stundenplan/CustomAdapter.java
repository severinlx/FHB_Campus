package org.alex.stundenplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import StundenPlanOnlineParser.simpleParser;



//Adapter==============================Adapter=====================================
//@a Class to set Adapter with List of Subject and indicating view for diferent components of a subject. The subject list is retrieved in Constructor from parse class
public class CustomAdapter extends BaseAdapter {
	List<Subject> subjectList = new ArrayList();
	// protected Plan plan;
	protected String fileName;
	protected Context context;

	// -------------------------------Constructor----------------------------------;
	public CustomAdapter(Context context, Integer[] preferences)
			throws Exception {
		super();
		this.context = context;
		// get list in Background:
		try {
			subjectList = simpleParser.getList(context, preferences);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// --------------------------------------Adapter Get View--------------------------------
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arg1 = inflater.inflate(R.layout.listitem, arg2, false);
		}
		// get Subject from list:
		Subject subjItem = subjectList.get(arg0);
		// TextViews set layout:
		TextView dayView = (TextView) arg1.findViewById(R.id.day);
		TextView timeView = (TextView) arg1.findViewById(R.id.time);
		TextView subjView = (TextView) arg1.findViewById(R.id.subj);
		TextView roomView = (TextView) arg1.findViewById(R.id.room);

		// show day only in front of 1 lesson
		if (subjItem.firstLesson == true) {
			dayView.setText(subjItem.day);
			dayView.setVisibility(View.VISIBLE);
		} else {
			dayView.setText("");
			dayView.setVisibility(View.GONE);
		}
		// show time, subj and room:
		timeView.setText(subjItem.time);
		subjView.setText(subjItem.subj);
		roomView.setText(subjItem.room);
		return arg1;
	}

    //___________________________________________________________________________________________

	// ----------------------------------------get and Set-----------------------------
	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> List) {
		// this.subjectList.clear();
		subjectList = List;
	}

	public int getCount() {
		return subjectList.size();
	}

	public Subject getItem(int arg0) {
		return subjectList.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public Subject getCodeLearnsubjItem(int position) {
		return subjectList.get(position);
	}

}