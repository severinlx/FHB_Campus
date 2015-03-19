package org.alex.stundenplan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import org.json.simple.JSONObject;

/**
 * @author Alex Severin
 */
public class Login extends Activity {

    final String fachPreference = "fach";
    final String semesterPreference = "semester";
    final String groupPreference = "group";

    @Override
    public void onBackPressed() {
        if (preferIsSet()) {
            this.finish();
            Intent myIntent = new Intent(this, Plan.class);
            startActivity(myIntent);
        } else {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Context context = this;
        //Preferences in fach, sem, group:
        final SharedPreferences sharedPref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        //editor to share preferencess:
        final SharedPreferences.Editor editor = sharedPref.edit();

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

        //Buttons:

        final Button selectFach = (Button) findViewById(R.id.button1);
        final Button selectSemester = (Button) findViewById(R.id.button2);
        final Button selectGroup = (Button) findViewById(R.id.button3);

        // todo get this object online in task

        JSONObject fachsJSON = new JSONObject();
        final String[] fachs = new String[fachsJSON.size()];
        int i = 0;
        for (Iterator iterator = fachsJSON.keySet().iterator(); iterator.hasNext(); ) {
            fachs[i] = (String) iterator.next();
            i++;
        }


        //===================================== fach dialog================================================================
        selectFach.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder3 = new AlertDialog.Builder(Login.this);
                builder3.setTitle("Wähle dein Fach aus: ").setItems(fachs, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //set fach as number
                        editor.putString(fachPreference, fachs[which]);
                        editor.commit();
                        selectFach.setText(fachs[which]);
                    }
                });
                builder3.show();
            }
        });









        //=================================== semester dialog:=============================================================
        selectSemester.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (!sharedPref.contains(fachPreference)) {
                    Toast.makeText(getApplicationContext(), "Wählen Sie bitte den Fach erst! ", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(Login.this);









                    //-------------------------------------select available sem for fach----------------------------------------------
                    //get fach from preferences
                    SharedPreferences sharedPref = getSharedPreferences("Login", Context.MODE_PRIVATE);
                    String fach = sharedPref.getString(fachPreference, " ");

                    //initialize arraylist for store semesters
                    final String [] semItems = null;

                    builder4.setTitle("Wähle dein Semester aus: ").setItems(semItems, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //-------------------------------------select available sem for fach----------------------------------------------


                            editor.putInt(semesterPreference, Integer.parseInt(semItems[which]));
                            editor.commit();
                            selectSemester.setText(semItems[which] + " Semester");






                        }
                    });
                    builder4.show();
                }
            }
        });

























        //===================================== group dialog:==============================================================
        selectGroup.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (!sharedPref.contains(semesterPreference)) {
                    Toast.makeText(getApplicationContext(), "Wählen Sie bitte den Semester erst! ", Toast.LENGTH_LONG).show();

                } else {
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(Login.this);
                    //-------------------------------------select available sem for fach----------------------------------------------

                    SharedPreferences sharedPref = getSharedPreferences("Login", Context.MODE_PRIVATE);
                    int fachNr = sharedPref.getInt(fachPreference, 1);
                    int semNr = sharedPref.getInt(semesterPreference, 1);

                    List<String> groupList = new ArrayList();


                    /*for (String a : comb[fachNr][semNr]) {
                        if (a != null) {
                            groupList.add(a);
                        }
                    }*/
                    String[] groupItems = new String[groupList.size()];
                    int i = 0;
                    for (String a : groupList) {
                        groupItems[i] = a;
                        i++;

                    }
                    //-------------------------------------------------------------------------------------------

                    builder4.setTitle("Wähle deine Gruppe aus: ");

                    builder4.setItems(groupItems, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //set group preferences:
                            //editor.putInt(groupPreference, group[which]);
                            editor.commit();
                            //selectGroup.setText(group[which] + " Gruppe");
//Toast.makeText(getApplicationContext(), "Sie haben "+group[which]+ " Gruppe gewählt!", Toast.LENGTH_LONG).show();
                        }
                    });
                    builder4.show();
                }
            }
        });


    }

    public static String getActualSeason() throws ParseException {
        String season;

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);


        if (date.after(formatDate.parse("01.03.2015")) && date.before(formatDate.parse("01.09.2015"))) {
            season = "ss15";
        }
        if (date.after(formatDate.parse("01.09.2015")) && date.before(formatDate.parse("01.03.2016"))) {
            season = "ws1516";
        }
        if (date.after(formatDate.parse("01.03.2016")) && date.before(formatDate.parse("01.09.2016"))) {
            season = "ss16";
        }
        if (date.after(formatDate.parse("01.09.2016")) && date.before(formatDate.parse("01.03.2017"))) {
            season = "ws1617";
        }
        if (date.after(formatDate.parse("01.03.2017")) && date.before(formatDate.parse("01.09.2017"))) {
            season = "ss17";
        }
        if (date.after(formatDate.parse("01.09.2017")) && date.before(formatDate.parse("01.03.2018"))) {
            season = "ws1718";
        }
        if (date.after(formatDate.parse("01.03.2018")) && date.before(formatDate.parse("01.09.2018"))) {
            season = "ss18";
        }
        if (date.after(formatDate.parse("01.09.2018")) && date.before(formatDate.parse("01.03.2019"))) {
            season = "ws1819";
        }
        if (date.after(formatDate.parse("01.03.2019")) && date.before(formatDate.parse("01.09.2019"))) {
            season = "ss19";
        }
        if (date.after(formatDate.parse("01.09.2019")) && date.before(formatDate.parse("01.03.2020"))) {
            season = "ws1920";
        }
        if (date.after(formatDate.parse("01.03.2020")) && date.before(formatDate.parse("01.09.2020"))) {
            season = "ss20";
        } else season = "cant set";
        return season;
    }

    //================== open Plan
    public void openPlan(View view) {

        if (preferIsSet()) {

            Intent myIntent = new Intent(this, Plan.class);
            this.startActivity(myIntent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Zuerst wähle dein Fach, Sem und Gruppe aus!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean preferIsSet() {
        SharedPreferences sharedPref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        if (sharedPref.contains(fachPreference) && sharedPref.contains(semesterPreference) && sharedPref.contains(groupPreference)) {
            return true;
        } else return false;
    }


}


