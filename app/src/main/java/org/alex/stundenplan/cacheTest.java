package org.alex.stundenplan;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.alex.stundenplan.backend.parserEndpoint.model.ESubject;

import java.util.ArrayList;


public class cacheTest extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_test);

        java.util.List<ESubject> subjectList = new ArrayList<>();
        ESubject subject =new ESubject();
        subject.setDay("day");
        subject.setRoom("r");
        subject.setSubj("s");
        subject.setFirstLesson(true);
        subject.setTime("time");

        subjectList.add(subject);
        List list = new List(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cache_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
