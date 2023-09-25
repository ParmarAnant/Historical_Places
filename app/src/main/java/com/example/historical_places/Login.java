package com.example.historical_places;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText edt_user_name, edt_password;
    Button btn_login;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // to make status bar black
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));

        //find views by their id
        findingViews();

        // login via shared preference when click on btn_login
        btn_login.setOnClickListener(view -> {
            sharedPrefLogin();
        });
    }

    // login using shared preference
    private void sharedPrefLogin() {
        sharedPref = getSharedPreferences("login_pref", MODE_PRIVATE);
        editor = sharedPref.edit();

        String str_edt_user_name = edt_user_name.getText().toString().trim();
        String str_edt_password = edt_password.getText().toString().trim();

        //check whether the field is null or not
        if (str_edt_user_name.length() == 0) edt_user_name.setError("Enter name");
        else if (str_edt_password.length() == 0) edt_password.setError("Enter password");
        else {
            editor.putString("shrd_user_name", edt_user_name.getText().toString());
            editor.putString("shrd_password", edt_password.getText().toString());
            editor.apply();

            // when login is successful
            Intent intent = new Intent(Login.this, rcv_content.class);
            startActivity(intent);
        }
    }

    // finding views by their id
    private void findingViews() {
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
    }
}