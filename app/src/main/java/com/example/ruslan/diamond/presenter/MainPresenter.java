package com.example.ruslan.diamond.presenter;

import android.app.AlertDialog;
import android.content.Intent;

import com.example.ruslan.diamond.App;
import com.example.ruslan.diamond.db.Price;
import com.example.ruslan.diamond.ui.MainActivityInterface;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainPresenter implements MainPresenterInterface {

    private MainActivityInterface view;

    public MainPresenter(MainActivityInterface view) {
        this.view = view;

        startPayPalService();
    }

    private void startPayPalService(){
        Intent intent = new Intent(App.getContext(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,App.getPayPalConfiguration());
        view.startPayPalService(intent);
    }

    @Override
    public void detachView() {
        if (view!=null){
            view.stopPayPalService(new Intent(App.getContext(),PayPalService.class));
            view = null;
        }
    }

    @Override
    public void needFilter() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Price> prices = realm.where(Price.class).findAll();

        List<String> carat = new ArrayList<>();
        Set<String> uniqueCarat = new HashSet<>();
        List<String> clarity = new ArrayList<>();
        Set<String> uniqueClarity = new HashSet<>();
        List<String> amount = new ArrayList<>();
        Set<String> uniqueAmount = new HashSet<>();
        List<String> weight = new ArrayList<>();
        Set<String> uniqueWeight = new HashSet<>();

        for (Price p:prices) {
            uniqueCarat.add(p.getCarat());
            uniqueClarity.add(p.getClarity());
            uniqueAmount.add(p.getAmount().toString());
            uniqueWeight.add(p.getWeight().toString());
        }

        realm.close();

        carat.addAll(uniqueCarat);
        clarity.addAll(uniqueClarity);
        amount.addAll(uniqueAmount);
        weight.addAll(uniqueWeight);

        view.setFilterCarat(carat);
        view.setFilterClarity(clarity);
        view.setFilterAmount(amount);
        view.setFilterWeight(weight);
    }

    @Override
    public void pressPay(String sum) {
        if (sum!=null && !sum.equals("0")){

            PayPalPayment payment = new PayPalPayment(new BigDecimal(sum), "USD", "sample item",
                    PayPalPayment.PAYMENT_INTENT_SALE);

            Intent intent = new Intent(App.getContext(), PaymentActivity.class);
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, App.getPayPalConfiguration());
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

            view.startPayPalActivity(intent);
        }
    }

    @Override
    public void resultFromPayPal(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                // TODO: send 'confirm' to your server for verification.
                showMessage("Success", "Оплата прошла успешно");
            }
        } else if (resultCode == 0) {
            showMessage("Cancel", "Отмена пользователем");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            showMessage("Error", "Ошибка SDK PayPal");
        }
    }

    @Override
    public void queryPrice(String carat, String clarity, Integer amount, Integer weight) {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Price> prices = realm.where(Price.class)
                .equalTo("carat",carat)
                .and()
                .equalTo("clarity",clarity)
                .and()
                .equalTo("amount",amount)
                .and()
                .equalTo("weight",weight)
                .findAll();

        if (prices.size()==0){
            view.setSum(0.00);
        }else view.setSum(prices.get(0).getPrice());

        realm.close();
    }

    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(App.getContext());
        builder.setTitle(title)
                .setMessage(message)
                .setNeutralButton("OK", (dialogInterface, i) -> dialogInterface.cancel());
        view.showMessageToUser(builder.create());
    }
}
