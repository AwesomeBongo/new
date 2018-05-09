package com.example.awesome.ireport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Button buttonreg;
    EditText ET_email,ET_pass;
    String stemail, stpass;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnclickButtonListener();
        ET_email = (EditText)findViewById(R.id.userEmail);
        ET_pass = (EditText)findViewById(R.id.userpass);
        btnlogin = (Button) findViewById(R.id.register);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  btnlogin(); // call when the button is clicked to validate input
            }
        });

    }
   /* public void btnlogin(){
        Initialize(); //initialize the input to string variables
        if (!validate()){
            Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show();
        }
        else {
            onLoginSuccess();
        }
    }
    public void onLoginSuccess(){
        //todo what will go after the valid input
        Toast.makeText(this,"Logged in successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, menu.class));
    }
    public boolean validate(){
        boolean valid = true;
        if (stemail.isEmpty()){
            ET_email.setError("Please enter First name");
            ET_email.requestFocus();
            valid = false;
        }
        if (stpass.isEmpty()){
            ET_pass.setError("Please enter Surname");
            ET_pass.requestFocus();
            valid = false;
        }
        return valid;
    }
    public void  Initialize(){
        stemail = ET_email.getText().toString().trim();;
        stpass = ET_pass.getText().toString().trim();
    }
*/

   public void OnclickButtonListener(){
        buttonreg = (Button)findViewById (R.id.btnreg);
        buttonreg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.awesome.ireport.registration");
                        startActivity(intent);
                    }
                }
        );
    }

    public void userLogin(View view) {
        String login_email = ET_email.getText().toString();
        String login_pass = ET_pass.getText().toString();
        String type = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(type,login_email,login_pass);

    }

    public void openReg(View view) {
        startActivity(new Intent(this,registration.class));

    }
}