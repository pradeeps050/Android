package com.shopping.feature.forgatpassword;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shopping.R;
import com.shopping.feature.changepassword.ChangePasswordActivity;
import com.shopping.feature.forgatpassword.data.ForgotPassword;
import com.shopping.feature.forgatpassword.data.ForgotPasswordDataSource;
import com.shopping.feature.registration.OTPActivity;
import com.shopping.framework.constantsValues.ConstantValues;

public class ForgotPasswordActivity extends AppCompatActivity {
    private static final String TAG = "ForgotPasswordActivity";

    private Toolbar toolbar;
    private Button submit;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        submit = findViewById(R.id.submit_btn);
        phone = findViewById(R.id.email_edt_txt);
        initToolbar();

        ForgotPasswordViewModel viewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel.class);

        viewModel.getLiveData().observe(this, new Observer<ForgotPassword>() {
            @Override
            public void onChanged(@Nullable ForgotPassword forgotPassword) {
                if(forgotPassword == null) {
                    Toast.makeText(ForgotPasswordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (! forgotPassword.getVerified()) {
                    Intent intent = new Intent(ForgotPasswordActivity.this, OTPActivity.class);
                    intent.putExtra(ConstantValues.CHANGE_PASSWORD, ConstantValues.CHANGE_PASSWORD);
                    intent.putExtra(ConstantValues.USER_ID, forgotPassword.getUserId());
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Verified", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString() != null) {
                    viewModel.sendOtp(phone.getText().toString());
                    /*try {
                        int mobile = Integer.parseInt(phone.getText().toString());
                        viewModel.sendOtp(mobile);
                    } catch (NumberFormatException e) {
                        Log.e(TAG, ">> " + e.getMessage());
                    }*/


                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Enter phone", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar_forgotpassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_icon_back_b);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
