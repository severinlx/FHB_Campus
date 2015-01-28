package StundenPlanOnlineParser;

import org.alex.stundenplan.Subject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//a class to parse the html from a given URL to a StringArray 
//with infos about subjects, time, day and daue
//@author Alex Severin
import java.util.Scanner;

public class URLtoStringParser {
	public static Document createDoc(String Fach, String Semester ) {
		return null;
		
	}
	public static List<Subject> tableParser(String url) throws IOException{
		 
		//auxiliar variables:
		int j=1;
		//variables to store info:
		String subj="initialise", time;
		Integer day=0,  iTime, dauer=0;
		String room="";
		String subjShort="";
		String roomTxt = " ";
		String StartEnd;
		//array to store infomation about till what a clock do we have classes in this day, dayEnd[0] = Monday, dayEnd[5]=Saturday
		int[] dayEnd = {8,8,8,8,8,8};
		//StringArray to store info variables
		String[][][] table = new String[6][24][4];
		List<Subject> subjList =  new ArrayList<Subject>();
		//Document that connect to the url and give the XMLDoc
		//url="http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-1.sem_2.gr.html";
		// 
		 
		 
		Document doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
		//String str = Download.getWebpage(url);
		//Document doc = Jsoup.parse(str);
	     
				//Jsoup.connect(url).get();
		//select all rows(TR)
		Elements TR = doc.select("tr");
		//iterate each row and each cell from the row:		
		for(Element eachTR: TR){
			 
			//skip first TR (its the Tr with Mo, Th, We...
			if(j==1){j++; continue;} 
			//select headcell, containing the time:
			Element th = eachTR.select("th").first();
			time=th.text();
			iTime=Integer.parseInt(time.substring(0, 2));
			//select all cells from row:
			Elements td = eachTR.select("td");
		    //take information for each TD, to know subj, dauer, day:=======================================================
			for( Element eachTD: td){
				 
			    //subj and dauer:-------------------------
				subj=eachTD.text();
				//if empty cell:
				if(subj.equals("\u00a0")){
					dauer=1;
					subj="no";
				}
				//if cell with lesson:
				else {
					Attributes at = eachTD.attributes();
					if(at.get("rowspan").equals(""))dauer =1;
					else dauer = Integer.parseInt(at.get("rowspan"));
					 //subj Alone:
	 				 Elements a = eachTD.select("a");
	 				 if(a.size()!=0){
	 				 int howManyN=0;
	 				 for( Element oneA: a){
	 					
	 				 if(howManyN==0){
	 					subjShort = oneA.text();
	 					howManyN=1;
	 				 }
	 				 else{
	 					subjShort = subjShort+", "+oneA.text();
	 					howManyN=howManyN+2;
	 				 }
	 				 
	 				 // 
	 				 
	 				
	 				 //room, if there is one:
	 				 if(roomTxt.length()>1)
	                 room = getRoom(roomTxt);
	 				 }
	 				roomTxt = subj.substring(subjShort.length());
	 				// 
	 				String firstLetter=roomTxt.substring(0, 1);
	 				if(firstLetter.equals(",")||firstLetter.equals(" ")){
	 					roomTxt = roomTxt.substring(1, roomTxt.length());
	 				}
	 				firstLetter=roomTxt.substring(0, 1);
	 				if(firstLetter.equals(",")||firstLetter.equals(" ")){
	 					roomTxt = roomTxt.substring(1, roomTxt.length());
	 				}
	 				firstLetter=roomTxt.substring(0, 1);
	 				if(firstLetter.equals(",")||firstLetter.equals(" ")){
	 					roomTxt = roomTxt.substring(1, roomTxt.length());
	 				}
	 				 howManyN=0;
				 }
	 			else{
						subjShort =subj;
						roomTxt="  ";
					}
	 				// 
	 				//  
				}
				
			//count day:---------------------------------
				for (int dayNumber = 0; dayNumber < dayEnd.length; dayNumber++) {
                     if (iTime==dayEnd[dayNumber]){
                        //day:
						day = dayNumber;
						//add the dauer of the lesson to this day
						dayEnd[dayNumber]=dayEnd[dayNumber]+dauer;
						break;
					}
				}
		//now we have all infos======================================================================	
		//add the subjects and dauer in the table, the info about day and time we'll take from index
				//StartEnd
				 
				Integer End = iTime+dauer;
				StartEnd =iTime.toString()+" - "+End.toString();
				//set array:
				if(!subj.equals("no")){
				table[day][iTime][0]=subjShort;
				table[day][iTime][1]=dauer.toString();
				table[day][iTime][2]=roomTxt;
				table[day][iTime][3]=StartEnd;
				/*Subject subject = new Subject(Integer.toString(day), subjShort, StartEnd, roomTxt,true );
				subjList.add(subject);*/
				}
				subjShort="";
			}
			    
		}
		//toString(table);
		subjList = toList(table);
             return subjList;
	}
	public static List<Subject> toList(String[][][] table){
		List<Subject> subjList =  new ArrayList<Subject>();
		boolean firstLesson;
		int day =0;
		String dayString =" ";
		for(String[][]days:table ){
			firstLesson = true;
			if(day==0) dayString = "Montag";
			if(day==1) dayString = "Dienstag";
			if(day==2) dayString = "Mittwoch";
			if(day==3) dayString = "Donnerstag";
			if(day==4) dayString = "Freitag";
			if(day==5) dayString = "Samstag";
			if(day==6) dayString = "Sontag";
			for(String[]subjects:days){
				if(subjects[0]!=null){
				Subject subject = new Subject(dayString, subjects[0], subjects[3], subjects[2], firstLesson);
				subjList.add(subject);
				firstLesson=false;
				}
			}
			day++;
		}
		return subjList;
	}
	
	
public static String getRoom(String input){
	/*Scanner in = new Scanner(txt).useDelimiter("[^0-9]+");
	int integer = in.nextInt();*/
	String Number = input.replaceAll(".*?(\\d+).*", "$1");
	//if(Number.length()>3) Number = parseRoomString(Number);
	return Number;
}

private static  String parseRoomString(String number) {
	// TODO Auto-generated method stub
	// 
	String fin = null;
	String temp;
	for (int i = 0, j=2; j < number.length(); i=i+3,j=j+3) {
			temp = number.substring(i, j);
			temp =temp.concat(",");
		    fin = fin.concat(temp);
	}
	// 
	return fin;
}

public static void toString(String[][][] table){
	for (String[][] strings : table) {
		// 
		for (String[] strings2 : strings) {
			for (String string : strings2) {
				//if(string!=null)
				// 
				
			}
		}
	}
}
		
	
	
    public static void main(String[] args) throws Exception {

        String urlTest = "http://informatik.fh-brandenburg.de/Stundenplan/ss14/plan/bwlm-2.sem_1.gr.html";
    	
    	tableParser(urlTest);
    }
}


