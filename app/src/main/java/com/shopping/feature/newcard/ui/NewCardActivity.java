package com.shopping.feature.newcard.ui;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.shopping.R;
import com.shopping.databinding.ActivityNewCardBinding;
import com.shopping.feature.editprofile.ui.EditProfileActivity;



public class NewCardActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_new_card);

        ActivityNewCardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_new_card);
        Toolbar toolbar = (Toolbar) binding.blacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Card");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewCardActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });

        changeColor(R.color.black);
    }

    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), resourseColor));
        }
    }
}
