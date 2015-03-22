package org.alex.stundenplan.mensa;
import java.text.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.alex.stundenplan.helpClasses.Day;
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
	
	static JSONObject jsonChild =null;
	static JSONArray json = null ;
	static JSONArray mealsJson = null;
	static String meal;
	
	public static ArrayList<MensaDay> parseMeals() {


        MensaDay mensaToday = new MensaDay();
        MensaDay mensaTommorow = new MensaDay();

        ArrayList<MensaDay> mensaDayList = new ArrayList<MensaDay>();

	    String	url = "https://mobile-quality-research.org/services/meals/";

        String input = readJSON(url);

        try {

            json = new JSONArray(input);

            //-----------      today meals:

			jsonChild = (JSONObject) json.get(0);

            //get meals Json Array

            mealsJson = jsonChild.getJSONArray("meals");

            //set the Date:
     try {
    mensaToday.date = Day.fineDate((String) jsonChild.get("date"));
     } catch (JSONException e) {
                mensaToday.date = " ";
            }

            //set Meals:

			for (int j = 0; j < mealsJson.length(); j++) {

				meal= Html.fromHtml((String) mealsJson.get(j)).toString();
                mensaToday.meals.add(meal);

			}

			
		    //------------      tommorow meals:

            jsonChild =(JSONObject) json.get(1);

            //set the Date

            try {
                mensaTommorow.date = Day.fineDate((String) jsonChild.get("date"));
            } catch (JSONException e) {
                mensaTommorow.date = " ";
            }


            //set Meals:

            mealsJson = jsonChild.getJSONArray("meals");
			for (int j = 0; j < mealsJson.length(); j++) {

                meal= Html.fromHtml((String) mealsJson.get(j)).toString();

                mensaTommorow.meals.add(meal);

			}
            } catch (JSONException e) {
            e.printStackTrace();
        }
        mensaDayList.add(mensaToday);
        mensaDayList.add(mensaTommorow);

        return mensaDayList;
		
	}

	public static String readJSON(String url) {

	    StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    client = MyHttpClient.getNewHttpClient();

	    HttpGet httpGet = new HttpGet(url);

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

	      }

	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }


        return builder.toString();
	  }

}
