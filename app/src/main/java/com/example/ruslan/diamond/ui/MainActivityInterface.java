package com.example.ruslan.diamond.ui;

import android.app.AlertDialog;
import android.content.Intent;

import java.util.List;

public interface MainActivityInterface {
    public void setFilterCarat(List<String> carat);
    public void setFilterClarity(List<String> clarity);
    public void setFilterAmount(List<String> amount);
    public void setFilterWeight(List<String> weight);
    public void setSum(Double sum);
    public void startPayPalActivity(Intent intent);
    public void startPayPalService(Intent intent);
    public void stopPayPalService(Intent intent);
    public void showMessageToUser(AlertDialog alertDialog);
}
