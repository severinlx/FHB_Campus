package org.alex.stundenplan;

import android.content.Context;
import android.content.SharedPreferences;

import org.alex.stundenplan.backend.parserEndpoint.model.ESubject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by alexandru on 11.03.15.
 */







public class List {
    java.util.List<ESubject> subjectList = new ArrayList<>();
    Context context;
    String url;
    boolean isCache;
    CacheManager cacheManager;


    //construct call main method to achieve list
    public List(Context context) {

        //to do: if cache get list from cache if not get it online check cache

        this.context= context;
        cacheManager = new CacheManager(context);
        //url="Stundenplan/ws1415/plan/acs-3.html";
        //to do:
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginNew", Context.MODE_PRIVATE);
        this.url = sharedPreferences.getString("URL", " ");


        System.out.println("url "+url);

        achiveList();
    }

    //main method to achieve list.   It verify cache or cloud.

    public void achiveList(){

        //cacheManager.clear();
        if(cacheManager.isCache()){
            subjectList = cacheManager.readCache();
            System.out.println("from cache");
       }
        else {
            try {
                subjectList = new EndpointsAsyncTask().execute(url).get();
                cacheManager.writeCache(subjectList);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    //geters and setters
    //getters and setters
    public java.util.List<ESubject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(java.util.List<ESubject> subjectList) {
        this.subjectList = subjectList;
    }



}