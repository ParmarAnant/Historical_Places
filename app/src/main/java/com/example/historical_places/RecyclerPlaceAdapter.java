package com.example.historical_places;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerPlaceAdapter extends RecyclerView.Adapter<RecyclerPlaceAdapter.ViewHolder> {
    Context context;
    ArrayList<Place_model> arr_places;


    RecyclerPlaceAdapter(Context context, ArrayList<Place_model> arr_places) {
        this.context = context;
        this.arr_places = arr_places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.place_lst_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Place_model temp = arr_places.get(position);
        holder.img_place_image.setImageResource(arr_places.get(position).img_place);
        holder.txt_place_name.setText(arr_places.get(position).txt_place);
        holder.txt_place_state.setText(arr_places.get(position).txt_place_state);


        // set on click listener on item
        holder.img_place_image.setOnClickListener(view -> {
            Intent intent = new Intent(context, place_details.class);

            //pass data about image
            intent.putExtra("img_place", temp.getImg_place());
            intent.putExtra("txt_place_name", temp.getTxt_place());
            intent.putExtra("txt_place_state",temp.getTxt_place_state());
            intent.putExtra("txt_place_description", temp.getTxt_place_description());
            intent.putExtra("txt_place_url", temp.getTxt_place_url());
            intent.putExtra("txt_latitude",temp.getTxt_latitude());
            intent.putExtra("txt_longitude",temp.getTxt_longitude());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return arr_places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_place_image;
        TextView txt_place_name, txt_place_state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_place_image = itemView.findViewById(R.id.img_place_image);
            txt_place_name = itemView.findViewById(R.id.txt_place_text);
            txt_place_state = itemView.findViewById(R.id.txt_place_state);
        }
    }
}
