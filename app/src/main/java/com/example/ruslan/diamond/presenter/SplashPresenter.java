package com.example.ruslan.diamond.presenter;

import com.example.ruslan.diamond.App;
import com.example.ruslan.diamond.R;
import com.example.ruslan.diamond.backend.BackendApi;
import com.example.ruslan.diamond.db.Price;
import com.example.ruslan.diamond.ui.SplashViewInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class SplashPresenter implements SplashPresenterInterface {

    private SplashViewInterface view;

    public SplashPresenter(SplashViewInterface view) {
        this.view = view;

        loadDataFromBackend();
    }

    private void loadDataFromBackend(){
        BackendApi api = App.getBackendApi();
        api.getPrice(App.getContext().getResources().getString(R.string.backendles_token_app),
                App.getContext().getResources().getString(R.string.backendles_token_rest))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::writePriceToDB, __ -> view.startMainActivity());
    }

    private void writePriceToDB(List<Price> prices){
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realm1 -> realm.deleteAll());

        realm.executeTransaction(realm1 -> realm.copyToRealm(prices));

        realm.close();
        view.startMainActivity();
    }

    @Override
    public void detachView() {
        if (view!=null) view=null;
    }
}
