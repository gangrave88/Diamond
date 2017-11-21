package com.example.ruslan.diamond;

import android.app.Application;
import android.content.Context;

import com.example.ruslan.diamond.backend.BackendApi;
import com.example.ruslan.diamond.db.Migration;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static BackendApi backendApi;
    private static Context context;
    private static PayPalConfiguration payPalConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
//        context
        context = this;
//        retrofit
        backendApi = new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build()
                .create(BackendApi.class);
//        realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .migration(new Migration())
                .schemaVersion(1)
                .name("price.db")
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);
//        paypal
        payPalConfiguration = new PayPalConfiguration()
                .environment(BuildConfig.PAYPAL_ENVIRONMENT)
                .clientId(getResources().getString(R.string.paypal_token));
    }

    public static BackendApi getBackendApi(){
        return backendApi;
    }

    public static Context getContext(){
        return context;
    }

    public static PayPalConfiguration getPayPalConfiguration(){
        return payPalConfiguration;
    }
}
