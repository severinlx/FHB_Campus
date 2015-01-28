package org.alex.stundenplan.iq;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.alex.stundenplan.DrawerActivity;
import org.alex.stundenplan.R;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailActivity extends DrawerActivity {
    private TextView mTxtLoyaltyBonus, mTxtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Adding our layout to parent class frame layout.
        getLayoutInflater().inflate(R.layout.activity_event_detail, frameLayout);
        //Setting title and itemChecked
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);
       // setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();

        // Generate UUID and get corresponding QR code from server
        ImageView imageView = (ImageView) findViewById(R.id.picture_frame);
        new getQRCode(imageView).execute("");

        setTitle(intent.getStringExtra("event_title"));

        mTxtLoyaltyBonus = (TextView) findViewById(R.id.event_detail_announcement);
        mTxtDescription = (TextView) findViewById(R.id.event_detail_description);
    }

    private class getQRCode extends AsyncTask<String, Void, Bitmap> {
        ImageView bmpImage;

        public getQRCode(ImageView bmpImage) {
            this.bmpImage = bmpImage;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap mBmp = null;

            // TODO Store bitmap locally and check if already present to
            // minimize traffic
            SharedPreferences prefs = getSharedPreferences("CampusAppStorage", 0);
            if (prefs.getString("qrcode_bitmap", "") != "") {
                mBmp = convertStringToBitmap(prefs.getString("qrcode_bitmap",
                        ""));
                return mBmp;
            }

            AndroidHttpClient httpclient = AndroidHttpClient
                    .newInstance("CampusApp 1.0");

            try {
                HttpPost httppost = new HttpPost(
                        "https://www.mobile-quality-research.org/services/events/qr.php");

                // Add POST data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
                        3);
                nameValuePairs.add(new BasicNameValuePair("API_KEY", "12345"));
                nameValuePairs.add(new BasicNameValuePair("UUID",
                        getUniquePsuedoID()));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httppost);

                if (response.getStatusLine().getStatusCode() == 200
                        || response.getStatusLine().getStatusCode() == 304) {
                    // Get hold of the response entity
                    HttpEntity entity = response.getEntity();

                    if (entity != null) {
                        InputStream instream = entity.getContent();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int bufferSize = 1024;
                        byte[] buffer = new byte[bufferSize];
                        int len = 0;

                        // instream is content got from httpentity.getContent()
                        while ((len = instream.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        baos.close();

                        byte[] b = baos.toByteArray();
                        mBmp = BitmapFactory.decodeByteArray(b, 0, b.length);
                    }
                }

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            httpclient.close();

            // Save bitmap locally
            prefs.edit().putString("qrcode_bitmap", convertBitmapToString(mBmp));
            prefs.edit().commit();

            return mBmp;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            SharedPreferences prefs = getSharedPreferences("CampusAppStorage", 0);

            try {
                JSONArray allEvents = new JSONArray(prefs.getString("events", ""));
                JSONObject event = (JSONObject) allEvents.get(getIntent().getIntExtra("position", 0));

                mTxtDescription = (TextView) findViewById(R.id.event_detail_description);
                mTxtDescription.setText(Html.fromHtml(event.getString("description")).toString());

                // If it's a loyalty event, show QR code and loyalty bonus
                if (!event.getString("loyalty").isEmpty()) {
                    // ...show QR Code and event announcement.
                    bmpImage.setImageBitmap(result);
                    bmpImage.setVisibility(View.VISIBLE);
                    mTxtLoyaltyBonus.setVisibility(View.VISIBLE);
                    mTxtLoyaltyBonus.setText(Html.fromHtml(event.getString("loyalty")).toString());
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    /**
     * Return pseudo unique ID
     *
     * @return ID
     */
    public static String getUniquePsuedoID() {
        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their phone or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Thanks http://www.pocketmagic.net/?p=1662!
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10)
                + (Build.BRAND.length() % 10) + (Build.DEVICE.length() % 10)
                + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        // Thanks to @Roman SL!
        // http://stackoverflow.com/a/4789483/950427
        // Only devices with API >= 9 have android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // If a user upgrades software or roots their phone, there will be a
        // duplicate entry
        String serial = null;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null)
                    .toString();

            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode())
                    .toString();
        } catch (Exception e) {
            // String needs to be initialized
            serial = "serial"; // some value
        }

        // Thanks @Joe!
        // http://stackoverflow.com/a/2853253/950427
        // Finally, combine the values we have found by using the UUID class to
        // create a unique identifier
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode())
                .toString();
    }

    public String convertBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    /**
     * @param encodedString
     * @return bitmap (from given string)
     */
    public Bitmap convertStringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
