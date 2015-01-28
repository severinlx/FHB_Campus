package org.alex.stundenplan;

import java.util.ArrayList;
import java.util.List;

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

/**
 * @author Alex Severin
 * 
 */
public class Login extends Activity{
	 final String[]  fileNames = {
    		"f0s2g1.xml","f0s2g2.xml","f0s2g3.xml","f0s4g1.xml","f0s4g2.xml","f0s4g3.xml","f1s2g1.xml","f2s2g1.xml","f2s2g2.xml","f2s2g3.xml","f2s4g1.xml","f2s4g2.xml","f3s2g1.xml","f4s2g1.xml","f5s2g1.xml","f6s2g1.xml","f6s4g1.xml","f7s2g1.xml","f8s2g1.xml","f8s2g2.xml","f8s4g1.xml","f8s4g2.xml","f8s4g3.xml","f8s6g1.xml","f9s2g1.xml","f10s2g1.xml","f10s4g1.xml","f11s1g1.xml","f12s1g1.xml","f12s2g1.xml","f13s2g1.xml","f13s4g1.xml","f13s6g1.xml","f14s2g1.xml","f14s2g2.xml","f14s2g3.xml","f14s2g4.xml","f14s4g1.xml","f14s4g2.xml","f14s4g3.xml","f14s6g1.xml","f14s6g2.xml","f14s6g3.xml","f15s2g1.xml","f15s2g2.xml","f15s2g3.xml","f15s2g4.xml","f15s4g1.xml","f15s4g2.xml","f15s4g3.xml","f15s4g4.xml","f15s6g1.xml","f15s6g2.xml","f15s6g3.xml","f15s6g4.xml","f16s2g1.xml","f16s4g1.xml","f16s6g1.xml","f17s2g1.xml",
    		        };
    final String fachPreference = "fach";
	final String semesterPreference = "semester";
	final String groupPreference = "group";
	
	@Override
	public void onBackPressed(){
		   if(preferIsSet()){
			this.finish();
			Intent myIntent = new Intent(this, Plan.class);
		    startActivity(myIntent);
		   }
		   else{
			   super.onBackPressed();
		   }
		}
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		final Context context = this;
		//Preferences in fach, sem, group:
		final SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
		//editor to share preferencess:
		final SharedPreferences.Editor editor = sharedPref.edit();
		
		
		final Button selectFach = (Button)findViewById(R.id.button1);
		final Button selectSemester = (Button)findViewById(R.id.button2);
		final Button selectGroup = (Button)findViewById(R.id.button3);
		
		final userData user = new userData();
		
