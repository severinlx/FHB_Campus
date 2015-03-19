package org.alex.stundenplan.cloud;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
        String loginCombinationJson ="loginCombinationJson";


        //--------------------cloud connection------------------------------

        if(combinationEndpoint == null) { // Only do this once
            CombinationEndpoint.Builder builder = new CombinationEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://fhb-backend.appspot.com/_ah/api/");


            builder.setApplicationName("FHB");
            combinationEndpoint  = builder.build();
        }

        //----------------------main method:--------------------------------


            try {
                String season = LoginActivity.getActualSeason();
                String json = combinationEndpoint.getCombination(season).execute().getFachs();
                System.out.println("json "+json);
                JSONParser jsonParser = new JSONParser();

                fachs = (JSONObject) jsonParser.parse(json);

                editor.putString(loginCombinationJson, json);
                editor.putString("season", season);

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