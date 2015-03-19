package org.alex.stundenplan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.alex.stundenplan.AdapterTask;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by alexandru on 17.03.15.
 */
public class LoginNew extends Activity{

    String fachPreference;
    String semesterPreference;
    String groupPreference;
    String urlPreference;
    final String FACH = "fachPreference";
    final String SEMESTER = "semesterPreference";
    final String    GROUP = "groupPreference";
    final String URL ="URL";

    Context context;

    //Preferences in fach, sem, group:
    SharedPreferences sharedPref;
    //editor to share preferencess:
    SharedPreferences.Editor editor;
    private String loginCombinationJson ="loginCombinationJson";


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = this;
        sharedPref = getSharedPreferences("LoginNew", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        checkSeason();//check season and if to old clear the data
        setContentView(R.layout.login);
        fachSpinner();

        System.out.println("sharedpref   "+sharedPref.getString(FACH, "no Fach")+ sharedPref.getString(SEMESTER, "no Sem")+ sharedPref.getString(GROUP, "no group"));
        System.out.println("pref  "+ fachPreference + semesterPreference+groupPreference);





    }

    public String fachSpinner() {

        Spinner selectFach = (Spinner) findViewById(R.id.button1);

        //JSONObject fachsJSON = getComb();
       // String[] fachs = new String[getFachs(fachsJSON).length];
       ArrayList fachs = (ArrayList) getFachs(getComb());
        //Collections.sort(fachs, String.CASE_INSENSITIVE_ORDER);


        final ArrayAdapter<String> fachAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fachs);
        fachAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectFach.setAdapter(fachAdapter);
        selectFach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView adapter, View v, int pos, long lng) {
                //do something here
                System.out.println("selcted fach");
                fachPreference = String.valueOf(adapter.getItemAtPosition(pos).toString());
                System.out.println("fach  "+ fachPreference);
                semesterPreference=null;
                groupPreference = null;
                semSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                 //do something else
            }
        });

        return fachPreference;

    }

    public String semSpinner() {

        Spinner selectSemester = (Spinner) findViewById(R.id.button2);

        List<String> semSet = getSemesters(getComb(), fachPreference);
        String[] semesters = new String[semSet.size()];
        int i =0;
        for(String sem: semSet){
            semesters[i] =sem;
            i++;
        }


        ArrayAdapter<String> semAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, semesters);
        semAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSemester.setAdapter(semAdapter);
        selectSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView adapter, View v, int pos, long lng) {
                //do something here
                semesterPreference = String.valueOf(adapter.getItemAtPosition(pos).toString());
                System.out.println("sem  "+ semesterPreference);
                groupPreference = null;
                groupSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                //do something else
            }
        });

        //groupPreference = groupSpinner();
        return String.valueOf(selectSemester.getSelectedItem());
    }

    public String groupSpinner() {

        Spinner selectGroup = (Spinner) findViewById(R.id.button3);

        JSONObject semestersJSON = (JSONObject) getComb().get(fachPreference);
        final JSONObject groupsJSON = (JSONObject) semestersJSON.get(semesterPreference);
        Set<String> groupSet = groupsJSON.keySet();
        List groupsList = new ArrayList(groupSet);
        Collections.sort(groupsList, String.CASE_INSENSITIVE_ORDER);





        ArrayAdapter<String> semAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, groupsList);
        semAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectGroup.setAdapter(semAdapter);
        selectGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView adapter, View v, int pos, long lng) {
                //do something here
                groupPreference = String.valueOf(adapter.getItemAtPosition(pos).toString());
                System.out.println("GROUP  "+ groupPreference);
                urlPreference =(String)groupsJSON.get(groupPreference);
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                //do something else
            }
        });

        return String.valueOf(selectGroup.getSelectedItem());
    }

    @Override
    public void onBackPressed() {
        if (preferIsSet()) {
            this.finish();
            Intent myIntent = new Intent(this, Plan.class);
            startActivity(myIntent);
        } else {JSONParser jsonParser = null;
            super.onBackPressed();
        }
    }

    public void checkSeason(){
        //check if login Pref aktuell
        String actualSeason = null;
        try {
            actualSeason = getActualSeason();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //clear all Data if not from this semester:
        if (sharedPref.getString("season", "noseason") != actualSeason) {
            editor.clear();
            editor.putString("season", actualSeason);
        }
    }

    public JSONObject getComb(){// program to return the Json for: fach-sem-group possible combinations

        JSONObject fachs = null;
        JSONParser jsonParser = null;

        //check if comb in Pref

        if(sharedPref.contains(loginCombinationJson)) {
            try {
                fachs = (JSONObject) jsonParser.parse(sharedPref.getString(loginCombinationJson, "NO"));
                return fachs;
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }

        //If not, do this:
        //proof connection

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() ) {

            LoginCombinationTask combTask = new LoginCombinationTask();
            try {
                fachs = combTask.execute(context).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Leider keine Internetverbindung... Um die App einzurichten brauchen Sie Internet ", Toast.LENGTH_LONG).show();
        }

        return fachs;
    }

    public java.util.List<String> getFachs(JSONObject fachsJSON){
        final java.util.List<String> fachs = new ArrayList<>();

        for (Iterator iterator = fachsJSON.keySet().iterator(); iterator.hasNext(); ) {
            fachs.add((String) iterator.next());
        }
        Collections.sort(fachs, String.CASE_INSENSITIVE_ORDER);
        return fachs;
    }

    public java.util.List<String> getSemesters(JSONObject fachsJSON, String fachPreference){

       JSONObject semestersJSON = (JSONObject) fachsJSON.get(fachPreference);

        final java.util.List<String> semesters = new ArrayList<>();

        for (Iterator iterator = semestersJSON.keySet().iterator(); iterator.hasNext(); ) {
            semesters.add((String) iterator.next());
        }
        Collections.sort(semesters, String.CASE_INSENSITIVE_ORDER);
        return semesters;
    }

    public Set<String> getGroups(JSONObject fachsJSON, String fachPreference, String SemesterPreference){

        JSONObject semestersJSON = (JSONObject) fachsJSON.get(fachPreference);
        JSONObject groupsJSON = (JSONObject) semestersJSON.get(SemesterPreference);
        Set<String> groups = groupsJSON.keySet();

        return groups;
    }


    public static String getActualSeason() throws ParseException {
        String season;
        System.out.println("in get actual season");

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

        System.out.println("season, date parse "+formatDate.parse("01.03.2015").toString());



        if (date.after(formatDate.parse("01.03.2015")) && date.before(formatDate.parse("01.09.2015"))) {
            season = "ss15";
            return season;
        }
        if (date.after(formatDate.parse("01.09.2015")) && date.before(formatDate.parse("01.03.2016"))) {
            season = "ws1516";
            return season;
        }
        if (date.after(formatDate.parse("01.03.2016")) && date.before(formatDate.parse("01.09.2016"))) {
            season = "ss16";
            return season;
        }
        if (date.after(formatDate.parse("01.09.2016")) && date.before(formatDate.parse("01.03.2017"))) {
            season = "ws1617";
            return season;
        }
        if (date.after(formatDate.parse("01.03.2017")) && date.before(formatDate.parse("01.09.2017"))) {
            season = "ss17";
            return season;
        }
        if (date.after(formatDate.parse("01.09.2017")) && date.before(formatDate.parse("01.03.2018"))) {
            season = "ws1718";
            return season;
        }
        if (date.after(formatDate.parse("01.03.2018")) && date.before(formatDate.parse("01.09.2018"))) {
            season = "ss18";
            return season;
        }
        if (date.after(formatDate.parse("01.09.2018")) && date.before(formatDate.parse("01.03.2019"))) {
            season = "ws1819";
            return season;
        }
        if (date.after(formatDate.parse("01.03.2019")) && date.before(formatDate.parse("01.09.2019"))) {
            season = "ss19";
            return season;
        }
        if (date.after(formatDate.parse("01.09.2019")) && date.before(formatDate.parse("01.03.2020"))) {
            season = "ws1920";
            return season;
        }
        if (date.after(formatDate.parse("01.03.2020")) && date.before(formatDate.parse("01.09.2020"))) {
            season = "ss20";
            return season;
        } else season = "cant set";
        return season;
    }

    //================== open Plan
    public void openPlan(View view) {

        CacheManager cacheManager = new CacheManager(context);

        if(fachPreference != null && semesterPreference != null && groupPreference != null) {
            System.out.println("they are not null");
            System.out.println("fach in open plan"+fachPreference);
            editor.putString(FACH, fachPreference);
            editor.putString(SEMESTER, semesterPreference);
            editor.putString(GROUP, groupPreference);
            editor.putString(URL, urlPreference);
            editor.commit();

            //todo: in cache manager check cache for the precise url.. so you dont have to delete it now

            //delete cache, because List.class ist looking if there is cache when retreiving the subjects list
            cacheManager.clear();
        }

        System.out.println("url in login "+ urlPreference);



        if (preferIsSet()) {
            System.out.println("pref is set");

            Intent myIntent = new Intent(this, Plan.class);
            this.startActivity(myIntent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Zuerst wähle dein Fach, Sem und Gruppe aus!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean preferIsSet() {
        SharedPreferences sharedPref = getSharedPreferences("LoginNew", Context.MODE_PRIVATE);
        System.out.println("in pref is set: "+sharedPref.getString(FACH, "fafafafaf c"));
        if (sharedPref.contains(FACH) && sharedPref.contains(SEMESTER) && sharedPref.contains(GROUP)) {
            return true;
        } else return false;
    }


}
