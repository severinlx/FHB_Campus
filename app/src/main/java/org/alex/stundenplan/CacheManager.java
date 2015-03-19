package org.alex.stundenplan;

import android.content.Context;

import org.alex.stundenplan.backend.parserEndpoint.model.ESubject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;


/**
 * Created by alexandru on 12.03.15.
 */






public class CacheManager {
    protected Context context;
    //protected java.util.List<ESubject> subjectList = new ArrayList<>();
    protected String FILENAME = "SubjListCache";
    protected File file;
    int time;
    Calendar c;
    JSONArray jsonArray;

    public CacheManager(Context context) {
        this.context = context;
        file = new File(context.getCacheDir(), FILENAME);
        c = Calendar.getInstance();
        jsonArray = new JSONArray();

    }

    public boolean isCache(){
    return file.exists();
    }

    public boolean clear(){
         return file.delete();
    }

    public boolean isUptoDate(){
        //todo control if this cache is newer than on gae
        return true;
    }

    public boolean writeCache(java.util.List<ESubject> subjectList ){

        time = c.get(Calendar.SECOND);

        //creating Json from List


            JSONArray jsonArray = new JSONArray();
            for (ESubject subject : subjectList) {
                JSONObject jsonObject = new JSONObject();


                jsonObject.put("day", subject.getDay());
                jsonObject.put("subj", subject.getSubj());
                jsonObject.put("time", subject.getTime());
                jsonObject.put("room", subject.getRoom());
                jsonObject.put("firstLesson", subject.getFirstLesson());
                jsonArray.add(jsonObject);

                System.out.println("in for loop ");
                System.out.println("            day in loop         "+jsonObject.get("day"));
            }


        //writing json to file
        FileWriter fileWriter = null;
        try {
             fileWriter = new FileWriter(file);
            //test
            JSONObject testO = (JSONObject) jsonArray.get(1);
            String day = (String) testO.get(0);
            System.out.println("day  "+ day);
            System.out.println("json Array  " + jsonArray.toJSONString());
            fileWriter.write(jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();

    } finally {

            try {
                fileWriter.flush();

            fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

        return true;
    }

    public java.util.List<ESubject> readCache(){

        java.util.List<ESubject>  subjectList = new ArrayList<>();

        JSONParser parser = new JSONParser();



            Object obj = null;
            try {
                obj = parser.parse(new FileReader(file));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            jsonArray= (JSONArray) obj;


            for (int i = 0; i < jsonArray.size() ; i++) {

            ESubject subject = new ESubject();

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                subject.setDay((String)jsonObject.get("day"));
                subject.setSubj((String)jsonObject.get("subj"));
                subject.setTime((String)jsonObject.get("time"));
                subject.setRoom((String)jsonObject.get("room"));
                subject.setFirstLesson((Boolean)jsonObject.get("firstLesson"));
            subjectList.add(subject);
        }

        System.out.println(subjectList.get(0).getRoom()+"            -------------------------------------------------------------------------------------");
        return  subjectList;
    }


    public  String readString() {

        StringBuilder builder = new StringBuilder();

        FileInputStream streamIn = null;
        try {
        streamIn = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(streamIn));
                String line;
        try {
        while ((line = reader.readLine()) != null) {
                builder.append(line);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return builder.toString();
    }
    //getters and setters

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

   /* public List<ESubject> getSubjectList() {
        return subjectList;
    }*/

/*
    public void setSubjectList(List<ESubject> subjectList) {
        this.subjectList = subjectList;
    }
*/

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Calendar getC() {
        return c;
    }

    public void setC(Calendar c) {
        this.c = c;
    }

}
