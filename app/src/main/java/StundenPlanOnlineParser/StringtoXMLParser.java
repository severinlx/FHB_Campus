/*package StundenPlanOnlineParser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

//a class to generate XML file with Stunden Plan from a StringArray obtained from URLtoStringParser
//@author Alex Severin

public class StringtoXMLParser {
	static List<String> pref = new ArrayList<String>();
	//read url list and for every entry create an xmlfile with name fach_Sem, using method xmlCreate(string fach, string sem, sting suj., day..)
    //and then write in this xmlFile the table from URL: subj, day, time, dauer..	
	public static Document entryToXML(String fach, String semester, String group, String url) throws IOException {
		//XML doc and root:
		Document groupPlanXML = new Document();
		Element root = new Element("Timetable");
		groupPlanXML.setRootElement(root);		
		//add info element, with fach, semester, group and url to take info:
		Element info = new Element("info");
		root.addContent(info);
		info.addContent(new Element("fach").setText(fach));		
		info.addContent(new Element("semester").setText(semester));		
		info.addContent(new Element("group").setText(group));		
		info.addContent(new Element("url").setText(url));
		//add table Element with days:
		Element Table = new Element("table");
		root.addContent(Table);
		Element mon = new Element("day").setText("mon");
		Table.addContent(mon);
		Element tue = new Element("day").setText("tue");
		Table.addContent(tue);		
		Element wed = new Element("day").setText("wed");
		Table.addContent(wed);
		Element thu = new Element("day").setText("thu");
		Table.addContent(thu);
		Element fri = new Element("day").setText("fri");
		Table.addContent(fri);
		Element sat = new Element("day").setText("sat");
		Table.addContent(sat);
		//get the array with stundenplan from 
		String[][][] table = new String[5][20][4];
		 
		table = URLtoStringParser.tableParser(url);	
		
		//iterate throw array and add entries to doc xml
		for (int i = 0; i < table.length; i++) {
			for (Integer j = 0; j < table[i].length; j++) {
				if(table[i][j][0]!=null){
				    Element time = new Element("time");
				    time.setText(j.toString());
					Element subj = new Element("subj");	
					Element dauer = new Element("dauer");
					Element room = new Element("room");
					Element startEnd = new Element("startEnd");
					subj.setText(table[i][j][0]);
					//dauer.setText(table[i][j][1]);
					room.setText(table[i][j][2]);
					startEnd.setText(table[i][j][3]);
					subj.addContent(room);
					startEnd.addContent(subj);
					//time.addContent(dauer);
					//time.addContent(startEnd);
					if(i==0) mon.addContent(startEnd); 
					if(i==1) tue.addContent(startEnd);
					if(i==2) wed.addContent(startEnd);
					if(i==3) thu.addContent(startEnd);
					if(i==4) fri.addContent(startEnd);
					if(i==5) sat.addContent(startEnd);
				}
			}
		}
		//write the items to file:
		//writeFile(groupPlanXML, fach, semester, group);	
		return groupPlanXML;
		
	}
	

	private static void writeFile(Document doc, String fach, String sem, String group) throws IOException {
		String path= "/home/alexandru/documents/uniapp/XMLPlan/xml1000/";
		String prefName = "\""+"f"+fach+"s"+sem+"g"+group+".xml"+"\",";
		String name = "f"+fach+"s"+sem+"g"+group+".xml";
		//add name for to check the sem - fach - group combination:
		pref.add(prefName);
		//preference[Integer.parseInt(fach)][Integer.parseInt(sem)][Integer.parseInt(sem)]=1;
		//String name = "XML"+nameFach+sem+group;
		try {
			XMLOutputter xmlOutput = new XMLOutputter();
			// display nice nice
			//xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(path+name));
			 
		} catch (IOException io) {
			 
		}
	}
}
*/