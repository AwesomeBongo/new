package com.example.awesome.ireport;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends Activity {
    private EditText fname, lname, oname, email, gender, dob, phone, address, pass, cpass;
    private String stfname, stlname, stoname, stemail,stgender, stdob, stphone, staddress,stpass,stcpass;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fname = (EditText)findViewById(R.id.firstname);
        lname = (EditText)findViewById(R.id.surname);
        oname = (EditText)findViewById(R.id.othername);
        email = (EditText)findViewById(R.id.email);
        gender = (EditText)findViewById(R.id.gender);
        dob = (EditText)findViewById(R.id.dob);
        phone = (EditText)findViewById(R.id.phone);
        address = (EditText)findViewById(R.id.address);
        pass = (EditText)findViewById(R.id.pass);
        cpass = (EditText)findViewById(R.id.compass);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(); // call when the button is clicked to validate input
            }
        });

    }
    public void register(){
        Initialize(); //initialize the input to string variables
        if (!validate()){
            Toast.makeText(this,"registration failed",Toast.LENGTH_SHORT).show();
        }
        else {
            userReg();
        }
    }
    public void userReg(){
        //todo what will go after the valid input
        String stfname = fname.getText().toString();
        String stlname = lname.getText().toString();
        String stoname = oname.getText().toString();
        String stemail = email.getText().toString();
        String stgender = gender.getText().toString();
        String stdob = dob.getText().toString();
        String stphone = phone.getText().toString();
        String staddress = address.getText().toString();
        String stpass = pass.getText().toString();
        String type = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(type,stfname,stlname, stoname, stemail, stgender, stdob, stphone, staddress, stpass);
        finish();
        Toast.makeText(this,"Registration successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MainActivity.class));
    }
    public boolean validate(){
        boolean valid = true;
        if (stfname.isEmpty()){
            fname.setError("Please enter First name");
            fname.requestFocus();
            valid = false;
        }
        if (stlname.isEmpty()){
            lname.setError("Please enter Surname");
            lname.requestFocus();
            valid = false;
        }
        if (stoname.length()>32){
            oname.setError("Please enter valid other name");
            oname.requestFocus();
            valid = false;
        }
        if (stemail.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(stemail).matches()){
            email.setError("Please enter a valid email");
            email.requestFocus();
            valid = false;
        }
        if (stphone.isEmpty()||stphone.length()!=10){
            phone.setError("Please enter valid phone number");
            phone.requestFocus();
            valid = false;
        }
        if (staddress.isEmpty()){
            address.setError("Please enter address");
            address.requestFocus();
            valid = false;
        }
        if (stpass.isEmpty()){
            pass.setError("Please enter password");
            pass.requestFocus();
            valid = false;
        }
        if (stcpass.isEmpty()){
            cpass.setError("Please enter password");
            cpass.requestFocus();
            valid = false;
        }
        if (stpass.length()<8){
            pass.setError("Password can't be less than 8 characters");
            pass.requestFocus();
            valid = false;
        }
        if (stcpass.length()<8 || !stpass.equals(stcpass)){
            cpass.setError("Passwords do not match");
            cpass.requestFocus();
            valid = false;
        }
        if (stgender.isEmpty()|| stgender.length()>6){
            gender.setError("Please check your input");
            gender.requestFocus();
            valid = false;
        }
        return valid;
    }
    public void  Initialize(){
        stfname = fname.getText().toString().trim();
        stlname = lname.getText().toString().trim();
        stoname = oname.getText().toString().trim();
        stemail = email.getText().toString().trim();
        stgender = gender.getText().toString().trim();
        stdob = dob.getText().toString().trim();
        stphone = phone.getText().toString().trim();
        staddress = address.getText().toString().trim();
        stpass = pass.getText().toString().trim();
        stcpass = cpass.getText().toString().trim();
    }

    /* public void userReg(View view) {
        String stfname = fname.getText().toString();
        String stlname = lname.getText().toString();
        String stoname = oname.getText().toString();
        String stemail = email.getText().toString();
        String stgender = gender.getText().toString();
        String stdob = dob.getText().toString();
        String stphone = phone.getText().toString();
        String staddress = address.getText().toString();
        String stpass = pass.getText().toString();
        String type = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(type,stfname,stlname, stoname, stemail, stgender, stdob, stphone, staddress, stpass);
        finish();
        startActivity(new Intent(this,menu.class));
    }*/
}