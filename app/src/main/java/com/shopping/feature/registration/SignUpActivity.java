package com.shopping.feature.registration;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shopping.R;
import com.shopping.feature.login.ui.login.LoginActivity;
import com.shopping.feature.registration.viewmodel.SignUpViewModel;
import com.shopping.framework.logger.Logger;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = SignUpActivity.class.getSimpleName();

    private Button signUpButton;
    private TextView signIn;
    private EditText userEmailEdtTxt;
    private EditText userPhoneEdtTxt;
    private EditText userPasswordEdtTxt;
    private EditText userConfPasswordEdtTxt;
    private ProgressBar progressBar;

    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        viewModel = ViewModelProviders.of(SignUpActivity.this).get(SignUpViewModel.class);
        viewModel.getSignUpFormState().observe(this, new Observer<SignUpFormState>() {
            @Override
            public void onChanged(@Nullable SignUpFormState signUpFormState) {
                if (signUpFormState == null) {
                    return;
                }
                signUpButton.setEnabled(signUpFormState.isDataValid());
                if (signUpFormState.getEmailError() != null) {
                    userEmailEdtTxt.setError(getString(signUpFormState.getEmailError()));
                }
                if (signUpFormState.getPhoneError() != null) {
                    userPhoneEdtTxt.setError(getString(signUpFormState.getPhoneError()));
                }
                if (signUpFormState.getPasswordError() != null) {
                    userPasswordEdtTxt.setError(getString(signUpFormState.getPasswordError()));
                }
                if (signUpFormState.getConfPasswordError() != null) {
                    userConfPasswordEdtTxt.setError(getString(signUpFormState.getConfPasswordError()));
                }
            }
        });

        viewModel.getSignUpResult().observe(this, new Observer<SignUpResult>() {
            @Override
            public void onChanged(@Nullable SignUpResult signUpResult) {

            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setEnabled(true);
                progressBar.setVisibility(View.VISIBLE);

                /*viewModel.createAccount(userEmailEdtTxt.getText().toString(), userPhoneEdtTxt.getText().toString()
                ,userPasswordEdtTxt.getText().toString());*/
                //startActivity(new Intent(SignUpActivity.this, OTPActivity.class));

            }
        });
    }

    private void init() {
        userEmailEdtTxt = findViewById(R.id.email_edt_txt);
        userPhoneEdtTxt = findViewById(R.id.phone_edt_txt);
        userPasswordEdtTxt = findViewById(R.id.password_edt_txt);
        userConfPasswordEdtTxt= findViewById(R.id.confir_password_edt_txt);
        signUpButton = findViewById(R.id.btn_signup);
        signIn = findViewById(R.id.signIn_txt);
        progressBar = findViewById(R.id.signin_progressbar);
        signUpButton.setEnabled(false);
        userEmailEdtTxt.addTextChangedListener(textWatcher);
        userPhoneEdtTxt.addTextChangedListener(textWatcher);
        userPasswordEdtTxt.addTextChangedListener(textWatcher);
        userConfPasswordEdtTxt.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Log.d(TAG, ">> before text change " + charSequence.toString());

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Log.d(TAG, ">> on text change " + charSequence.toString());

        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Log.d(TAG, ">> after text change " + editable.toString());
            viewModel.signUpDataChanged(userEmailEdtTxt.getText().toString(), userPhoneEdtTxt.getText().toString(), userPasswordEdtTxt.getText().toString(),
                    userConfPasswordEdtTxt.getText().toString());
        }
    };

}
