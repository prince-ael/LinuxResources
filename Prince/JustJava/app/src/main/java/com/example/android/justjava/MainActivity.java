package com.example.android.justjava;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView totalTV;
    private TextView priceTV;
    private TextView qntyTV;

    private Button plus;
    private Button minus;
    private Button order;

    private CheckBox whippedCream;
    private CheckBox chocolate;

    private EditText nameET;

    private int qnty;
    private int price;
    private boolean chocolateAdded;
    private boolean creamAdded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initActions();

    }

    private void initViews(){

        totalTV = (TextView) findViewById(R.id.total_text_view);
        priceTV = (TextView) findViewById(R.id.price_text_view);
        qntyTV = (TextView) findViewById(R.id.quantity);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        order = (Button) findViewById(R.id.order);

        whippedCream = (CheckBox) findViewById(R.id.whipped_chk);
        chocolate = (CheckBox) findViewById(R.id.choco_chk);

        nameET = (EditText) findViewById(R.id.customer_name);

    }

    private void initActions(){

        qntyTV.setText("0");
        qnty = 0;
        price = 5;

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                qnty = (qnty < 100)? (qnty + 1) : qnty;
                qntyTV.setText(""+qnty);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                qnty = (qnty <= 0)?  qnty: (qnty - 1);
                qntyTV.setText(""+qnty);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String customerName = nameET.getText().toString();
                String topping = "";

                chocolateAdded = chocolate.isChecked();
                creamAdded = whippedCream.isChecked();

                if(creamAdded){

                    topping += (topping.length() > 0)?"\nWhipped Cream Added":"Whipped Cream Added";
                }

                if(chocolateAdded){

                    topping += (topping.length() > 0)?"\nChocolate Added":"Chocolate Added";
                }

                String orderSummary = String.format("Name   : %s\n%s\nQuntity : %d\nTotal    : $%d\n%s",customerName ,topping, qnty , calculatePrice() , "Thank You");

                totalTV.setText(orderSummary);

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT , "Coffee Order");
                intent.putExtra(Intent.EXTRA_TEXT , orderSummary);

                try{

                    startActivity(intent);

                }catch (ActivityNotFoundException anf){

                    Log.d("Exception ",anf.getMessage());
                }

            }
        });


    }

    private int calculatePrice(){

        price = 5;

        price += (creamAdded)? 1 : 0;
        price += (chocolateAdded)? 2 : 0;

        return (price * qnty);
    }
}
