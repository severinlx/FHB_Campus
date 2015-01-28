package StundenPlanOnlineParser;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.AssetManager;
public class URLFinder {
	public void obtainXML(){
		
	}
	
	public static String urlFind(Context context, int fach, int semester, int group)throws Exception{
		AssetManager assetManager = context.getAssets();
	    InputStream is = assetManager.open("ws1415.xml");
		//String data ="<?xmlversion= \"1.0 \"encoding= \"utf-8 \"?><resources><fach>Betriebswirtschaftslehre(Bachelor)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-1.sem_1.gr.html</url></group><group>2<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-1.sem_2.gr.html</url></group><group>3<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-1.sem_3.gr.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-3.sem_1.gr.html</url></group><group>2<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-3.sem_2.gr.html</url></group><group>3<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-3.sem_3.gr.html</url></group></semester><semester>5<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-5.sem_1.gr.html</url></group></semester></fach><fach>Betriebswirtschaftslehre(Master)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlm-1.sem_1.gr.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlm-3.sem_1.gr.html</url></group></semester></fach><fach>Wirtschaftsinformatik(Bachelor)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-1.sem_1.gr.html</url></group><group>2<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-1.sem_2.gr.html</url></group><group>3<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-1.sem_3.gr.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-3.sem_1.gr.html</url></group><group>2<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-3.sem_2.gr.html</url></group><group>3<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-3.sem_3.gr.html</url></group></semester><semester>5<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-5.sem_1.gr.html</url></group><group>2<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-5.sem_2.gr.html</url></group><group>3<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wib-5.sem_3.gr.html</url></group></semester><fach>Wirtschaftsinformatik(Master)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wim-1.sem_1.gr.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/wim-3.sem_1.gr.html</url></group></semester></fach><fach>SecurityManagement(Master)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/sm-1.sem.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/sm-3.sem.html</url></group></semester></fach><fach>Technologie-undInnovationsmanagement(Master)<semester>2<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/tim-2.sem.html</url></group></semester></fach><fach>AppliedComputerScience(Bachelor)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/acs-1.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/acs-3.html</url></group></semester></fach><fach>DigitaleMedien(Master)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/dmm-1.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/dmm-3.html</url></group></semester></fach><fach>Informatik(Bachelor)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/infb-1-1.html</url></group><group>2<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/infb-1-2.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/infb-3-1.html</url></group><group>2<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/infb-3-2.html</url></group></semester><semester>5<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/infb-5-1.html</url></group></semester></fach><fach>Informatik(Master)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/infm-1.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/infm-3.html</url></group></semester></fach><fach>Medizininformatik(Bachelor)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/medi-1-1.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/medi-3-1.html</url></group></semester></fach><fach>ComputerAidedRobustEngineering(Master)<semester>2<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/care-2.html</url></group></semester></fach><fach>EnergieeffizienzTechnischerSysteme(Master)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/enef-1.html</url></group></semester><semester>2<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/enef-2.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/enef-3.html</url></group></semester></fach><fach>IT-Elektronik(Bachelor)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bitel-1.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bitel-3.html</url></group></semester><semester>5<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bitel-5.html</url></group></semester><semester>7<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bitel-7.html</url></group></semester></fach><fach>Maschinenbau(Bachelor)AM-AllgemeinerMaschinenbauEUT-Energie-u.Umwelttechnik<semester>1<group>1<subfach>AM/EUT</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-1-1.html</url></group><group>2<subfach>AM/EUT</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-1-2.html</url></group><group>3<subfach>AM/EUT</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-1-3.html</url></group><group>4<subfach>AM/EUT</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-1-4.html</url></group></semester><semester>3<group>1<subfach>AM</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-3-1-am.html</url></group><group>2<subfach>AM</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-3-2-am.html</url></group><group>3<subfach>EUT</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-3-eut.html</url></group></semester><semester>5<group>1<subfach>AM</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-5-1-am.html</url></group><group>2<subfach>AM</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-5-2-am.html</url></group><group>3<subfach>EUT</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-5-eut.html</url></group></semester><semester>7<group>1<subfach>AM</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-7-1-am.html</url></group><group>2<subfach>AM</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-7-2-am.html</url></group><group>3<subfach>EUT</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/mb-7-eut.html</url></group></semester></fach><fach>MechatronikSiemens-Akademie(Bachelor)<semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtta-3.html</url></group></semester><semester>5<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtta-5.html</url></group></semester><semester>7<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtta-7.html</url></group></semester></fach><fach>Mechatronik/Automatisierungssysteme(Bachelor)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-1-1.html</url></group><group>2<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-1-2.html</url></group><group>3<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-1-3.html</url></group><group>4<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-1-4.html</url></group></semester><semester>3<group>1<subfach>Automatisierungstechnik</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-3-at.html</url></group><group>2<subfach>Mechatronik</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-3-mt.html</url></group><group>3<subfach>Siemens-Technik-Akademie</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtta-3.html</url></group></semester><semester>5<group>1<subfach>Automatisierungstechnik</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-5-at.html</url></group><group>2<subfach>Gebäudesystemtechnik</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-5-gst.html</url></group><group>3<subfach>Mechatronik</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-5-mt.html</url></group></semester><semester>7<group>1<subfach>Automatisierungstechnik</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-7-at.html</url></group><group>2<subfach>Gebäudesystemtechnik</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-7-gst.html</url></group><group>3<subfach>Mechatronik</subfach><url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bmtat-7-mt.html</url></group></semester></fach><fach>MikrosystemtechnikundOptischeTechnologien(Bachelor)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/miopt-1.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/miopt-3.html</url></group></semester><semester>5<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/miopt-5.html</url></group></semester><semester>7<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/miopt-7.html</url></group></semester></fach><fach>Photonics(Master)<semester>1<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/pht-1.html</url></group></semester><semester>3<group>1<url>http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/pht-3.html</url></group></semester></fach></resources>";
		//InputStream is = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        int fachNumber=0, semesterNumber=0, groupNumber=0;
        xpp.setInput(is, null);
        int eventType = xpp.getEventType();
         
		 
        String url = "http://informatik.fh-brandenburg.de/Stundenplan/ws1415/plan/bwlb-1.sem_2.gr.html";
        
        while (eventType != XmlPullParser.END_DOCUMENT) {
          //if(eventType == XmlPullParser.TEXT) {
        	  // 
        	  //find fach (depth 1)
        	  if(xpp.getDepth()==2){
				fachNumber++;
				 
				 
        		  if(fachNumber==fach){
        			   
        			  //find semester
        			  if(xpp.getDepth()==3){
        				   
        				   
        				  semesterNumber++;
        				  if(semesterNumber==semester){
        					   
        					//find group:
        				       if(xpp.getDepth()==4){
        				    	    
        				    	    
        					      groupNumber++;
        					      if(groupNumber==group){
        					    	   
        					      //find url:
        						  if(xpp.getDepth()==5){
        							   
        							   
        							url =  xpp.getText();
        						     }
        						  }
        					  }
        				  }
        			  }
        		  }
        	  }
         //}
       }
        // 
		return url;
	}
	/*public static void main(String[] args) throws Exception{
		urlFind(1,1,1);
	}*/
}
