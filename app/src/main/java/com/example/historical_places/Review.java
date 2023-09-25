package com.example.historical_places;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Review extends AppCompatActivity {
    TextView txt_review_place_title,txt_user_name, txt_place_review;
    RatingBar rtb_rating;
    Button btn_post;

    String getRating, str_user_name;
    
    DBmanager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        
        db = new DBmanager(this);

        // finding views by their id
        findViews();

        getUserName();

        // get rating value in string to save into database later
        rtb_rating.setOnRatingBarChangeListener((ratingBar, ratingNo, b) -> {
            getRating = String.valueOf(ratingNo);
        });

        btn_post.setOnClickListener(v->{
            boolean check;

            // get values to store into database
            String userName = str_user_name;
            String name = txt_review_place_title.getText().toString();
            String rating = getRating;
            String review = txt_place_review.getText().toString().trim();

            if (rating == null || review.equals("")) {
                Toast.makeText(this, "Empty values not allowed", Toast.LENGTH_SHORT).show();
            }
            else {
                check = db.insert_data(userName, name, rating, review);
                if (check) {
                    Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, rcv_content.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getUserName() {
        SharedPreferences sharedPref = getSharedPreferences("login_pref",MODE_PRIVATE);
        str_user_name = sharedPref.getString("shrd_user_name", null);
        txt_user_name.setText(str_user_name);
    }

    // finding views by their id
    private void findViews() {
        txt_review_place_title = findViewById(R.id.txt_review_place_title);
        txt_user_name = findViewById(R.id.txt_user_name);
        txt_place_review = findViewById(R.id.txt_place_review);
        rtb_rating = findViewById(R.id.rtb_rating);
        btn_post = findViewById(R.id.btn_post);
        txt_review_place_title.setText(getIntent().getExtras().getString("txt_place_title"));
    }
}