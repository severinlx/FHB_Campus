package org.alex.stundenplan.backend.allURL.linksParser;



import org.alex.stundenplan.backend.allURL.OneLink;
import org.alex.stundenplan.backend.helpclasses.Pair;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class allLinksParser {

	public static Pair<JSONObject , ArrayList<OneLink>> parse(String mainUrl, String season){

        //-------------------initialise Block:--------------------------

		Document doc = null;
        //OneLink oneLink;
        ArrayList <OneLink> linksList = new ArrayList<>();
        ArrayList <OneLink> fachList = new ArrayList<>();
        String fachUrl;
		String fach;
        JSONObject fachs = new JSONObject();

        //Todo a function to generate season and to look for url

        if(mainUrl==null) mainUrl = "http://freya.fh-brandenburg.de/Stundenplan/ws1415/liste-zu.html";
        if(season== null) season = "ws1415";

		try {
			doc = Jsoup.parse(new URL(mainUrl).openStream(), "ISO-8859-1", mainUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*
		if (!doc.hasText()) {
			System.out.println("error!!!! kein Text!!!");
		}
          */
        //-----------------------Function:-----------------------------

		Elements a = doc.getElementsByTag("a");
		for (Element eachA : a){
			fach = eachA.text();
			fachUrl = eachA.attr("href");
			fachUrl = "http://informatik.fh-brandenburg.de/"+fachUrl;
			System.out.println("////////////////////////////////////////////////");
            System.out.println();
            System.out.println("fach "+fach);
			//System.out.println(fachUrl);

            //=============main function:
            Pair pair = fachURL.linkParser(fachUrl, fach);
			fachList = (ArrayList<OneLink>) pair.getSecond();

            for(OneLink oneLink: fachList) {
                oneLink.setSeason(season);

                //add to the list:
                linksList.add(oneLink);
            }

            //json
            JSONObject semesters = (JSONObject) pair.getFirst();
            fachs.put(fach, semesters);
            System.out.println(fachs);
            System.out.println();

		}
        //test:
        System.out.println(fachs.toJSONString());

        Pair pair = new Pair(fachs, linksList);
        return pair;
	}//-------------------------------end----------------------------------
	
	
	
	public static void main(String[] args) throws Exception {

		String urlTest = "http://informatik.fh-brandenburg.de/Stundenplan/ss15/liste-zu.html";

		parse(urlTest, "ss15");
	}
}
