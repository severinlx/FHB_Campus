package org.alex.stundenplan.mensa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.alex.stundenplan.R;
import org.alex.stundenplan.data.MensaDay;
import org.alex.stundenplan.data.rest.FhbRestClient;
import org.alex.stundenplan.helpClasses.DrawerActivity;
import org.alex.stundenplan.stundenplan.PlanActivity;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EActivity
public class MensaActivity extends DrawerActivity implements RestErrorHandler, SwipeRefreshLayout.OnRefreshListener {

    private ExpandableListView listView;
    private MensaAdapter mensaAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @RestService
    FhbRestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Adding our layout to parent class frame layout.
        getLayoutInflater().inflate(R.layout.activity_mensa_list, frameLayout);

        //Setting title and itemChecked
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);

        // Get SwipeRefreshLayout object from xml
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        // Get ListView object from xml
        listView = (ExpandableListView) findViewById(R.id.listViewMensa);

        mensaAdapter = new MensaAdapter(this);
        listView.setAdapter(mensaAdapter);

        loadMeals();

    }

    public void onBackPressed() {

        Intent myIntent = new Intent(this, PlanActivity.class);

        this.startActivity(myIntent);

    }

    @Background
    protected void loadMeals() {

        Log.d("MensaActivity", "start REST");
        restClient.setRestErrorHandler(this);
        List<MensaDay> result = restClient.getMensaDays();
        updateMeals(result);
        Log.d("MensaActivity", "finished REST");
    }

    @UiThread
    protected void updateMeals(List<MensaDay> result) {
        Log.d("MensaActivity", "update Meals");
        mensaAdapter.updateData(result);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        Log.d("MensaActivity", "RestError");
        e.printStackTrace();
    }

    @Override
    public void onRefresh() {
        loadMeals();
    }
}