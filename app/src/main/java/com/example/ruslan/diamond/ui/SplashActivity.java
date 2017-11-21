package com.example.ruslan.diamond.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruslan.diamond.R;
import com.example.ruslan.diamond.presenter.SplashPresenter;
import com.example.ruslan.diamond.presenter.SplashPresenterInterface;

public class SplashActivity extends AppCompatActivity implements SplashViewInterface {

    SplashPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this);
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
