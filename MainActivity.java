package com.example.shanylajara.team_scores;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.res.Configuration;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;
import android.view.View.OnClickListener;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    private TextView plus_a;
    private TextView plus_b;

    private ImageView team_a;
    private ImageView team_b;

    private int myCounter = 0;
    private int myCounter2 = 0;
    private int result = 0;
    private RelativeLayout background;

    private static final String TAG = "MainActivity";
    protected static final String KEY_SCORE = "scoreCount";
    protected static final String team = "team";

    private String SPORT_KEY = "sportkey";

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "inside onCreate");
        setContentView(R.layout.activity_main);
        plus_a = (TextView) findViewById(R.id.a_score);
        plus_b = (TextView) findViewById(R.id.b_score);

        team_a = (ImageView) findViewById(R.id.team_a);
        team_b = (ImageView) findViewById(R.id.team_b);

        background = (RelativeLayout) findViewById(R.id.background);


        plus_a.setText("" + myCounter);
        plus_b.setText("" + myCounter2);

        Log.d(TAG, "end of onCreate");


        Bundle extras = getIntent().getExtras();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_data_sync, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);
  
        updateBackground();
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_id:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

            

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "inside onSaveInstanceState");
        outState.putInt("myCount", myCounter);
        outState.putInt("myCount", myCounter2);

        Log.d(TAG, "end of onSaveInstanceState myCounter=" + myCounter);
        Log.d(TAG, "end of onSaveInstanceState myCounter=" + myCounter2);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myCounter = savedInstanceState.getInt("myCount", myCounter);
        myCounter2 = savedInstanceState.getInt("myCount", myCounter2);
        Log.d(TAG, "end of onRestoreInstanceState");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "inside of onStart");
        Log.d(TAG, "end of onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "inside of onResume");
        plus_a.setText("" + myCounter);
        plus_b.setText("" + myCounter2);
        PreferenceManager.setDefaultValues( this, R.xml.pref_sports, false);
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        String FavoriteSportBG = sharedPreferences.getString("list_preference_1", "");

        Toast.makeText(this, "this the team"+FavoriteSportBG, Toast.LENGTH_SHORT).show();

        RelativeLayout mainActLayout = (RelativeLayout) findViewById(R.id.background);

        if (FavoriteSportBG.equalsIgnoreCase("Sports")){
            mainActLayout.setBackgroundResource(R.drawable.sport1); 
        }
        else if (FavoriteSportBG.equalsIgnoreCase("Basketball")){
            mainActLayout.setBackgroundResource(R.drawable.basketball); 
        }

        else if (FavoriteSportBG.equalsIgnoreCase("Soccer")){
            mainActLayout.setBackgroundResource(R.drawable.soccer); 
        }

        else if (FavoriteSportBG.equalsIgnoreCase("Baseball")){
            mainActLayout.setBackgroundResource(R.drawable.bb); 
        }

        else if (FavoriteSportBG.equalsIgnoreCase("Tennis")) {
            mainActLayout.setBackgroundResource(R.drawable.tennis); 
        }


        PreferenceManager.setDefaultValues( this, R.xml.pref_sports, false);
        SharedPreferences teamIconPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        String favoriteTeam = teamIconPreferences.getString("list_preference_2", "");

        Toast.makeText(this, "this the team"+favoriteTeam, Toast.LENGTH_SHORT).show();

        RelativeLayout favoriteIS = (RelativeLayout) findViewById(R.id.teamalayout);
        ImageView getRid_of_this = (ImageView) findViewById(R.id.team_a);


        if (favoriteTeam.equalsIgnoreCase("Default Team")){

        }

        if (favoriteTeam.equalsIgnoreCase("Las Aguilas")){
            favoriteIS.setBackgroundResource(R.drawable.aguilas);
            getRid_of_this.setVisibility(View.GONE);

        }
        else if (favoriteTeam.equalsIgnoreCase("El Escogido")){
            getRid_of_this.setVisibility(View.GONE);
            favoriteIS.setBackgroundResource(R.drawable.escogido); 
        }

        else if (favoriteTeam.equalsIgnoreCase("El Licey")){
            getRid_of_this.setVisibility(View.GONE);
            favoriteIS.setBackgroundResource(R.drawable.licey1); 
        }

        else if (favoriteTeam.equalsIgnoreCase("Las Estrellas")){
            getRid_of_this.setVisibility(View.GONE);
            favoriteIS.setBackgroundResource(R.drawable.estrellas); 
        }

        else if (favoriteTeam.equalsIgnoreCase("Los Toros")){
            getRid_of_this.setVisibility(View.GONE);
            favoriteIS.setBackgroundResource(R.drawable.toros); 
        }


        Log.d(TAG, "end of onResume");


    }

    @Override
    protected void onRestart() {

        Log.d(TAG, "inside of onRestart");

        super.onRestart();
        Log.d(TAG, "end of onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "inside of onPause");


        Log.d(TAG, "end of onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "inside of onStop");

        Log.d(TAG, "end of onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "inside of onDestroy");

        Log.d(TAG, "end of onDestroy");
    }

    public void countUp(View view) {

        myCounter++;
        plus_a.setText("" + myCounter);


        if (myCounter == 5) {
            result = myCounter - myCounter2;
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra(KEY_SCORE, result); 
            intent.putExtra(team, "TEAM  A");
            startActivity(intent);
            resetScores();
        }

    }

    public void countUp2(View view) {
        myCounter2++;
        plus_b.setText("" + myCounter2);


        if (myCounter2 == 5) {
            result = myCounter2 - myCounter;
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra(KEY_SCORE, result); 
            intent.putExtra(team, "TEAM B");
            startActivity(intent);
            resetScores();
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updateBackground();
           recreate();
      }

    private void updateBackground() {

        int background_value = Integer.parseInt(preferences.getString("List_of_Sports", "0"));
        switch (background_value) {
            case 0:

                background.setBackgroundResource(R.drawable.sport1);
                break;
            case 1:
                background.setBackgroundResource(R.drawable.tennis);
                break;
            case 2:
                background.setBackgroundResource(R.drawable.soccer);
                break;
            case 3:
                background.setBackgroundResource(R.drawable.basketball);
                break;
            case 4:
                background.setBackgroundResource(R.drawable.bb);
                break;

            default:
                break;
        }

    }

    private void resetScores() {
        myCounter = 0;
        myCounter2 = 0;
    }

}