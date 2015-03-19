package org.alex.stundenplan.backendConnection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.alex.stundenplan.backend.myApi.MyApi;
import org.alex.stundenplan.backend.parserEndpoint.ParserEndpoint;
import org.alex.stundenplan.backend.parserEndpoint.model.ESubject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private static ParserEndpoint parser=null;
    String url="Stundenplan/ws1415/plan/acs-3.html";
    List<ESubject> subjectList = new ArrayList<>();


    protected String doInBackground(Pair<Context, String>... params) {

        context = params[0].first;

        //--------------------cloud connection------------------------------

        if(parser == null) { // Only do this once
            ParserEndpoint.Builder builder = new ParserEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://fhb-backend.appspot.com/_ah/api/");

                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                   // .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                   // .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        //@Override
                       // public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                      //      abstractGoogleClientRequest.setDisableGZipContent(true);
                       // }
                  //  });

            // end options for devappserver
             builder.setApplicationName("FHB");
            parser  = builder.build();
        }

        //----------------------main method:--------------------------------

        try {

            subjectList = parser.getList(url).execute().getItems();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //==================================================================

        /*
        //---------------------code from example----------------------------
        if(myApiService == null) {  // Only do this once
            //settings for deply server:
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://fhb-backend.appspot.com/_ah/api/");
            /*
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)

                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            //comment block end

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();


        } catch (IOException e) {
            return e.getMessage();
        }
        //==================================================================
    */
        return subjectList.get(1).getSubj();
    }



    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}