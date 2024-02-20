package com.example.bookstore.RecyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Models.bookListModel;
import com.example.bookstore.MyDBHelper;
import com.example.bookstore.R;
import com.example.bookstore.book_category_activity;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {

    ArrayList<bookListModel> bookArrayList ;
    Context context;

    public BookListAdapter(ArrayList<bookListModel> bookArrayList, Context context) {
        this.bookArrayList = bookArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_layout, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            bookListModel temp = bookArrayList.get(position);
            holder.bookname.setText(bookArrayList.get(position).getBookName());
            holder.bookauthor.setText(bookArrayList.get(position).getBookAuthorName());
            holder.bookrating.setText(bookArrayList.get(position).getBookRating());
            holder.bookimage.setImageResource(bookArrayList.get(position).getBookImage());
            holder.bookcost.setText(bookArrayList.get(position).getBookCost());


            holder.addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyDBHelper dbHelper = new MyDBHelper(context);
                    dbHelper.addDataToCart(temp.getBookName(),temp.getBookAuthorName(),temp.getBookCost(),1,temp.getBookImage());
                    Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();
                }
            });


    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookname , bookauthor, bookrating, bookcost;
        ImageView bookimage;
        Button addtocart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.BookTitle);
            bookauthor = itemView.findViewById(R.id.BookAuthor);
            bookcost = itemView.findViewById(R.id.rupeesValue);
            bookrating = itemView.findViewById(R.id.ratingValue);
            bookimage=itemView.findViewById(R.id.BookImage);
            addtocart = itemView.findViewById(R.id.addTocartButton);
        }

    }
}
