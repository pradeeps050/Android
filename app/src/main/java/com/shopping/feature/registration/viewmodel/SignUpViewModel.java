package com.shopping.feature.registration.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Patterns;

import com.shopping.R;
import com.shopping.feature.registration.SignUpFormState;

public class SignUpViewModel extends ViewModel {

    private Context context;
    private MutableLiveData<SignUpFormState> signUpFormState = new MutableLiveData<>();

    public SignUpViewModel(){}

    public SignUpViewModel(Context context) {
        this.context = context;
    }

    public LiveData<SignUpFormState> getSignUpFormState() {
        return signUpFormState;
    }




    public void createAccount(String email, String password) {

    }

    public void signUpDataChanged(String email, String phone, String password) {
        if (! isUserEmailValid(email)) {
            signUpFormState.setValue(new SignUpFormState(R.string.invalid_username, null, null));
        } else if (!isUserPhoneValid(phone)){
            signUpFormState.setValue(new SignUpFormState(null, R.string.invalid_phone, null));
        } else if (! isPasswordValid(password)) {
            signUpFormState.setValue(new SignUpFormState(null, null, R.string.invalid_password));
        } else {
            signUpFormState.setValue(new SignUpFormState(true));
        }
    }

    private boolean isUserEmailValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    private boolean isUserPhoneValid(String phone) {
        if (phone == null) {
            return false;
        }
        if (phone.contains("(0/91)?[7-9][0-9]{9}")){
            return Patterns.PHONE.matcher(phone).matches();
        } else {
            return !phone.trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
