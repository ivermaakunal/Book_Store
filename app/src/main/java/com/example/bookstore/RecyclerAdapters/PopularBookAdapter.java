package com.example.bookstore.RecyclerAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Models.popularBooksModel;
import com.example.bookstore.PopularBooksDetailsActivity;
import com.example.bookstore.R;

import java.util.ArrayList;

public class PopularBookAdapter extends RecyclerView.Adapter<PopularBookAdapter.MyViewHolder> {

    ArrayList<popularBooksModel> popularList;
    Context context;

    public PopularBookAdapter(ArrayList<popularBooksModel> popularList, Context context) {
        this.popularList = popularList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_layout, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.popularimg.setImageResource(popularList.get(position).getPopularBookImage());
        holder.populartitle.setText(popularList.get(position).getPopularBookTitle());
        holder.popularAuthor.setText(popularList.get(position).getPopularBookAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularBooksModel pos = popularList.get(position);
                Intent intent = new Intent(context.getApplicationContext(), PopularBooksDetailsActivity.class);
                intent.putExtra("book_title",pos.getPopularBookTitle());
                intent.putExtra("book_author",pos.getPopularBookAuthor());
                intent.putExtra("book_cost",pos.getPopularBookCost());
                intent.putExtra("book_rating",pos.getPopularBookRating());
                intent.putExtra("book_description",pos.getPopularBookDescription());
                intent.putExtra("book_image",pos.getPopularBookImage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //when you have to call the next activity from sn inner class, we set flags
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

            ImageView popularimg;
            TextView populartitle, popularAuthor;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            popularimg = itemView.findViewById(R.id.popBookImg);
            populartitle = itemView.findViewById(R.id.popBookTitle);
            popularAuthor = itemView.findViewById(R.id.popBookAuthor);

        }
    }
}