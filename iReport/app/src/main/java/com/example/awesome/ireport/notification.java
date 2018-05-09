package com.example.awesome.ireport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import android.os.StrictMode;
import javax.net.ssl.HttpsURLConnection;

public class notification extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> adapter;
    String address = "http://10.0.2.2/ireport/admin/android/getNotification.php";
    InputStream is = null;
    String line = null;
    String result = null;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //allow network in the notification thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        lv = findViewById(R.id.listView);


        //Retrieve
        getData();

        //Adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, data);
        lv.setAdapter(adapter);
    }

    private void getData() {
        try {
            URL url = new URL(address);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            is = new BufferedInputStream(con.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        // read is content into a string
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //parse json data
        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo;

            data = new String[ja.length()];
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                data[i] = jo.getString("type_of_notification");
                data[i] = jo.getString("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}