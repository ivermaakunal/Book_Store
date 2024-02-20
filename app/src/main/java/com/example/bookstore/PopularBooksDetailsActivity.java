package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class PopularBooksDetailsActivity extends AppCompatActivity {

    TextView bookName, bookAuthor ,bookCost, bookRating, bookDescription;
    MaterialButton addtocartbtn;
    ImageView bookImage , backbtn;
    MyDBHelper dbHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_books_details);
        addtocartbtn = findViewById(R.id.addtocartbtn);
        bookName = findViewById(R.id.popular_book_title);
        bookAuthor = findViewById(R.id.popular_book_author);
        bookCost = findViewById(R.id.rupeesprice);
        bookRating = findViewById(R.id.popular_book_rating);
        bookDescription = findViewById(R.id.popular_book_description);
        bookImage = findViewById(R.id.popular_book_image);

        bookName.setText(getIntent().getStringExtra("book_title"));
        bookAuthor.setText(getIntent().getStringExtra("book_author"));
        bookCost.setText(getIntent().getStringExtra("book_cost"));
        bookRating.setText(getIntent().getStringExtra("book_rating"));
        bookDescription.setText(getIntent().getStringExtra("book_description"));
        bookImage.setImageResource(getIntent().getIntExtra("book_image",1));

        dbHelper = new MyDBHelper(this);
        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addDataToCart(getIntent().getStringExtra("book_title"),getIntent().getStringExtra("book_author"),getIntent().getStringExtra("book_cost"),1 , getIntent().getIntExtra("book_image",1));
                Toast.makeText(PopularBooksDetailsActivity.this, "Item added to cart.", Toast.LENGTH_SHORT).show();
            }
        });


        backbtn = findViewById(R.id.backBtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}