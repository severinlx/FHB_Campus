package org.alex.stundenplan.iq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.alex.stundenplan.helpClasses.DrawerActivity;
import org.alex.stundenplan.R;
import org.alex.stundenplan.stundenplan.PlanActivity;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventActivity extends DrawerActivity  {
    Bitmap bmp;
    private ListView mListView;


    // TODO Change to a more suitable type for 'events' (e.g. JSONArray...)
    private List<String> events = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Adding our layout to parent class frame layout.
        getLayoutInflater().inflate(R.layout.activity_events, frameLayout);
        //Setting title and itemChecked
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);
       // setContentView(R.layout.activity_events);



        // TODO Use new CardLayout for events (with images!)!
        // Prepare list
        mListView = (ListView) findViewById(R.id.event_list);
        final ArrayAdapter<String> eventsAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, events);
        mListView.setAdapter(eventsAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
                intent.putExtra("event_title", parent.getItemAtPosition(pos).toString());
                intent.putExtra("position", pos);
                startActivity(intent);
            }
        });

        // Fetch events from server or from local storage if no connection
        new updateEventList(eventsAdapter).execute();
    }

    public void onBackPressed() {

        Intent myIntent = new Intent(this, PlanActivity.class);

        this.startActivity(myIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class updateEventList extends AsyncTask<Void, Void, String> {
        ArrayAdapter<String> eventsAdapter;

        public updateEventList(ArrayAdapter<String> eventsAdapter) {
            this.eventsAdapter = eventsAdapter;
        }

        @Override
        protected String doInBackground(Void... params) {
            String json = "";

            try {
                URL url = new URL(
                        "https://mobile-quality-research.org/services/events/list.php");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        url.openStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    json += inputLine;
                }
                in.close();

                return json;

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }
            return json;
        }

        protected void onPostExecute(String json) {
            // Store events
            SharedPreferences prefs = getSharedPreferences("CampusAppStorage", 0);
            prefs.edit().putString("events", json).commit();

            // Iterate through events and fill list
            try {
                JSONArray allEvents = new JSONArray(json);
                JSONObject event;

                for (int i = 0; i < allEvents.length(); i++) {
                    event = (JSONObject) allEvents.opt(i);

                    String strCurrentDate = event.getString("start_time");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMAN);
                    Date newDate = format.parse(strCurrentDate);

                    format = new SimpleDateFormat("EEEE, dd.MM.", Locale.GERMAN);
                    String date = format.format(newDate);

                    events.add(Html.fromHtml(event.getString("title")+" ("+date+")").toString());
                }

                eventsAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public List<String> getEvents() {
        return events;
    }
    public void setEvents(List<String> events) {
        this.events = events;
    }
}
