package com.shopping.feature.registration.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;

import com.shopping.R;
import com.shopping.feature.registration.SignUpFormState;
import com.shopping.feature.registration.SignUpResult;
import com.shopping.framework.network.RestApiBuilder;
import com.shopping.framework.network.RestApiServices;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpViewModel extends ViewModel {
    private static final String TAG = "SignUpViewModel";

    private Context context;
    private MutableLiveData<SignUpFormState> signUpFormState = new MutableLiveData<>();
    private MutableLiveData<SignUpResult> signUpResult = new MutableLiveData<>();

    public SignUpViewModel(){}

    public SignUpViewModel(Context context) {
        this.context = context;
    }

    public LiveData<SignUpFormState> getSignUpFormState() {
        return signUpFormState;
    }

    public LiveData<SignUpResult> getSignUpResult() {
        return signUpResult;
    }

    public void createAccount(String email, String phone, String password) {
        //Call rest api
        Log.d(TAG, ">> create account ");



    }

    public void signUpDataChanged(String email, String phone, String password, String confPassword) {
        if (! isUserEmailValid(email)) {
            signUpFormState.setValue(new SignUpFormState(R.string.invalid_email, null, null, null));
        } else if (!isUserPhoneValid(phone)){
            signUpFormState.setValue(new SignUpFormState(null, R.string.invalid_phone, null, null));
        } else if (! isPasswordValid(password)) {
            signUpFormState.setValue(new SignUpFormState(null, null, R.string.invalid_password, null));
        } else if (! isPasswordSame(password, confPassword)){
            signUpFormState.setValue(new SignUpFormState(null, null, null, R.string.password_match));
        } else {
            signUpFormState.setValue(new SignUpFormState(true));
        }
    }

    private boolean isUserEmailValid(String username) {
        if (username == null) {
            return false;
        }
        boolean f = Patterns.EMAIL_ADDRESS.matcher(username).matches();
        //Log.d(TAG, ">> " + String.valueOf("email " + f));
        return f;

    }

    private boolean isUserPhoneValid(String phone) {
        if (phone == null) {
            return false;
        }
        boolean check = false;
        String expression = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
        Pattern p = Pattern.compile(expression);
        Matcher matcher = p.matcher(phone);
        if (matcher.matches()) {
            check = true;
        }
        //Log.d(TAG, ">> phone " + String.valueOf(check));
        return check;
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 6;
    }

    private boolean isPasswordSame(String password, String confPassword) {
        if (! (password != null && confPassword != null)) {
            return false;
        }
        return password.equals(confPassword);
    }
}
