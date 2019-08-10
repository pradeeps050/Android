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
import android.widget.Toast;

import com.shopping.R;
import com.shopping.databinding.ActivityOtpBinding;
import com.shopping.feature.changepassword.ChangePasswordActivity;
import com.shopping.feature.home.HomeActivity;
import com.shopping.feature.registration.model.OTP;
import com.shopping.feature.registration.model.RequestOtpResponse;
import com.shopping.feature.registration.model.ValidateOtp;
import com.shopping.feature.registration.viewmodel.OTPViewModel;
import com.shopping.framework.constantsValues.ConstantValues;
import com.shopping.framework.logger.Logger;

import java.io.IOException;

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
    private Intent intent;
    private boolean callVerify = false;
    private RequestOtpResponse reqOtpResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp);
        intent = getIntent();
        initToolbar();
        init();
        editText1 = binding.edit1;
        editText2 = binding.edit2;
        editText3 = binding.edit3;
        editText4 = binding.edit4;
        editText5 = binding.edit5;
        otpViewModel = ViewModelProviders.of(this).get(OTPViewModel.class);

        binding.resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpViewModel.resendOtp(userID);
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
                String flag = intent.getStringExtra(ConstantValues.FLAG);
                if (ConstantValues.LOGIN.equals(flag)) {
                    //call verify otp api. and request otp
                    if (callVerify) {
                        if (otp.length() == 5) {
                            int userid = reqOtpResponse.getUserId();
                            Logger.i(TAG, ">> calling verify otp api");
                            otpViewModel.verifyOtp(userid, Integer.parseInt(otp));
                            callVerify = false;
                        }
                    } else {
                        Logger.i(TAG, ">> calling request otp api");
                        int userId = intent.getIntExtra(ConstantValues.USER_ID, 0);
                        otpViewModel.requestOtp(userId);
                        binding.progressBar.setVisibility(View.VISIBLE);
                        callVerify = false;
                    }

                } else if (ConstantValues.FORGOT_PASSWORD.equals(flag)) {
                    //call forget password api.
                    if (otp.length() == 5) {
                        int userId = intent.getIntExtra(ConstantValues.USER_ID, 0);
                        Logger.i(TAG, ">> calling forget password otp api");
                        otpViewModel.validateOtp(userId, otp);
                        binding.progressBar.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

        otpViewModel.getOTPRequestData().observe(this, new Observer<RequestOtpResponse>() {
            @Override
            public void onChanged(@Nullable RequestOtpResponse requestOtpResponse) {
                Logger.d(TAG, ">> request otp response ");
                binding.progressBar.setVisibility(View.GONE);
                if (requestOtpResponse == null) {
                    callVerify = false;
                    Toast.makeText(OTPActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (! requestOtpResponse.getVerified()) {
                    callVerify = true;
                    binding.nextBtn.setText("Verify");
                    reqOtpResponse = requestOtpResponse;
                    Logger.i(TAG, ">> RQOTP RES " + reqOtpResponse.toString());
                }
            }
        });

        otpViewModel.getMutable().observe(this, new Observer<OTP>() {
            @Override
            public void onChanged(@Nullable OTP otp) {
                binding.progressBar.setVisibility(View.GONE);
                if (otp == null) {
                    Toast.makeText(OTPActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    callVerify = true;
                    return;
                }
                if (otp.getVerified()) {
                    callVerify = false;
                    startActivity(new Intent(OTPActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(OTPActivity.this, "User is not verified", Toast.LENGTH_SHORT).show();
                }
            }
        });

        otpViewModel.getResponseLiveData().observe(this, new Observer<ValidateOtp>() {
            @Override
            public void onChanged(@Nullable ValidateOtp validateOtp) {
                binding.progressBar.setVisibility(View.GONE);
                if (validateOtp == null) {
                    Toast.makeText(OTPActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (validateOtp.getVerified()) {
                    Intent intent = new Intent(OTPActivity.this, ChangePasswordActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(ConstantValues.USER_ID, validateOtp.getUserId());
                    startActivity(intent);
                    finish();
                }
            }
        });
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

    private void init() {
        //Set ui according to intent
        String flag = intent.getStringExtra(ConstantValues.FLAG);
        if (ConstantValues.LOGIN.equals(flag)) {
            Logger.d(TAG, ">> setting login screen");
            binding.nextBtn.setText(R.string.request_opt);
        } else if (ConstantValues.FORGOT_PASSWORD.equals(flag)) {
            Logger.d(TAG, ">> setting forgot password screen");
            binding.nextBtn.setText(R.string.submit);
        }
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
