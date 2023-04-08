package com.lessons.app_2;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.app_2.adapter.ItemListAdapter;
import com.lessons.app_2.clicker.CountryClickListener;
import com.lessons.app_2.database.AppDatabase;
import com.lessons.app_2.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    private ItemListAdapter itemListAdapter;

    List<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        RecyclerView recyclerView = findViewById(R.id.itemList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(
                        getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false));

        countries = AppDatabase.getInstance(getApplicationContext()).countryDao().getAll();

        itemListAdapter = new ItemListAdapter(this, countries,
                new CountryClickListener(getApplicationContext(), this));
        recyclerView.setAdapter(itemListAdapter);

        SearchView searchView = findViewById(R.id.countryFilter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    // Принимаем введённый текст
    private void filter(String newText) {
        List<Country> countryList = new ArrayList<>();
        for(Country country : countries) {
            // Ищем по вхождению
            if(country.title.toLowerCase().contains(newText.toLowerCase()) ||
                    country.location.toLowerCase().contains(newText.toLowerCase())) {
                countryList.add(country);
            }
        }
        // передаём новый список
        itemListAdapter.filterList(countryList);
    }
}