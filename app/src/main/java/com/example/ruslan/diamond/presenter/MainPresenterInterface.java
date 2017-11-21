package com.example.ruslan.diamond.presenter;

import android.content.Intent;

public interface MainPresenterInterface {
    public void detachView();
    public void needFilter();
    public void pressPay(String sum);
    public void resultFromPayPal(int requestCode, int resultCode, Intent data);
    public void queryPrice(String carat, String clarity, Integer amount, Integer weight);
}
