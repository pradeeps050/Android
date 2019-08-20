package com.shopping.feature.editprofile.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivityEditProfileBinding;
import com.shopping.feature.profile.ProfileActivity;

public class EditProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_edit_profile);
        ActivityEditProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        toolbar = (Toolbar) binding.blacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        binding.blacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        changeColor(R.color.black);

        ((Toolbar) binding.blacktoolbar).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), resourseColor));

        }
    }
}