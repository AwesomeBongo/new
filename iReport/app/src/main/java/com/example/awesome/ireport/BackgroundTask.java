package com.example.awesome.ireport;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask <String, Void, String> {
    AlertDialog alertDialog;
    Context context;
    BackgroundTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("form Information...");
    }

    @Override
    protected String doInBackground(String... params) {
        String report_url = "http://10.0.2.2/ireport/admin/android/report.php";
        String type = params[0];
        String reg_url = "http://10.0.2.2/ireport/admin/android/register.php";
        String login_url = "http://10.0.2.2/ireport/admin/android/login.php";


        if (type.equals("login"))
        {
            try {
                String login_email = params[1];
                String login_pass = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("login_email","UTF-8")+"="+URLEncoder.encode(login_email,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  response;

            } catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

        } else if(type.equals("register"))
        {
            try {
                String fname = params[1];
                String lname = params[2];
                String oname = params[3];
                String email = params[4];
                String gender = params[5];
                String dob = params[6];
                String phone = params[7];
                String address = params[8];
                String pass = params[9];
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("firstname","UTF-8") +"="+URLEncoder.encode(fname,"UTF-8")+"&"+
                        URLEncoder.encode("lastname","UTF-8") +"="+URLEncoder.encode(lname,"UTF-8")+"&"+
                URLEncoder.encode("othername","UTF-8") +"="+URLEncoder.encode(oname,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8") +"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("gender","UTF-8") +"="+URLEncoder.encode(gender,"UTF-8")+"&"+
                        URLEncoder.encode("dob","UTF-8") +"="+URLEncoder.encode(dob,"UTF-8")+"&"+
                        URLEncoder.encode("phone_number","UTF-8") +"="+URLEncoder.encode(phone,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8") +"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8") +"="+URLEncoder.encode(pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration Successful...";

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        } else if(type.equals("report"))
        {
            try {
                String typeIncident = params[1];
                String description = params[2];
                String location = params[3];
                URL url = new URL(report_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("type_of_incident","UTF-8") +"="+URLEncoder.encode(typeIncident,"UTF-8")+"&"+
                        URLEncoder.encode("description","UTF-8") +"="+URLEncoder.encode(description,"UTF-8")+"&"+
                        URLEncoder.encode("location","UTF-8") +"="+URLEncoder.encode(location,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Report send Successfully...";

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Registration Successful...")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Login Successfully...")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }else if (result.equals("Report send Successfully...")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
        else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }

    }
}