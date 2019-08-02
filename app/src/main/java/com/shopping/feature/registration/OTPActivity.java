package com.shopping.feature.registration;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.shopping.R;
import com.shopping.databinding.ActivityOtpBinding;

public class OTPActivity extends AppCompatActivity {
    private static final String TAG = "OTPActivity";

    private Toolbar toolbar;
    private ActivityOtpBinding binding;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp);
        editText1 = binding.edit1;
        editText2 = binding.edit2;
        editText3 = binding.edit3;
        editText4 = binding.edit4;

        initToolbar();

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.edit1.addTextChangedListener(new OTPTextWatcher(binding.edit1));
        binding.edit2.addTextChangedListener(new OTPTextWatcher(binding.edit2));
        binding.edit3.addTextChangedListener(new OTPTextWatcher(binding.edit3));
        binding.edit4.addTextChangedListener(new OTPTextWatcher(binding.edit4));
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



    class OTPTextWatcher implements TextWatcher {
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
    }


}
