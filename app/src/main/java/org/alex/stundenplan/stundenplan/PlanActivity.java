package org.alex.stundenplan.stundenplan;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.alex.stundenplan.helpClasses.CacheManager;
import org.alex.stundenplan.helpClasses.DrawerActivity;
import org.alex.stundenplan.R;

//import android.support.v7.app.ActionBarActivity;

/**
 * @author Alex Severin
 *
 */
public class PlanActivity extends DrawerActivity {


    private static Context context;
    protected ListView listView;

    //======================          onCreate        ============================

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = PlanActivity.this;

        //----------------------  NavDrawer

        getLayoutInflater().inflate(R.layout.activity_stundenplan_list, frameLayout);
        //Setting title and itemChecked
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);


        listView = (ListView)findViewById(R.id.listView1);

        //---------------------   adapter
        makeAdapter();
    }

    //----------------------------------------------------------------------------


    protected void onPause(){
        super.onPause();
    }
    protected void onResume(){
        super.onResume();
    }


    //==============      Adapter to load the PlanActivity on activity_stundenplan_list:   ==================
    public void makeAdapter() {


        CacheManager cacheManager = new CacheManager(context);
        ConnectivityManager connMgr = (ConnectivityManager)

        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //if connection exist go in asynctask to init adapter

        Stundenplan_Adapter stundenplanAdapter = null ;
        if (cacheManager.isCache() || (networkInfo != null && networkInfo.isConnected())){
            try {
                stundenplanAdapter = new Stundenplan_Adapter(context);
                listView.setAdapter(stundenplanAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        else {
            Toast.makeText(this, "Leider keine Internetverbindung... Für den Stundenplan brauchen Sie Internet ", Toast.LENGTH_LONG).show();
        }



    }
}



