package com.example.historical_places;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.icu.number.Precision;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class place_details extends AppCompatActivity {
    ImageView img_place;
    TextView txt_place_name, txt_avg_rating, txt_count_rating, txt_place_description, txt_place_state, txt_place_db_reviews;

    RatingBar rtb_avg_rating;
    Button btn_map, btn_review, btn_wiki;

    String str_url_place, str_place_name;
    int count;
    double dbl_avg_rating = 0.0;
    DBmanager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        db = new DBmanager(this);

        //finding views by their id
        findViews();

        // set title into variable
        str_place_name = getIntent().getExtras().getString("txt_place_name");

        // set up place image, place text, place description into the views
        img_place.setImageResource(getIntent().getIntExtra("img_place", 0));
        txt_place_name.setText(str_place_name);
        txt_place_state.setText(getIntent().getExtras().getString("txt_place_state"));
        txt_place_description.setText(getIntent().getStringExtra("txt_place_description"));

        // go to wikipedia page by url
        btn_wiki.setOnClickListener(v -> {
            str_url_place = getIntent().getExtras().getString("txt_place_url");
            Uri uri = Uri.parse(str_url_place);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });

        // go to map via latitude & longitude
        btn_map.setOnClickListener(view -> {
            String latitude = getIntent().getExtras().getString("txt_latitude");
            String longitude = getIntent().getExtras().getString("txt_longitude");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + latitude + "," + longitude));
            startActivity(mapIntent);
        });

        // go to review activity
        btn_review.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Review.class);
            intent.putExtra("txt_place_title", str_place_name);
            startActivity(intent);

        });

        // set reviews
        setReviewData();
    }

    // set review data into the textview
    private void setReviewData() {
        Cursor review_data = db.view_data(str_place_name);
        if (review_data.getCount() < 0) {
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer buffer = new StringBuffer();
            while (review_data.moveToNext()) {
                buffer.append("Name: " + review_data.getString(1) + " | ");
                buffer.append("" + review_data.getString(3) + "\n");
                buffer.append("" + review_data.getString(4) + "\n\n");

                // to count average rating and set into textview
                count++;
                dbl_avg_rating += Double.parseDouble(review_data.getString(3));
                setRatingData(dbl_avg_rating, count);
            }
            // set peoples review in textview
            txt_place_db_reviews.setText(buffer.toString());
        }
    }

    // to count average rating and set into textview
    private void setRatingData(double dbl_avg_rating, int count) {
        DecimalFormat df = new DecimalFormat("###.##");

        double avg_rating = Double.parseDouble(df.format(dbl_avg_rating / count));
        int total_rating = count;

        rtb_avg_rating.setRating(Float.parseFloat(String.valueOf(avg_rating)));
        txt_avg_rating.setText(String.valueOf(avg_rating));
        txt_count_rating.setText(String.format("(%s)", total_rating));
    }

    //finding views by their id
    public void findViews() {
        img_place = findViewById(R.id.img_place);
        txt_place_name = findViewById(R.id.txt_place_name);
        txt_place_state = findViewById(R.id.txt_place_state);
        rtb_avg_rating = findViewById(R.id.rtb_avg_rating);
        txt_avg_rating = findViewById(R.id.txt_avg_rating);
        txt_count_rating = findViewById(R.id.txt_count_rating);
        txt_place_description = findViewById(R.id.txt_place_description);
        txt_place_db_reviews = findViewById(R.id.txt_place_db_reviews);
        btn_map = findViewById(R.id.btn_map);
        btn_review = findViewById(R.id.btn_review);
        btn_wiki = findViewById(R.id.btn_wiki);
    }
}