package com.example.ruslan.diamond.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.ruslan.diamond.R;
import com.example.ruslan.diamond.presenter.MainPresenter;
import com.example.ruslan.diamond.presenter.MainPresenterInterface;
import com.jakewharton.rxbinding2.view.RxView;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    MainPresenterInterface presenter;

    ScrollChoice scrollChoiceCarat;
    ScrollChoice scrollChoiceClarity;
    ScrollChoice scrollChoiceAmount;
    ScrollChoice scrollChoiceWeight;

    TextView sum;

    Button payButton;

    private String carat;
    private String clarity;
    private String amount;
    private String weight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initScrollChoice();
        initViews();
        initPresenter();
    }

    private void initViews() {
        sum = findViewById(R.id.sum);

        payButton = findViewById(R.id.button_pay);

        RxView.clicks(payButton).subscribe(__ -> clickPayButton());
    }

    private void initPresenter() {
        presenter = new MainPresenter(this);

        presenter.needFilter();
    }

    private void initScrollChoice() {
        scrollChoiceCarat = findViewById(R.id.scroll_choice_carat);
        scrollChoiceClarity = findViewById(R.id.scroll_choice_clarity);
        scrollChoiceAmount = findViewById(R.id.scroll_choice_amount);
        scrollChoiceWeight = findViewById(R.id.scroll_choice_weight);

        scrollChoiceCarat.setOnItemSelectedListener((scrollChoice, position, name) -> {
            carat = name;
            applyFilter();
        });
        scrollChoiceClarity.setOnItemSelectedListener((scrollChoice, position, name) -> {
            clarity = name;
            applyFilter();
        });
        scrollChoiceAmount.setOnItemSelectedListener((scrollChoice, position, name) -> {
            amount = name;
            applyFilter();
        });
        scrollChoiceWeight.setOnItemSelectedListener((scrollChoice, position, name) -> {
            weight = name;
            applyFilter();
        });
    }

    private void applyFilter(){
        presenter.queryPrice(carat,clarity,Integer.valueOf(amount),Integer.valueOf(weight));
    }

    public void clickPayButton(){
        presenter.pressPay(sum.getText().toString(),carat,clarity,Integer.valueOf(amount),Integer.valueOf(weight), "");
    }

    @Override
    public void setFilterCarat(List<String> carat) {
        scrollChoiceCarat.addItems(carat,0);
        this.carat = carat.get(0);
    }

    @Override
    public void setFilterClarity(List<String> clarity) {
        scrollChoiceClarity.addItems(clarity,0);
        this.clarity = clarity.get(0);
    }

    @Override
    public void setFilterAmount(List<String> amount) {
        scrollChoiceAmount.addItems(amount,0);
        this.amount = amount.get(0);
    }

    @Override
    public void setFilterWeight(List<String> weight) {
        scrollChoiceWeight.addItems(weight,0);
        this.weight = weight.get(0);
    }

    @Override
    public void setSum(Double sumd) {
        sum.setText(sumd.toString());
    }

    @Override
    public void startPayPalActivity(Intent intent) {
        startActivityForResult(intent,0);
    }

    @Override
    public void startPayPalService(Intent intent) {
        startService(intent);
    }

    @Override
    public void stopPayPalService(Intent intent) {
        stopService(intent);
    }

    @Override
    public void showMessageToUser(AlertDialog alertDialog) {
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        presenter.resultFromPayPal(requestCode,resultCode,data);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
