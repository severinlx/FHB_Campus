package org.alex.stundenplan.backend.allURL.linksParser;



;

import org.alex.stundenplan.backend.helpclasses.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.alex.stundenplan.backend.allURL.OneLink;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


//a class to parse the html from a given URL to Fach-Sem-Group Combination and List of all Group Links
//@author Alex Severin

public class fachURL {


	public static Pair<JSONObject , ArrayList<OneLink>> linkParser(String link, String fach) {


        //Json
        JSONObject semesters = new JSONObject();
        JSONObject groups  =  new JSONObject();

        ArrayList<OneLink> linksList = new ArrayList<>();
		String group= "0";
		String url = "0";
		//allURL allLinks = new allURL();
		Integer semester = 0;
		OneLink oneLink = new OneLink("0", semester, group, url);

		// Document that connect to the url and give the XMLDoc
		Document doc = null;
		try {
			doc = Jsoup.parse(new URL(link).openStream(), "ISO-8859-1", url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!doc.hasText()) {
			//System.out.println("error!!!! kein Text!!!");
		}

		// method 1
		Elements td = doc.select("td");
		for (Element eachTD : td) {
			
			//--------------- sort out strong elements = names from faecher---------
			Elements strong = null;
			if (!eachTD.children().isEmpty()) {
				strong = eachTD.child(0).getElementsByTag("strong");
				if (!strong.isEmpty()) {
					continue;
				}
			}
		    //-----------------------------------------------------------------------
            
			//-------------------------find only Group and Semester  TD------
			
			if (eachTD.hasText() && !eachTD.text().equals("\u00a0")) {
				
				// for each td now do an url Object:
				
				
				//@@@@@@@@@@@@@@@@@    Semester element 
				
				if (eachTD.text().contains("Semester")) {

                    //Json -
                    //store goups from last semester, at the end, out of the loop, the last one
                    if(semester!=0) semesters.put(semester, groups);
                    // each time reach the semester, make new groups to store groups only for this sem:
                    groups  =  new JSONObject();
					
					//will stay at this value till next td of Semester, so can be taked as sem value from group tds
					semester = Integer.parseInt(eachTD.text().substring(0, 1));



					//_____ elements that has no groups:

					if (!eachTD.children().isEmpty()) {
						
						Element a = eachTD.child(0);
						// if semester is in the link, there is no group
						// = we use group=1 by default
						group = "1. Gruppe"; //becouse this format of groups is on the website and so will be the others groups ass well
						// first letter is the number of Semester
						url = a.attr("href");
						
						//make Object:

                       oneLink = new OneLink(fach, semester, group, url);

                        //Json url:
                        groups.put(group, url);



                        //add
                        linksList.add(oneLink);
                        //System.out.println(url);


                    }

					else{//_____ if element have group go to next TD to find out group.
                        // it will bring us to  Group Element

                        continue;
					}
				}

				

				
				else {
                    //_____ elements that has  groups: @@@@@@@@@@@@@@@@@@@   Group Element
                    //firstly the semester td, will be accessed and then  throw continue we will come here


					if(!eachTD.getElementsByTag("a").isEmpty()){
					Element a = eachTD.child(0);
					// if semester is in the link, there is no group
					// = we use group=1 by default
					group = a.text();
					// first letter is the number of Semester
					url = a.attr("href");
					
					//make Object:
                      oneLink = new OneLink(fach, semester, group, url);

					 }

                    //Json url:
                    groups.put(group, url);


                    // TODO add URL to allURL and to database
                    out(oneLink);
                    linksList.add(oneLink);
                    //System.out.println(url);
				}




            }

		}

        semesters.put(semester, groups);

      //  System.out.println(semesters.toJSONString());
        Pair pair = new Pair(semesters, linksList);
        return pair;
	}

	private static void out(OneLink URL) {
		
		/*System.out.println("URL fach: " + URL.fach);
		System.out.println("URL sem: " + URL.sem);
		System.out.println("URL group: " + URL.group);
		System.out.println("URL url: " + URL.url);
		System.out.println("========================================");
*/
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws Exception {
        System.out.println("we run in fachUrl");

		String urlTest = "http://informatik.fh-brandenburg.de/Stundenplan/ss15/liste-winfb.html";

		linkParser(urlTest, "f  ");

	}
}