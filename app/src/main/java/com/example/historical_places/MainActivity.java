package com.example.historical_places;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    String str_user_name, str_password;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // to make status bar transparent
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        sharedPref = getSharedPreferences("login_pref",MODE_PRIVATE);

        // getting shared preference to check whether user has logged in or not
        str_user_name = sharedPref.getString("shrd_user_name",null);
        str_password = sharedPref.getString("shrd_password",null);

        new Handler().postDelayed(() -> {
            if (str_user_name!=null || str_password!=null) {
                // pass intent to rcv_content.class activity if user is logged in
                i = new Intent(MainActivity.this, rcv_content.class);
                startActivity(i);
            } else {
                // pass intent to Login.class activity if user has not logged in
                i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
            finish();
        },1000);
    }
}