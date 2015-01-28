package StundenPlanOnlineParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import android.content.Context;
import android.content.res.AssetManager;

public class url2 {
	public static String findUrl(Context context, Integer selectedFach, Integer selectedSemester, Integer selectedGroup) throws JDOMException, IOException{
	AssetManager assetManager = context.getAssets();
	InputStream is=null;
	 
    try {
		is = assetManager.open("ws1415.xml");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    int fachNr=0;
    Document doc = new Document();
    SAXBuilder sax = new SAXBuilder();
    doc = sax.build(is);
    Element root = doc.getRootElement();
    List<Element> fachs =  root.getChildren();
    for(Element fachEl: fachs){
    	 
    	//compare the number of fach from urlTable with number from preferences( see the table in login class with fachs)
    	if(selectedFach==fachNr){
    		 
    		List <Element> semesters = fachEl.getChildren();
    		for(Element semesterEl: semesters){
    			if(semesterEl.getText().equals(Integer.toString(selectedSemester))){
    				List <Element> groups = semesterEl.getChildren();
    				for(Element groupEl: groups){
    					if(groupEl.getText().equals(Integer.toString(selectedGroup))){
    						String url =groupEl.getChildText("url");
    						return url;
    					}
    				}
    			}
    		}
    	}
    	fachNr++;
    }
    return null;
	}
}
