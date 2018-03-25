package org.alex.stundenplan.cloud;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.alex.stundenplan.LoginActivity;
import org.alex.stundenplan.backend.combinationEndpoint.CombinationEndpoint;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;


public class LoginCombination_Endpoint_Task extends AsyncTask<Context, Void, JSONObject> {

    private static CombinationEndpoint combinationEndpoint = null;
    JSONObject fachs = null;






    protected JSONObject doInBackground(Context... params) {

        Context context = params[0];
        SharedPreferences sharedPref = context.getSharedPreferences("LoginNew", Context.MODE_PRIVATE);
        //editor to share preferencess:
        SharedPreferences.Editor editor = sharedPref.edit();
        String loginCombinationJson ="loginCombination";


        //--------------------cloud connection------------------------------

        if(combinationEndpoint == null) { // Only do this once
            CombinationEndpoint.Builder builder = new CombinationEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://fhb-backend.appspot.com/_ah/api/");


            builder.setApplicationName("FHB");
            combinationEndpoint  = builder.build();
        }

        //----------------------main method:--------------------------------


            try {
                String season = 
                        LoginActivity.getActualSeason();
                Log.d("what season it", season);

                //the call to provide from clod all the data

                String json = combinationEndpoint.getCombination(season).execute().getFachs();

                Log.d("json from endpoint", json);

                JSONParser jsonParser = new JSONParser();

                fachs = (JSONObject) jsonParser.parse(json);

                editor.putString(loginCombinationJson, json);
                editor.putString("season", season);
                editor.commit();

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
           } catch (java.text.ParseException e) {
               e.printStackTrace();
            }


        return fachs;
    }


}