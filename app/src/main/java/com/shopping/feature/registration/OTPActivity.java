package com.shopping.feature.registration;

import android.annotation.TargetApi;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.shopping.R;
import com.shopping.databinding.ActivityOtpBinding;
import com.shopping.feature.registration.viewmodel.OTPViewModel;
import com.shopping.framework.constantsValues.ConstantValues;

import okhttp3.ResponseBody;

public class OTPActivity extends AppCompatActivity {
    private static final String TAG = "OTPActivity";

    private Toolbar toolbar;
    private ActivityOtpBinding binding;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private ProgressBar progressBar;
    private OTPViewModel otpViewModel;
    private  int userID;
    private String changePassword;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        changePassword = intent.getStringExtra(ConstantValues.CHANGE_PASSWORD);
        if (ConstantValues.CHANGE_PASSWORD.equals(changePassword)) {
            userID = intent.getIntExtra(ConstantValues.USER_ID, 0);
        }
        userID = getIntent().getIntExtra("UserID", 0);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp);
        editText1 = binding.edit1;
        editText2 = binding.edit2;
        editText3 = binding.edit3;
        editText4 = binding.edit4;
        editText5 = binding.edit5;
        progressBar = binding.progressBar;
        initToolbar();
        otpViewModel = ViewModelProviders.of(this).get(OTPViewModel.class);
        otpViewModel.getMutable().observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(@Nullable ResponseBody responseBody) {

            }
        });
        binding.resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpViewModel.requestOtp(userID);
            }
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(26)
            @Override
            public void onClick(View view) {
                String ed1 = editText1.getText().toString();
                String ed2 = editText2.getText().toString();
                String ed3 = editText3.getText().toString();
                String ed4 = editText4.getText().toString();
                String ed5 = editText5.getText().toString();
                String otp = new String(ed1+ed2+ed3+ed4+ed5);

                //call here according to intent.
                if (ConstantValues.CHANGE_PASSWORD.equals(changePassword)) {
                    Log.d(TAG, " >> Change Password");
                    otpViewModel.validateOtp(userID, otp);
                }
                Log.i(TAG, ">> OTP>> and UserID >> " + otp +" " + String.valueOf(userID));
                otpViewModel.verifyOtp(userID, Integer.parseInt(otp));
                progressBar.setVisibility(View.VISIBLE);
            }
        });
       /* binding.edit1.addTextChangedListener(new OTPTextWatcher(binding.edit1));
        binding.edit2.addTextChangedListener(new OTPTextWatcher(binding.edit2));
        binding.edit3.addTextChangedListener(new OTPTextWatcher(binding.edit3));
        binding.edit4.addTextChangedListener(new OTPTextWatcher(binding.edit4));*/
    }

    private void initToolbar() {
        toolbar = binding.otpToolbar;
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



    /*class OTPTextWatcher implements TextWatcher {
        View view;

        OTPTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();

            switch (view.getId()) {
                case R.id.edit1:
                    if(text.length()==1) {
                        editText2.requestFocus();

                    }

                case R.id.edit2:
                    editText3.requestFocus();
                case R.id.edit3:
                    editText4.requestFocus();
            }

        }
    }*/


}
