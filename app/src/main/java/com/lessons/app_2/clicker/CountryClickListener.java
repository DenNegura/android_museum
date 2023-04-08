package com.lessons.app_2.clicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lessons.app_2.CategoryActivity;
import com.lessons.app_2.CountryActivity;
import com.lessons.app_2.MainActivity;
import com.lessons.app_2.models.Country;
import com.lessons.app_2.models.Item;

public class CountryClickListener implements ItemClickListener {

    private Context context;

    private Activity activity;

    public CountryClickListener(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    // Получаем объект элемента, на который нажали
    @Override
    public void onClick(Item item) {
        // создаём мост между представлениями
        Intent intent = new Intent(context, CategoryActivity.class);
        // передаём объект, для этого он должен быть Serializable
        intent.putExtra("country", item);
        activity.startActivityForResult(intent, 102);
    }
}
