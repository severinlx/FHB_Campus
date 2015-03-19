package org.alex.stundenplan.cloud;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.alex.stundenplan.backend.myApi.MyApi;
import org.alex.stundenplan.backend.parserEndpoint.ParserEndpoint;
import org.alex.stundenplan.backend.parserEndpoint.model.ESubject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Stundenplan_Endpoint_Task extends AsyncTask<String, Void, List<ESubject>> {






    protected List<ESubject> doInBackground(String... params) {

        ParserEndpoint parser=null;

        List<ESubject> subjectList = new ArrayList<>();


      String  url = params[0];


        //--------------------cloud connection------------------------------

        if(parser == null) { // Only do this once
            ParserEndpoint.Builder builder = new ParserEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://fhb-backend.appspot.com/_ah/api/");
             builder.setApplicationName("FHB");
            parser  = builder.build();
        }

        //----------------------main method:--------------------------------

        try {

            subjectList = parser.getList(url).execute().getItems();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return subjectList;
    }




}