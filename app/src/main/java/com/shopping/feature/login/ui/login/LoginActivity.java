package com.shopping.feature.login.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.R;
import com.shopping.feature.forgatpassword.ForgotPasswordActivity;
import com.shopping.feature.home.HomeActivity;
import com.shopping.feature.login.data.model.User;
import com.shopping.feature.registration.OTPActivity;
import com.shopping.feature.registration.SignUpActivity;
import com.shopping.feature.termandcondition.ui.TermAndConditionActivity;
import com.shopping.feature.registration.model.ResponseFail;
import com.shopping.framework.constantsValues.ConstantValues;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private LoginViewModel loginViewModel;
    private TextView createAccount, forgotPassword;
    //private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        loginButton.setEnabled(false);
        createAccount = findViewById(R.id.signup);
        forgotPassword = findViewById(R.id.forgot_password);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getUserMutableLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                loadingProgressBar.setVisibility(View.GONE);
                if (user == null) {
                    return;
                }
                if (user.getIsVerified()) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;

                } else {
                    Intent intent = new Intent(LoginActivity.this, OTPActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(ConstantValues.FLAG, ConstantValues.LOGIN);
                    intent.putExtra(ConstantValues.USER_ID, user.getUserId().intValue());
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        });

        loginViewModel.getFailResponse().observe(this, new Observer<ResponseFail>() {
            @Override
            public void onChanged(@Nullable ResponseFail responseFail) {
                loadingProgressBar.setVisibility(View.INVISIBLE);
                if (responseFail != null) {
                    Toast.makeText(LoginActivity.this, responseFail.message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.d(TAG , ">>>> onEditorAction");
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                /*ShoppingRoomDatabase db = ShoppingRoomDatabase.getInstance(LoginActivity.this);
                UserEntity entity = db.userDao().getUserDetail();
                if (entity != null) {
                    Log.i(TAG, " >> QUERY >> " + entity.toString());
                } else {
                    Log.i(TAG, " >> QUERY is NULL ");
                }*/
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, TermAndConditionActivity.class));

                /*Log.d(TAG, ">> forgot >> ");
                ShoppingRoomDatabase db = ShoppingRoomDatabase.getInstance(LoginActivity.this);
                UserEntity userEntity = new UserEntity();
                userEntity.setMobile(22);
                userEntity.setFullName("Pradeep Sharma");
                userEntity.setEmail("ps@gmail.com");
                userEntity.setUserID(2);
                db.userDao().insetUserDetails(userEntity);*/
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));

            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        startActivity(new Intent(LoginActivity.this, OTPActivity.class));
        finish();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
