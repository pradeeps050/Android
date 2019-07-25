package com.shopping.feature.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shopping.R;
import com.shopping.feature.login.ui.login.LoginActivity;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    private Button signUpButton;
    private TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpButton = findViewById(R.id.btn_signup);
        signIn = findViewById(R.id.signIn_txt);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, OTPActivity.class));
            }
        });
    }
}
