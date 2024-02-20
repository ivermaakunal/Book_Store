package com.example.bookstore;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstore.Models.cartModel;
import com.example.bookstore.RecyclerAdapters.CartAdapter;
import com.google.android.material.button.MaterialButton;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {

    RecyclerView cartRecView;
    CartAdapter cartAdapter;
    TextView totalAmounttxt;
    ArrayList<cartModel> cartArrayList;
    MyDBHelper dbHelper;
    ImageView backbtn;
    MaterialButton checkoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Checkout.preload(getApplicationContext());
        cartRecView = findViewById(R.id.cartRecyclerView);
        totalAmounttxt = findViewById(R.id.totalamounttxt);

        dbHelper = new MyDBHelper(this);
        cartRecView = findViewById(R.id.cartRecyclerView);

        cartRecView.setLayoutManager(new LinearLayoutManager(CartActivity.this,LinearLayoutManager.VERTICAL,false));

        cartArrayList = new ArrayList<>();

        cartArrayList = dbHelper.fetchCart();
        cartAdapter = new CartAdapter(cartArrayList,CartActivity.this, totalAmounttxt);
        cartRecView.setAdapter(cartAdapter);
        cartAdapter.notifyItemInserted(cartArrayList.size()-1);


        backbtn = findViewById(R.id.backtxtbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        checkoutbtn = findViewById(R.id.Checkout_button);
        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makepayment();
            }
        });

    }

    private void makepayment() {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_rfEe5jn0vgcope");

        checkout.setImage(R.drawable.logo);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();
            int cost = cartAdapter.updateamount()*100;
            options.put("name", "BookWorm");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
//            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", cost);//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","6203121647");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        try {
            Dialog dialog = new Dialog(CartActivity.this);
            dialog.setContentView(R.layout.ordered_confirmed);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dbHelper.deleteAll();
            cartArrayList = dbHelper.fetchCart();
            cartAdapter = new CartAdapter(cartArrayList, CartActivity.this, totalAmounttxt);
            cartRecView.setAdapter(cartAdapter);
            dialog.show();
        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
    }
}

