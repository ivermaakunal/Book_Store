package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookstore.Models.bookListModel;
import com.example.bookstore.RecyclerAdapters.BookListAdapter;

import java.util.ArrayList;

public class book_category_activity extends AppCompatActivity {

    RecyclerView booklistRecView;
    BookListAdapter bookListAdapter;
    ArrayList<bookListModel> bookListArrayList;
    MyDBHelper dbHelper ;
    SearchView searchView;
    TextView bookListTitle;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category);

        backbtn = findViewById(R.id.backtxtbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dbHelper = new MyDBHelper(book_category_activity.this);
        bookListTitle = findViewById(R.id.bookCategoryHeading);

        booklistRecView = findViewById(R.id.bookCategoryRecyclerView);
        booklistRecView.setLayoutManager(new LinearLayoutManager(book_category_activity.this, LinearLayoutManager.VERTICAL, false));
        bookListArrayList = new ArrayList<>();

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                filterList(text);
                return true;
            }
        });

        //SELF HELP
        if(getIntent().getStringExtra("genre_name").equals("Self-help")) {

            bookListTitle.setText("Self-Help");
            bookListArrayList.add(new bookListModel("Atomic Habits", "James Clear", "4.6", "467", "", R.drawable.atomichabits));
            bookListArrayList.add(new bookListModel("The 5 AM Club", "Robin Sharma", "4.5", "202", "", R.drawable.fiveamclub));
            bookListArrayList.add(new bookListModel("How to win friends and Influence People", "Dale Carnegie", "4.5", "212", "", R.drawable.howtowinfriendsandinfluencepeople));
            bookListArrayList.add(new bookListModel("Stillness is the Key", "Ryan Holiday", "4.6", "350", "", R.drawable.stillnessisthekey));
            bookListArrayList.add(new bookListModel("Thinking Fast and Slow", "Daniel Kahneman", "4.5", "258", "", R.drawable.thinkingfastandslow));
            bookListArrayList.add(new bookListModel("Think like a Monk", "Jay Shetty", "4.6", "310", "", R.drawable.thinklikemonk));

        }

        //FANTASY

        else if(getIntent().getStringExtra("genre_name").equals("Fantasy")) {
            bookListTitle.setText("Fantasy");

            bookListArrayList.add(new bookListModel("Game Of Thrones", "George R.R.Martin", "4.7", "477", "", R.drawable.gameofthrones));
            bookListArrayList.add(new bookListModel("Harry Potter And The Cursed Child", "J.K Rowling", "4.0", "250", "", R.drawable.harrypottercursedchild));
            bookListArrayList.add(new bookListModel("Hunger Games", "Suzanne Collins", "4.6", "309", "", R.drawable.hungergames));
            bookListArrayList.add(new bookListModel("Lord Of The Rings", "J.R.R. Tolkien", "4.5", "789", "", R.drawable.lordoftherings));
            bookListArrayList.add(new bookListModel("Samsara : Enter the valley of Gods", "Saksham Garg", "4.3", "168", "", R.drawable.samsara));
            bookListArrayList.add(new bookListModel("The Hobbit", "J.R.R. Tolkien", "4.6", "305", "", R.drawable.thehobbit));

        }

        else if(getIntent().getStringExtra("genre_name").equals("Mystery")) {
            //MYSTERY
            bookListTitle.setText("Mystery");
            bookListArrayList.add(new bookListModel("Grave Intentions", "R.V. Raman", "4.3", "239", "", R.drawable.graveintentions));
            bookListArrayList.add(new bookListModel("The Maid", "Nita Prose", "3.9", "284", "", R.drawable.themaid));
            bookListArrayList.add(new bookListModel("Theory Of Suspicion", "Kirit Panwala", "4.6", "326", "", R.drawable.theoryofsuspicion));
            bookListArrayList.add(new bookListModel("The Seven Dials Mystery", "Agatha Cristie", "4.2", "258", "", R.drawable.thesevendialsmystery));
            bookListArrayList.add(new bookListModel("The Silent Patient", "Alex Michaelides", "4.5", "205", "", R.drawable.thesilentpatient));
            bookListArrayList.add(new bookListModel("The Unexpected Guest", "Agatha Cristie", "4.4", "225", "", R.drawable.theunexpectedguest));

        }


        //THRILLER

       else if(getIntent().getStringExtra("genre_name").equals("Thriller")) {
            bookListTitle.setText("Thriller");
            bookListArrayList.add(new bookListModel("Dark matter", "Blake Crouch", "4.4", "319", "", R.drawable.darkmatter));
            bookListArrayList.add(new bookListModel("Inferno", "Dan Brown", "4.5", "297", "", R.drawable.inferno));
            bookListArrayList.add(new bookListModel("The Davinci Code", "Dan Brown", "4.5", "310", "", R.drawable.thedavincicode));
            bookListArrayList.add(new bookListModel("The Girl On The Train", "Paula Hawkins", "4.1", "312", "", R.drawable.thegirlonthetrain));
            bookListArrayList.add(new bookListModel("The Housemaid", "Freida McFadden", "4.4", "307", "", R.drawable.thehousemaid));
            bookListArrayList.add(new bookListModel("The Silence Of the Lambs", "Thomas Harris", "4.6", "319", "", R.drawable.thesilenceofthelambs));

        }
        //SCI-FI

       else if(getIntent().getStringExtra("genre_name").equals("Sci-fi")) {
            bookListTitle.setText("Sci-fi");
            bookListArrayList.add(new bookListModel("2001: A Space Odyssey", "Arthur C. Clarke", "4.6", "250", "", R.drawable.aspaceodyssey));
            bookListArrayList.add(new bookListModel("Children Of Time", "Adrian Tchaikovsky", "4.5", "376", "", R.drawable.childrenoftime));
            bookListArrayList.add(new bookListModel("Faranheit 451", "Ray Bradbury", "4.4", "324", "", R.drawable.faranheit451));
            bookListArrayList.add(new bookListModel("Flowers For Algernon", "Daniel Keyes", "4.6", "575", "", R.drawable.flowersforalgernon));
            bookListArrayList.add(new bookListModel("The Martian", "Andy Weir", "4.6", "285", "", R.drawable.themartian));
            bookListArrayList.add(new bookListModel("The Time Machine", "HG Wells", "4.4", "129", "", R.drawable.thetimemachine));
        }

        //CLASSICS

        else if(getIntent().getStringExtra("genre_name").equals("Classics")) {
            bookListTitle.setText("Classics");
            bookListArrayList.add(new bookListModel("As a Man Thinketh", "James Allen", "4.4", "109", "", R.drawable.asamanthinketh));
            bookListArrayList.add(new bookListModel("Crime and Punishment", "Fyodor Dostocvsky", "4.5", "263", "", R.drawable.crimeandpunishment));
            bookListArrayList.add(new bookListModel("Little Women", "Louisa MAY Alcott", "4.5", "190", "", R.drawable.littlewomen));
            bookListArrayList.add(new bookListModel("1984", "George Orwell", "4.6", "250", "", R.drawable.nineteeneightyfour));
            bookListArrayList.add(new bookListModel("Pride and Prejudice", "Jane Austen", "4.5", "230", "", R.drawable.prideandprejudice));
            bookListArrayList.add(new bookListModel("To Kill a Mockingbird", "Harper Lee", "4.4", "189", "", R.drawable.tokillamockingbird));


        }

        bookListAdapter = new BookListAdapter(bookListArrayList, book_category_activity.this);
        booklistRecView.setAdapter(bookListAdapter);

    }

    private void filterList(String text) {
        ArrayList<bookListModel> filteredList = new ArrayList<>();
        for(bookListModel item : bookListArrayList){
            if(item.getBookName().toLowerCase().contains(text) | item.getBookName().toUpperCase().contains(text)) {
                filteredList.add(item);
            }
            bookListAdapter = new BookListAdapter(filteredList, this);
            if(filteredList.isEmpty()){
                filteredList.clear();
                booklistRecView.setAdapter(bookListAdapter);
            }else{
                booklistRecView.setAdapter(bookListAdapter);
            }

        }
    }
}