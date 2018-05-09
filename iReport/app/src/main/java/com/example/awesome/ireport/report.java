package com.example.awesome.ireport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class report extends AppCompatActivity {

    private FusedLocationProviderClient client;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView reportImage;
    EditText etTypeIncident,etDescription, etLocation;
    String typeIncident,description,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        etTypeIncident = (EditText)findViewById(R.id.typeIncident);
        etDescription = (EditText)findViewById(R.id.description);
        etLocation = (EditText)findViewById(R.id.location);
        reportImage = (ImageView) findViewById(R.id.imageView);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);

        Button button = findViewById(R.id.getLocation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(report.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
               client.getLastLocation().addOnSuccessListener(report.this, new OnSuccessListener<Location>(){

                   public void onSuccess(Location location) {
                       if (location!= null){
                           TextView textView = findViewById(R.id.location);
                           textView.setText(location.toString());
                       }

                   }
               });
           }
        });

        Button cameraButton = (Button) findViewById(R.id.cameraButton);
        reportImage = (ImageView) findViewById(R.id.reportImage);

        //disable the button if the user has no camera
        if (!hasCamera())
            cameraButton.setEnabled(false);
    }
    /*public void sendReport(){
        Initialize(); //initialize the input to string variables
        if (!validate()){
            Toast.makeText(this,"registration failed",Toast.LENGTH_SHORT).show();
        }
        else {
            sendreport();
        }
    }
    public void sendreport(){
        //todo what will go after the valid input
        String typeIncident = etTypeIncident.getText().toString();
        String description = etDescription.getText().toString();
        String location = etLocation.getText().toString();
        String type = "report";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(type,typeIncident,description,location);
        finish();
        startActivity(new Intent(this,menu.class));
    }
    public boolean validate(){
        boolean valid = true;
        if (typeIncident.isEmpty()){
            etTypeIncident.setError("Please enter type of Incident");
            etTypeIncident.requestFocus();
            valid = false;
        }
        if (description.isEmpty()){
            etDescription.setError("Please give more details");
            etDescription.requestFocus();
            valid = false;
        }
        return valid;
    }
    public void  Initialize(){
        typeIncident = etTypeIncident.getText().toString().trim();
        description = etDescription.getText().toString().trim();
        location = etLocation.getText().toString().trim();

    }*/

    //check if the user has camera6
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Launching the camera
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //take a picture and pass result along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //if you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            reportImage.setImageBitmap(photo);
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    public void sendreport(View view) {
        String typeIncident = etTypeIncident.getText().toString();
        String description = etDescription.getText().toString();
        String location = etLocation.getText().toString();
        String type = "report";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(type,typeIncident,description,location);
        finish();
        startActivity(new Intent(this,menu.class));
    }
}
