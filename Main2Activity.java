package com.example.shanylajara.team_scores;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity  {

    private TextView Champion;
    private TextView Points;

    private Button callButton;
    private Button msg;
    private Button maploc;
    private EditText phoneNum;
    public static final int REQUEST_CALL_PHONE = 1;
    private static final String TAG = "Main2Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Champion = (TextView) findViewById(R.id.champion);
        Intent intent = getIntent();
        String Champ = intent.getExtras().get(MainActivity.team).toString();
        Champion.setText(Champ);

        String Score = intent.getExtras().get(MainActivity.KEY_SCORE).toString();
        Points = (TextView) findViewById(R.id.points);
        Points.setText("Won by: " + Score + " points!");


        maploc = (Button) findViewById(R.id.map);

        phoneNum = (EditText) findViewById(R.id.phoneNumber);
        phoneNum.setVisibility(View.GONE);


        msg = (Button) findViewById(R.id.send_msg);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    TextingOptions();


                }

        });


        callButton = (Button) findViewById(R.id.call);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallingOptions();

            }

        });

        maploc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locateGames();

            }

        });


    }

    private void TextingOptions() {
        final AlertDialog.Builder ChooseContact = new AlertDialog.Builder(this);
        ChooseContact.setTitle("Choose a contact");
        ChooseContact.setMessage("Click RECENT to add the number on system, " +
                "click NEW to insert a new number.");
        ChooseContact.setPositiveButton("SAVED", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shareTextWithContact();
            }
        });
        ChooseContact.setNegativeButton("NEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newNumber();
            }
        });
        ChooseContact.show();
    }


    private void CallingOptions() {
        final AlertDialog.Builder ChooseContact = new AlertDialog.Builder(this);
        ChooseContact.setTitle("Choose a contact to call");
        ChooseContact.setMessage("Click SAVED to call the number on system, " +
                "click NEW to insert a new number.");
        ChooseContact.setPositiveButton("SAVED", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CallContact();
            }
        });
        ChooseContact.setNegativeButton("NEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newNumber2call();
            }
        });
        ChooseContact.show();
    }

    private void newNumber() {
        AlertDialog.Builder newNumberDialog = new AlertDialog.Builder(this);
        newNumberDialog.setTitle("Enter number to contact");

        final EditText phoneNumInput = new EditText(this);

        LinearLayout.LayoutParams MatchPARENT = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        phoneNumInput.setLayoutParams(MatchPARENT);
        phoneNumInput.setGravity(Gravity.CENTER_HORIZONTAL);
        phoneNumInput.setInputType(InputType.TYPE_CLASS_PHONE);

        phoneNumInput.setHint("Enter phone number");

        newNumberDialog.setView(phoneNumInput);


        newNumberDialog.setNegativeButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (phoneNumInput.length() > 0) {
                    final String number = phoneNumInput.getText().toString();
                    shareText(number);
                }}
        });

        newNumberDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                
                dialog.dismiss();
            }
        });

        newNumberDialog.show();
    }

    private void newNumber2call() {
        AlertDialog.Builder newNumberDialog = new AlertDialog.Builder(this);
        newNumberDialog.setTitle("Enter number to call");

        final EditText phoneNumInput = new EditText(this);

        LinearLayout.LayoutParams MatchPARENT = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        phoneNumInput.setLayoutParams(MatchPARENT);
        phoneNumInput.setGravity(Gravity.CENTER_HORIZONTAL);
        phoneNumInput.setInputType(InputType.TYPE_CLASS_PHONE);

        phoneNumInput.setHint("Enter phone number");

        newNumberDialog.setView(phoneNumInput);


        newNumberDialog.setNegativeButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (phoneNumInput.length() > 0) {
                    final String number = phoneNumInput.getText().toString();
                    callNew(number);
                }}
        });

        newNumberDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                
                dialog.dismiss();
            }
        });

        newNumberDialog.show();
    }

    public void callNew(String number) {


        String contact = "tel:" + number;

        Log.d(TAG, "inside of callFriend method");
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(contact));

        if (intent.resolveActivity(getPackageManager()) != null) {

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);


            } else {
                startActivity(intent);
            }

        } else {
            Log.d(TAG, "cannot make a call");

        }
    }


    public void CallContact() {


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String contact = "tel:"+sharedPreferences.getString("example_text3", "").toString();

        Log.d(TAG, "inside of callFriend method");
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(contact));

        if (intent.resolveActivity(getPackageManager()) != null) {

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
               
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);


            } else {
                startActivity(intent);
            }

        } else {
            Log.d(TAG, "cannot make a call");

        }
    }

    public void shareText(String number) {
        Log.d(TAG, "inside of callJenny method");
        String thewinner = Champion.getText().toString();
        String message = "Hi there, Here go the scores! " +thewinner+" "+Points.getText().toString();

        String contact1 = "smsto:" + number;

        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(contact1));
        intent2.putExtra("sms_body", message);

        if (intent2.resolveActivity(getPackageManager()) != null) {
            startActivity(intent2);
        } else {
            Log.d(TAG, "Your call could not be completed as dialed");
            }
        }

    private void shareTextWithContact() {
        Log.d(TAG, "inside of callJenny method");
        String thewinner = Champion.getText().toString();
        String message = "Hi there, Here go the scores! " +thewinner+" "+Points.getText().toString();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String contact = "smsto:"+sharedPreferences.getString("example_text3", "").toString();

        Toast.makeText(this, "contact #: "+ contact, Toast.LENGTH_SHORT).show();

        Intent intentsavedcontact = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:"+contact));
        intentsavedcontact.putExtra("sms_body", message);

        if (intentsavedcontact.resolveActivity(getPackageManager()) != null) {
            startActivity(intentsavedcontact);
        } else {
            Log.d(TAG, "Your call could not be completed as dialed");
        }
    }


    public void locateGames(){

        Uri locData = Uri.parse("geo:0,0?q=Baseball+games+near+my+location");
        Intent Intent3 = new Intent(Intent.ACTION_VIEW, locData);
        Intent3.setPackage("com.google.android.apps.maps");
        startActivity(Intent3);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "inside onRequestPermissionsResult");
        switch (requestCode) {
            case REQUEST_CALL_PHONE: {
                if ((grantResults.length > 0) &&
                        (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    CallContact();

                }
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        PreferenceManager.setDefaultValues( this, R.xml.pref_sports, false);
        SharedPreferences sharedPreferences2 =
                PreferenceManager.getDefaultSharedPreferences(this);

        String WinnerBG = sharedPreferences2.getString("list_preference_3", "");

        Toast.makeText(this, "this the team"+WinnerBG, Toast.LENGTH_SHORT).show();

        RelativeLayout EndActLayout = (RelativeLayout) findViewById(R.id.winneract);

        if (WinnerBG.equalsIgnoreCase("Medal")){
            EndActLayout.setBackgroundResource(R.drawable.medal2); 
        }
        else if (WinnerBG.equalsIgnoreCase("Cup")){
            EndActLayout.setBackgroundResource(R.drawable.win); 
        }

        else if (WinnerBG.equalsIgnoreCase("ThumbsUp")){
            EndActLayout.setBackgroundResource(R.drawable.thummm); 
        }

    }



}


