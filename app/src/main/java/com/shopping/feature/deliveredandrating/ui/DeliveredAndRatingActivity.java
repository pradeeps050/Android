package com.shopping.feature.deliveredandrating.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.shopping.R;
import com.shopping.databinding.ActivityDeliveredAndRatingBinding;
import com.shopping.feature.newcard.ui.NewCardActivity;
import com.shopping.feature.privacypolicy.ui.PrivacyPolicyActivity;

public class DeliveredAndRatingActivity extends AppCompatActivity {
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_delivered_and_rating);
        ActivityDeliveredAndRatingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_delivered_and_rating);
        toolbar = (Toolbar) binding.shadowLightBlackToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
         binding.shadowLightBlackToolbar.setNavigationIcon(R.drawable.ic_icon_back_b);

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DeliveredAndRatingActivity.this, PrivacyPolicyActivity.class);
            }
        });

      //  requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        //getSupportActionBar().hide(); // hide the title bar

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(DeliveredAndRatingActivity.this, NewCardActivity.class);
               startActivity(i);
            }
        });

        changeColor(R.color.shadow_light_black_background);
    }

    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), resourseColor));
        }

    }
}
