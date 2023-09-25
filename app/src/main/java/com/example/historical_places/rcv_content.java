package com.example.historical_places;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class rcv_content extends AppCompatActivity {
    RecyclerView rcv_content;
    TextView txt_user_name;
    ImageView img_logout;
    ArrayList<Place_model> arr_places = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcv_content);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.color_primary));

        //finding views by their id
        findingViews();

        //set user name by getting user name value from shared preferences
        setUserName();

        // set logout process
        img_logout.setOnClickListener(view -> {
            AlertDialog.Builder alrt_logout = new AlertDialog.Builder(rcv_content.this);
            alrt_logout.setTitle("Log Out");
            alrt_logout.setMessage("Are you sure you want to logout?");

            // if user press Yes --> logout
            alrt_logout.setPositiveButton("Yes", (dialogInterface, i) -> {
                SharedPreferences sharedPref = getSharedPreferences("login_pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.clear();
                editor.apply();
                Intent intent = new Intent(rcv_content.this, Login.class);
                startActivity(intent);
            });

            alrt_logout.setNegativeButton("No", null);
            alrt_logout.show();

        });

        //add detail of places
        addPlaces();

        RecyclerPlaceAdapter adapter = new RecyclerPlaceAdapter(this, arr_places);
        rcv_content.setAdapter(adapter);
    }

    //add detail of places
    private void addPlaces() {
        arr_places.add(new Place_model(R.drawable.tajmahal, "Taj Mahal", "Agra, India", getString(R.string.txt_descr_tajmahal), "https://en.wikipedia.org/wiki/Taj_Mahal", getString(R.string.lat_tajmahal), getString(R.string.log_tajmahal)));
        arr_places.add(new Place_model(R.drawable.somnath, "Somnath", "Gujarat, India", getString(R.string.txt_descr_somnath), "https://en.wikipedia.org/wiki/Somnath_temple", getString(R.string.lat_somnath), getString(R.string.log_somnath)));
        arr_places.add(new Place_model(R.drawable.kedarnath, "Kedarnath", "Uttarakhand, India", getString(R.string.txt_descr_kedarnath), "https://en.wikipedia.org/wiki/Kedarnath_Temple", getString(R.string.lat_kedarnath), getString(R.string.log_kedarnath)));
        arr_places.add(new Place_model(R.drawable.badrinath, "Badrinath", "Uttarakhand, India", getString(R.string.txt_descr_badrinath), "https://en.wikipedia.org/wiki/Badrinath_Temple", getString(R.string.lat_badrinath), getString(R.string.log_badrinath)));
        arr_places.add(new Place_model(R.drawable.kailasa, "Kailash Temple", "Maharashtra, India", getString(R.string.txt_descr_kailasa), "https://en.wikipedia.org/wiki/Kailasa_Temple,_Ellora#:~:text=Kailasa%20temple%20lacks%20a%20dedicatory,%22Krishnaraja%22%20(IAST%20K%E1%B9%9B%E1%B9%A3%E1%B9%87ar%C4%81ja)%3A", getString(R.string.lat_kailasa), getString(R.string.log_kailasa)));
    }

    //finding views by their id
    private void findingViews() {
        txt_user_name = findViewById(R.id.txt_user_name);
        img_logout = findViewById(R.id.img_logout);
        rcv_content = findViewById(R.id.rcv_content);
        rcv_content.setLayoutManager(new LinearLayoutManager(this));
    }

    //set user name by getting user name value from shared preferences
    private void setUserName() {
        SharedPreferences sharedPref = getSharedPreferences("login_pref", MODE_PRIVATE);
        String str_user_name = sharedPref.getString("shrd_user_name", null);
        txt_user_name.setText(String.format("Hello %s", str_user_name));
    }

}