		final Integer[] semester = {1,2,3,4,5,6,7,8,};
		final Integer[] group = {1,2,3,4};
		//boolean[][][] comb = {{{true, true, true, false, false, false}, {true, true, true, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, true, true, false, false, false}, {true, true, true, false, false, false}, {true, true, true, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, true, false, false, false, false}, {true, true, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, true, true, true, false, false}, {true, true, true, false, false, false}, {true, true, true, false, false, false}, {true, true, true, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, true, true, true, false, false}, {true, true, true, false, false, false}, {true, true, true, false, false, false}, {true, true, true, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{true, false, false, false, false, false}, {true, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}, {{false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}, {false, false, false, false, false, false}}};
		final String [][][] comb = {{{null, null, null, null, null, null}, { "1 ",  "2 ",  "3 ", null, null, null}, {null, null, null, null, null, null}, { "1 ",  "2 ",  "3 ", null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ",  "2 ",  "3 ", null, null, null}, {null, null, null, null, null, null}, { "1 ",  "2 ",  "3 ", null, null, null}, {null, null, null, null, null, null}, { "1 ",  "2 ",  "3 ", null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ",  "2 ", "3", null, null, null}, {null, null, null, null, null, null}, { "1 ",  "2 ", null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, { "1 ", null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ",  "2 ",  "3",  "4", null, null}, {null, null, null, null, null, null}, { "1",  "2",  "3", null, null, null}, {null, null, null, null, null, null}, { "1",  "2",  "3", null, null, null}, {null, null, null, null, null, null}, { "1",  "2",  "3", null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ",  "2 ",  "3 ",  "4 ", null, null}, {null, null, null, null, null, null}, { "Automatisierungstechnik ",  "Mechatronik ",  "Siemens-Technik-Akademie ", null, null, null}, {null, null, null, null, null, null}, { "Automatisierungstechnik ",  "Gebäudesystemtechnik ",  "Mechatronik ", null, null, null}, {null, null, null, null, null, null}, { "Automatisierungstechnik ",  "Gebäudesystemtechnik ",  "Mechatronik ", null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}}, {{null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, { "1 ", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}, {{null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}}
;
	
		final String[] fachs={      "Betriebswirtschaftslehre (Bachelor)",
				                    "Betriebswirtschaftslehre (Master)",
				                    "Wirtschaftsinformatik (Bachelor)",
				                    "Wirtschaftsinformatik (Master)",
				                    "Security Management (Master)",
				                    "Technologie- und Innovationsmanagement (Master)",
				                    "Applied Computer Science (Bachelor)",
		                    "Digitale Medien (Master)",
				                    "Informatik (Bachelor)",
				                    "Informatik (Master)",
				                    "Medizininformatik (Bachelor)",
				                    "Computer Aided Robust Engineering (Master)",
				                    "Energieeffizienz Technischer Systeme (Master)",
				                    "IT-Elektronik (Bachelor)",
				                    "Maschinenbau (Bachelor)",
				                    "Mechatronik Siemens-Akademie (Bachelor)",
				                    "Mechatronik / Automatisierungssysteme (Bachelor)",
				                    "Mikrosystemtechnik und Optische Technologien (Bachelor) ",
				                    "Photonics (Master)",};
		
		//dialog constructor:
		final AlertDialog.Builder builder1=new AlertDialog.Builder(Login.this);
		//===================================== fach dialog=================================================================
		selectFach.setOnClickListener(new OnClickListener(){
			  public void onClick(View v){
			    AlertDialog.Builder builder3=new AlertDialog.Builder(Login.this);
			    builder3.setTitle("Wähle dein Fach aus: ").setItems(fachs, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int which){
			    	//set fach as number
			    	Integer fachCode = which;
				    //editor.putString(fachPreference, fachCode.toString());
				    editor.putInt(fachPreference, which);
				    editor.putString("fachName", fachs[which]);
					editor.commit();
					selectFach.setText(fachs[which]);
			//Toast.makeText(getApplicationContext(), "Sie haben "+fachs[which]+ " gewählt!", Toast.LENGTH_LONG).show();
			} 
			    });
			  builder3.show();
			  }
		 });
		
		//===========================================semester dialog:============================================================
		selectSemester.setOnClickListener(new OnClickListener(){
			  public void onClick(View v){
				  if(!sharedPref.contains(fachPreference)){
					  Toast.makeText(getApplicationContext(), "Wählen Sie bitte den Fach erst! ", Toast.LENGTH_LONG).show();
			  }
				  else{
			  AlertDialog.Builder builder4=new AlertDialog.Builder(Login.this);
			  //-------------------------------------select available sem for fach----------------------------------------------
			  //get fach from preferences
			  SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
			  int fachNr = sharedPref.getInt(fachPreference, 1);
			  //initialize arraylist for store semesters
			  List<String> sem= new ArrayList();
			  //get semester from comb at index fachNr:
			  Integer semNr=0;
			  for(String[] a: comb[fachNr]){
				  if(a[0]!=null){
					 sem.add(Integer.toString(semNr));
				  }
				  semNr++;
			  }
			  String[] semItems = new String[sem.size()];
			  int i=0;
			  for(String a: sem){
				  semItems[i] =a;
				  i++;
			   }
			  //-------------------------------------------------------------------------------------------
			  builder4.setTitle("Wähle dein Semester aus: ").setItems(semItems, new DialogInterface.OnClickListener(){
			  public void onClick(DialogInterface dialog, int which){
				//-------------------------------------select available sem for fach----------------------------------------------
				  SharedPreferences sharedPref = context.getSharedPreferences("Login",Context.MODE_PRIVATE);
				  int fachNr = sharedPref.getInt(fachPreference, 1);
				  List<String> sem= new ArrayList();
				  Integer semNr=0;
				  for(String[] a: comb[fachNr]){
					  if(a[0]!=null){
						 sem.add(Integer.toString(semNr));
					  }
					  semNr++;
				  }
				  String[] semItems = new String[sem.size()];
				  int i=0;
				  for(String a: sem){
					  semItems[i] =a;
					  i++;
				   }
				  //-------------------------------------------------------------------------------------------
				 
				  
				  //set semester preferences:
				  //editor.putString(semesterPreference, semItems[which]);
				   
				  editor.putInt(semesterPreference, Integer.parseInt(semItems[which]));
				  editor.commit();
				  selectSemester.setText(semItems[which]+ " Semester");
			//Toast.makeText(getApplicationContext(), "Sie haben "+semester[which]+ " Semester gewählt!", Toast.LENGTH_LONG).show();
			}	
			  });
			  builder4.show();
			  }
			  }
		});
		
		//===========================================group dialog:==========================================================
		selectGroup.setOnClickListener(new OnClickListener(){
			  public void onClick(View v){
				  if(!sharedPref.contains(semesterPreference)){
					  Toast.makeText(getApplicationContext(), "Wählen Sie bitte den Semester erst! ", Toast.LENGTH_LONG).show();
			  
			  }
				  else{
					  AlertDialog.Builder builder4=new AlertDialog.Builder(Login.this);
					//-------------------------------------select available sem for fach----------------------------------------------
					   
					  SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
					  int fachNr = sharedPref.getInt(fachPreference, 1);
					  int semNr =  sharedPref.getInt(semesterPreference, 1);
					   
					  List<String> groupList= new ArrayList();
					  
					  for(String a: comb[fachNr][semNr]){
						  if(a!=null){
							 groupList.add(a);
						  }
					  }
					  String[] groupItems = new String[groupList.size()];
					  int i=0;
					  for(String a: groupList){
						  groupItems[i] =a;
						  i++;
						   
					   }
					  //-------------------------------------------------------------------------------------------
					  
					  builder4.setTitle("Wähle deine Gruppe aus: ");
					   
					  builder4.setItems(groupItems, new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int which) {
		              //set group preferences:
						  editor.putInt(groupPreference, group[which]);
						  editor.commit();
						  selectGroup.setText(group[which]+ " Gruppe");
//Toast.makeText(getApplicationContext(), "Sie haben "+group[which]+ " Gruppe gewählt!", Toast.LENGTH_LONG).show();
					  } 
					  });
					  builder4.show();
				  }
			  }
		});
		
		
	}
	//=================================open Plan
	public void openPlan(View view){

    	 
    	SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
    	 
    	 
		 
  
		
				boolean preferIsSet = preferIsSet();
				if(preferIsSet){
				    
				   //u
				   //if(fileCorrect){
				   Intent myIntent = new Intent(this, Plan.class);
				    this.startActivity(myIntent);
				    finish();
				}
				else{
					 Toast.makeText(getApplicationContext(), "Zuerst wähle dein Fach, Sem und Gruppe aus!", Toast.LENGTH_LONG).show();  
				   }
	}
	//=====================================================================================================
	public boolean preferIsSet(){
		SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
		if(sharedPref.contains(fachPreference) && sharedPref.contains(semesterPreference) &&sharedPref.contains(groupPreference)){
			return true;
		}
		else return false;
	}
	
	/*public boolean preferenceCorrect(){
		SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
		String fach = sharedPref.getString("fach", "no");
		String sem = sharedPref.getString("semester", "no");
		String group = sharedPref.getString("group", "no");
		String file= "f"+fach+"s"+sem+"g"+group+".xml";
		for(String existedFile: fileNames){
			if(file.equals(existedFile)){
				 
				return true;
			}
		}
		return false;
	}*/
	
	
   

	
}


