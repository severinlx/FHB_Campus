package StundenPlanOnlineParser;


import java.util.ArrayList;
import java.util.List;

import org.alex.stundenplan.Subject;
import org.jdom2.Document;
import org.jdom2.Element;

public class DOCtoArray {
	static List<Subject> subjectsList = new ArrayList<Subject>();
	
    public static List<Subject> parse(Document doc)
            throws Exception{
String day, time, subjName, room;
Subject subj;
boolean firstlesson=false;
int i=0;
Element root = doc.getRootElement();
Element timetable = root.getChild("table");
List<Element> daysList =  timetable.getChildren();
for(Element eachDay: daysList){
	day = eachDay.getText();
	List<Element> times = eachDay.getChildren();
	for(Element eachTime:times){
		if(i==0)firstlesson=true;
		else firstlesson =false;
		i++;
		time = eachTime.getText();
		Element subjEl = eachTime.getChild("subj");
		Element roomEl = subjEl.getChild("room");
		subjName = subjEl.getText();
		room = roomEl.getText();
		
		subj = new Subject(day, subjName, time, room, firstlesson);
		subjectsList.add(subj);
	}
	
}
return subjectsList;
}
}