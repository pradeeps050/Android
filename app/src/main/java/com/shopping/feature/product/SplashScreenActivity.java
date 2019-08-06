package com.shopping.feature.product;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.shopping.R;
import com.shopping.feature.home.HomeActivity;
import com.shopping.feature.login.ui.login.LoginActivity;
import com.shopping.framework.Room.Database.ShoppingRoomDatabase;
import com.shopping.framework.application.AppInstance;
import com.shopping.framework.model.UserEntity;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = SplashScreenActivity.class.getSimpleName();

    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
                }
        }, SPLASH_SCREEN_TIME_OUT);

        /*AppInstance.getInstance().getContext().getExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, ">> Querying Db for user ");
                ShoppingRoomDatabase db = ShoppingRoomDatabase.getInstance(SplashScreenActivity.this);
                if (db != null) {
                    UserEntity entity = db.userDao().getUserDetail();
                    if (entity!= null) {
                        if ((entity.getIsVerified() == 1) ? true : false) {
                            startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                        } else {
                            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                        }
                        Log.d("Users ", ">> " + entity.toString());
                    }
                }

            }
        });*/

    }
}
