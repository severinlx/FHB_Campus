package org.alex.stundenplan.stundenplan;

import android.content.Context;
import android.content.SharedPreferences;

import org.alex.stundenplan.helpClasses.CacheManager;
import org.alex.stundenplan.backend.parserEndpoint.model.ESubject;
import org.alex.stundenplan.cloud.Stundenplan_Endpoint_Task;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by alexandru on 11.03.15.
 */



public class SubjectsList {
    java.util.List<ESubject> subjectList = new ArrayList<>();
    Context context;
    String url;
    CacheManager cacheManager;


    //construct call main method to achieve list
    public SubjectsList(Context context) {

        //to do: if cache get list from cache if not get it online check cache

        this.context= context;
        cacheManager = new CacheManager(context);
        //url="Stundenplan/ws1415/plan/acs-3.html";

        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginNew", Context.MODE_PRIVATE);
        this.url = sharedPreferences.getString("URL", " ");

        achiveList();
    }

    //main method to achieve list.   It verify cache or cloud.

    public void achiveList(){

        //cacheManager.clear();
        if(cacheManager.isCache()){

            subjectList = cacheManager.readCache();
       }
        else {
            try {

                subjectList = new Stundenplan_Endpoint_Task().execute(url).get();
                cacheManager.writeCache(subjectList);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
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