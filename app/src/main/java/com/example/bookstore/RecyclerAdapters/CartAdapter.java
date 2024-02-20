package com.example.bookstore.RecyclerAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Models.cartModel;
import com.example.bookstore.MyDBHelper;
import com.example.bookstore.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    ArrayList<cartModel> cartList;
    Context context;
    TextView totalamount;

    public CartAdapter(ArrayList<cartModel> cartList, Context context , TextView totalamount) {
        this.cartList = cartList;
        this.context = context;
        this.totalamount = totalamount;
        updateamount();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

            cartModel temp = cartList.get(position);
            holder.bookName.setText(cartList.get(position).getBookTitle());
            holder.bookAuthor.setText(cartList.get(position).getBookAuthor());
            holder.bookCost.setText(cartList.get(position).getBookCost());
            holder.bookImg.setImageResource(cartList.get(position).getBookImage());
            holder.bookQuantity.setText(String.valueOf(cartList.get(position).getBookQuantity()));


        holder.removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper dbHelper = new MyDBHelper(context);
                dbHelper.removeFromCart(temp.getBookTitle());
                cartList = dbHelper.fetchCart();
                notifyItemRemoved(position);
                updateamount();

            }
        });

        holder.increasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = cartList.get(position).getBookQuantity();
                count++;
                cartList.get(position).setBookQuantity(count);
                notifyDataSetChanged();
                updateamount();

            }
        });

        holder.decreasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = cartList.get(position).getBookQuantity();
                if(count>1){
                    count--;}
                cartList.get(position).setBookQuantity(count);
                notifyDataSetChanged();
                updateamount();

            }
        });

    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView bookImg;
        TextView bookName, bookAuthor,bookCost, bookQuantity;
        ImageButton increasebtn, decreasebtn , removebtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg = itemView.findViewById(R.id.BookImage);
            bookName = itemView.findViewById(R.id.BookTitle);
            bookAuthor = itemView.findViewById(R.id.BookAuthor);
            bookCost = itemView.findViewById(R.id.rupeesValue);
            bookQuantity = itemView.findViewById(R.id.quantityTxt);
            increasebtn = itemView.findViewById(R.id.addBtn);
            decreasebtn = itemView.findViewById(R.id.subtractBtn);
            removebtn = itemView.findViewById(R.id.removebtn);
        }
    }

    public int updateamount(){
        int sum = 0, i;
        for(i=0; i<cartList.size();i++){
            sum = sum + Integer.parseInt(cartList.get(i).getBookCost()) * cartList.get(i).getBookQuantity();
        }

        totalamount.setText("₹ "+sum);

        return  sum;
    }


}
