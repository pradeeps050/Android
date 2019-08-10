package com.shopping.feature.changepassword;

import android.annotation.TargetApi;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shopping.R;
import com.shopping.feature.login.ui.login.LoginActivity;
import com.shopping.framework.constantsValues.ConstantValues;
import com.shopping.framework.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG = "ChangePasswordActivity";
    private Toolbar toolbar;
    private EditText passwordEditText;
    private EditText confPasswordEditText;
    private Button submitBtn;
    private ProgressBar progressBar;
    private ChangePasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        toolbar = findViewById(R.id.toolbar_changePassword);
        setToolbar();
        changeStatusBarColor();
        init();
        viewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);
        viewModel.getMutableData().observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(@Nullable ResponseBody responseBody) {
                progressBar.setVisibility(View.GONE);
                if (responseBody == null) {
                    Toast.makeText(ChangePasswordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (responseBody != null) {
                    try {
                        Logger.d(TAG, "Change Password >> " + responseBody.string());
                        startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
                        finish();
                    } catch (IOException e) {
                        Logger.e(TAG, e.getMessage());
                    }
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = getIntent().getIntExtra(ConstantValues.USER_ID, 0);
                String password = passwordEditText.getText().toString();
                String confPassword = confPasswordEditText.getText().toString();
                if (password != null && confPassword != null) {
                    if (password.length() >= 8 && confPassword.length() >=8) {
                        if (password.equals(confPassword.toString())) {
                            viewModel.updatePassword(userid, password);
                            progressBar.setVisibility(View.VISIBLE);
                        } else {
                            passwordEditText.setError("Password must be same");
                        }
                    } else {
                        passwordEditText.setError("Minimum 8 charcter required");
                    }
                } else {
                    passwordEditText.setError("Password required");
                }

            }
        });
    }

    public void setToolbar() {
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.chg_password));
        this.toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        this.toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @TargetApi(21)
    public void changeStatusBarColor() {
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
    }
     private void init() {
        passwordEditText = findViewById(R.id.new_password_edt_txt);
        confPasswordEditText = findViewById(R.id.cnf_password_edt_txt);
        submitBtn = findViewById(R.id.change_password_btn);
        progressBar = findViewById(R.id.chg_pass_progressBar);
     }
}
