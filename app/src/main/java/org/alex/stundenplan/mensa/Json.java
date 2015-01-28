package org.alex.stundenplan.mensa;
import java.text.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.alex.stundenplan.Day;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;

public class Json {
	
	static JSONObject mealsJO=null;
	static JSONArray mealsJA = null ;
	static String date=null;
	static JSONArray mealsA = null;
	static String meal;
	
	public static ArrayList<MensaDay> getJson() throws JSONException, ParseException{
        //ArrayList<String> meals=new ArrayList<String>();
        MensaDay mensaToDay = new MensaDay();
        MensaDay mensaTommorow = new MensaDay();
        ArrayList<MensaDay> mensaDayList = new ArrayList<MensaDay>();
	    String	url = "https://mobile-quality-research.org/services/meals/v2";
        System.out.println("in getJson");
        String input = " ";
                input=readJSON(url);
        try {
            mealsJA=new JSONArray(input);

            //todaY:
			mealsJO=(JSONObject) mealsJA.get(0);
			//add date
			date=getToday();
            //get meals Json Array
            mealsA = mealsJO.getJSONArray("meals");
            //todo mensaday
            mensaToDay.date = date;
			for (int j = 0; j < mealsA.length(); j++) {
				meal= Html.fromHtml((String) mealsA.get(j)).toString();
             //todo mensaday
             mensaToDay.meals.add(meal);
			}
			
		    //extract json for tommorow:
            mealsJO=null;
            mealsJO=(JSONObject) mealsJA.get(1);
            //add date
			date=getTommorow();
            //todo mensaday
            mensaTommorow.date=date;
            //add meals:
            mealsA = mealsJO.getJSONArray("meals");
			for (int j = 0; j < mealsA.length(); j++) {
                System.out.println("in mealsA iteration Nr: "+j);
                meal= Html.fromHtml((String) mealsA.get(j)).toString();
                //todo mensaday
                mensaTommorow.meals.add(meal);
			}
			   } catch (Exception e) {
		    	 
		      e.printStackTrace();
		    }

        mensaDayList.add(mensaToDay);
        mensaDayList.add(mensaTommorow);
        System.out.println("listSize"+mensaDayList.size());
        System.out.println(mensaDayList.get(0).date +"    "+mensaDayList.get(0).meals.size() );
        return mensaDayList;
		
	}
	public static String getToday(){
		//todaY:
		try {
		mealsJO=(JSONObject) mealsJA.get(0);
		//get date
		date=(String) mealsJO.get("date");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		date=Day.fineDate(date);
		return date;
	}
	public static String getTommorow(){
		mealsJO=null;
		try {
		mealsJO=(JSONObject) mealsJA.get(1);
		date=(String) mealsJO.get("date");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		date=Day.fineDate(date);
		return date;
	}
	public static String readJSON(String url) {
	    StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    client = MyHttpClient.getNewHttpClient();
	    HttpGet httpGet = new HttpGet("https://mobile-quality-research.org/services/meals/v2");
	    try {
	      HttpResponse response = client.execute(httpGet);
	      StatusLine statusLine = response.getStatusLine();
	      int statusCode = statusLine.getStatusCode();
	      if (statusCode == 200) {
	        HttpEntity entity = response.getEntity();
	        InputStream content = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	      } else { 
	      }
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
        System.out.println("in readJson string at the end  "+builder.toString().length()+"    "+builder.toString());
        return builder.toString();
	  }

}
