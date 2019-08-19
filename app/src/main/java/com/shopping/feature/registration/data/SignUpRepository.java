package com.shopping.feature.registration.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shopping.feature.registration.model.ResponseFail;
import com.shopping.feature.registration.model.SignUpResponse;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository {
    private static final String TAG = SignUpRepository.class.getSimpleName();

    private static volatile SignUpRepository instance;

    public static SignUpRepository getInstance() {
        if (instance == null) {
            instance = new SignUpRepository();
        }
        return instance;
    }

    public void signUp(String email, String phone, String password,
                       MutableLiveData<SignUpResponse> resultMutableLiveData, MutableLiveData<ResponseFail> failMutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).signUp( email, phone, password)
        .enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, ">>> Response is successfil");
                    Log.d(TAG, ">>> Response is " + response.body().toString());
                    resultMutableLiveData.postValue(response.body());
                } else {
                    Log.w(TAG, ">> Response is FALSE while login >> ");
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("Message");
                        Log.w(TAG, ">> + " +  message);
                        failMutableLiveData.postValue(new ResponseFail(message));
                    } catch (IOException e) {
                        Log.e(TAG, ">> " + e.getMessage());
                    } catch (JSONException e) {
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d(TAG, ">>> Response FAIL " );
                Log.i(TAG, ">> Exp " + t.getMessage());
                failMutableLiveData.postValue(new ResponseFail("Response Fail"));
            }
        });
    }
}
