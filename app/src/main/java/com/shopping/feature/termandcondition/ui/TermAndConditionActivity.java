package com.shopping.feature.termandcondition.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import com.shopping.R;
import com.shopping.databinding.ActivityTermAndConditionBinding;
import com.shopping.feature.mycard.ui.MyCardActivity;


public class TermAndConditionActivity extends AppCompatActivity {
    private Button button;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_term_and_condition);

        ActivityTermAndConditionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_term_and_condition);

        toolbar = (Toolbar) binding.whiteToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Term And Condition");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);

      /*  TermAndConditionViewModel viewModel = new TermAndConditionViewModel(new TermAndConditionModel(getResources().getString(R.string.termandcond)));
        binding.webView3.loadData(viewModel.getMessage(), "text/html; charset=utf-8", "utf-8");
        changeColor(R.color.black);*/


        binding.webView3.loadUrl("http://www.orangeskill.com/privacy-policy");

      button = findViewById(R.id.privacyPolicybutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TermAndConditionActivity.this, MyCardActivity.class);
                startActivity(i);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        changeColor(R.color.black);
    }

    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),resourseColor));
        }
    }
}



























