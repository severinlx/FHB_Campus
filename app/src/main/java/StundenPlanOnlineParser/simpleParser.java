package StundenPlanOnlineParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.alex.stundenplan.Subject;
import android.content.Context;
public class simpleParser {
	static List<Subject> subjectList;
	static InputStream is;
	static String url;
	
  public static List<Subject> getList(Context context, Integer[] preferences) throws Exception{
	  Integer selectedFach = preferences[0];
	  Integer selectedSemester=preferences[1]; 
	  Integer selectedGroup = preferences[2];
	  
	 //url = URLFinder.urlFind(context, 1,1,1/*fach, semester, group*/);
	 url = url2.findUrl(context, selectedFach,selectedSemester,selectedGroup/*fach, semester, group*/);
	//url="http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-1.sem_2.gr.html";
		
	 //Document doc = StringtoXMLParser.entryToXML(selectedFach, selectedSemester, selectedGroup, url);
	 //Subject sub = new Subject("mon","2-3","example","202",true);
	 List<Subject> subjectList = new ArrayList(); 
	 // subjectList= DOCtoArray.parse(doc);
	 subjectList = URLtoStringParser.tableParser(url);
	  
	 return subjectList;
	  
  }
  

}
