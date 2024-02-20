package com.example.bookstore.RecyclerAdapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Models.genreModel;
import com.example.bookstore.R;
import com.example.bookstore.book_category_activity;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder> {

    ArrayList<genreModel> genres;

    public GenreAdapter(ArrayList<genreModel> genres, Context context) {
        this.genres = genres;
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.genre_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.genreTitle.setText(genres.get(position).getGenreTitle());
            holder.genreImage.setImageResource(genres.get(position).getGenreImage());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genreName = genres.get(position).getGenreTitle();
                Intent intent = new Intent(context, book_category_activity.class);
                intent.putExtra("genre_name",genreName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //when you have to call the next activity from sn inner class, we set flags
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView genreImage;
        TextView genreTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            genreImage = itemView.findViewById(R.id.genreimg);
            genreTitle = itemView.findViewById(R.id.genretxt);
        }
    }
}
