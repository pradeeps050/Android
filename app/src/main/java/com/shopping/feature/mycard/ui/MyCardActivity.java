package com.shopping.feature.mycard.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.R;
import com.shopping.databinding.ActivityMyCardBinding;
import com.shopping.feature.needhelp.ui.NeedHelpActivity;

public class MyCardActivity extends AppCompatActivity {
private Toolbar toolbar;
private ImageView penImage;
private TextView akshayText;
    private static final String TAG="  On click event is ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);


        ActivityMyCardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_my_card);
        //String cardNumber ="<font color=#b7b7b7 >Card  Number</font>   <font color=#000000>XXXX XXXX 4556</font>";
        //binding.cardnumTxt.setText(Html.fromHtml(cardNumber));

      /*  String cardNumber2 ="<font color=#b7b7b7> Card Number</font>    <font color=#000000>XXXX XXXXX 5676</font>";
        binding.cardnum2Txt.setText(Html.fromHtml(cardNumber));

        String expiryDate1 ="<font color=#b7b7b7 >Expiry :</font> <font color=#000000 >2/12</font>";
        binding.expiryTxt.setText(Html.fromHtml(expiryDate1));

        String expiryDate2 ="<font color=#b7b7b7> Expiry :</font><font color=#000000> 3/12 </font>";
        binding.expiryTxt2.setText(Html.fromHtml(expiryDate2));*/

        akshayText=binding.akshayTxt;
        penImage= binding.penImg;

        penImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG ,">>>"+ akshayText.getText().toString());
                /*Intent i =new Intent(MyCardActivity .this, NeedHelpActivity.class);
                startActivity(i);*/
            }
        });
        toolbar=(Toolbar)binding.blackToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Cards");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        changeColor(R.color.black);
        binding.addNewCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MyCardActivity.this,NeedHelpActivity.class);
                startActivity(i);
            }
        });
    }

    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),resourseColor));
        }
    }

    }

