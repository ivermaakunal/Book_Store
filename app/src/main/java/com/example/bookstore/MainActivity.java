package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookstore.Models.genreModel;
import com.example.bookstore.Models.popularBooksModel;
import com.example.bookstore.RecyclerAdapters.GenreAdapter;
import com.example.bookstore.RecyclerAdapters.PopularBookAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView nametxt;
    RecyclerView genreRecView, popularBooksRecView;
    ImageView gotocart, profilepic ;
    ArrayList<genreModel> genreList;
    ArrayList<popularBooksModel> popularList;
    GenreAdapter genreAdapter;
    PopularBookAdapter popularBookAdapter;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pref = getSharedPreferences("login",MODE_PRIVATE);
        nametxt = findViewById(R.id.nametxt);
        nametxt.setText(pref.getString("NAME",""));


        genreRecView = findViewById(R.id.genreRecyclerView);
        genreRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        genreList = new ArrayList<>();
        genreList.add(new genreModel(R.drawable.bw_selfhelp,"Self-help"));
        genreList.add(new genreModel(R.drawable.bw_fantasy,"Fantasy"));
        genreList.add(new genreModel(R.drawable.bw_mystery,"Mystery"));
        genreList.add(new genreModel(R.drawable.bw_thriller,"Thriller"));
        genreList.add(new genreModel(R.drawable.bw_sciencefiction,"Sci-fi"));
        genreList.add(new genreModel(R.drawable.bw_classics,"Classics"));

        genreAdapter = new GenreAdapter(genreList,this);
        genreRecView.setAdapter(genreAdapter);


        popularBooksRecView = findViewById(R.id.popularRecyclerView);
        popularBooksRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, false));
        popularList = new ArrayList<>();
        popularList.add(new popularBooksModel(R.drawable.themonkwhosoldhisferrari,
                "The Monk Who Sold His Ferrari","Robin Sharma","4.4","259","The Monk Who Sold His Ferrari\" is a transformative self-help book by Robin Sharma, recounting a lawyer\\'s journey from materialism to spiritual awakening in the Himalayas. It imparts life lessons for personal growth and fulfillment."));
        popularList.add(new popularBooksModel(R.drawable.richdadpoordad,
                "Rich Dad Poor Dad","Robert Kiyosaki","4.6","299","\"Rich Dad Poor Dad\" by Robert Kiyosaki explores financial wisdom through the contrasting approaches of his 'rich dad' and 'poor dad,' offering valuable insights on money management and financial independence."));
        popularList.add(new popularBooksModel(R.drawable.twentyonelessons,
                "21 Lessons for 21st Century","Yuval Noah Harari","4.5","329","\"21 Lessons for the 21st Century\" by Yuval Noah Harari offers insightful reflections on contemporary global challenges, addressing topics such as technology, politics, and culture, providing essential guidance for our rapidly changing world."));
        popularList.add(new popularBooksModel(R.drawable.theoryofeverything,
                "The Theory of Everything","Stephen Hawking","4.6","199","\"The Theory of Everything\" is a captivating exploration of physicist Stephen Hawking's life and groundbreaking work, offering a glimpse into the mysteries of the universe through a personal and scientific lens."));
        popularList.add(new popularBooksModel(R.drawable.thealchemist,
                "The Alchemist","Paulo Coelho","4.5","227","\"The Alchemist\" by Paulo Coelho is a captivating novel that follows the journey of Santiago, a shepherd boy, as he embarks on a quest to discover his personal legend and the meaning of life."));
        popularList.add(new popularBooksModel(R.drawable.manssearchformeaning,
                "Man's Search For Meaning","Victor E. Frankl","4.3","289","\"Man's Search for Meaning\" by Viktor E. Frankl is a profound exploration of the human spirit, recounting the author's experiences in a concentration camp and his philosophy on finding purpose in life."));

        popularBookAdapter = new PopularBookAdapter(popularList,MainActivity.this);
        popularBooksRecView.setAdapter(popularBookAdapter);

        gotocart = findViewById(R.id.cart_image);
        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        profilepic = findViewById(R.id.profilepic);
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog logoutdialog = new Dialog(MainActivity.this);
                logoutdialog.setContentView(R.layout.dialogboxlayout);
                logoutdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView personEmail = logoutdialog.findViewById(R.id.useremailid);
                Button logoutbtn = logoutdialog.findViewById(R.id.logoutbtn);
                personEmail.setText(pref.getString("EMAIL",""));   // setting the email name of logged in user

                logoutbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("flag",false);
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });

                logoutdialog.show();
            }
        });
    }
}