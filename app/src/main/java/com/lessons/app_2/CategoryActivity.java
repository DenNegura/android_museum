package com.lessons.app_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lessons.app_2.adapter.ItemExpandableListAdapter;
import com.lessons.app_2.database.AppDatabase;
import com.lessons.app_2.models.CategoryMuseums;
import com.lessons.app_2.models.Country;
import com.lessons.app_2.models.Museum;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private Country country;

    private ItemExpandableListAdapter listAdapter;

    private boolean isFullDescription;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        try {
            country = (Country) getIntent().getSerializableExtra("country");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageView image = findViewById(R.id.imageCategoryView);
        TextView title = findViewById(R.id.titleCategoryView);
        TextView description = findViewById(R.id.descriptionCategoryView);

        try (InputStream imageStream = this.getAssets().open(country.getImage())) {
            Drawable drawable = Drawable.createFromStream(imageStream, null);
            image.setImageDrawable(drawable);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
        } catch (IOException e) {
            image.setImageResource(R.drawable.missing);
        }
        title.setText(country.getTitle());
        description.setText(country.getDescription().substring(0, 125) + "...");
        isFullDescription = false;

        ExpandableListView listView = findViewById(R.id.categoryList);
        List<CategoryMuseums> categories = AppDatabase.getInstance(this)
                .categoryWithMuseumsDao()
                .getCategoriesWithMuseumsByCountryId(country.id);
        listAdapter = new ItemExpandableListAdapter(this, categories);
        listView.setAdapter(listAdapter);
        listView.setOnChildClickListener(ChildListener);
    }

    // Обработчик нажатия на кнопку
    @SuppressLint("SetTextI18n")
    public void openCloseCountryFullDescription(View view) {
        TextView description = findViewById(R.id.descriptionCategoryView);
        if(isFullDescription) {
            description.setText(country.getDescription().substring(0, 125) + "...");
            isFullDescription = false;
        } else {
            description.setText(country.getDescription());
            isFullDescription = true;
        }
    }

    private final ExpandableListView.OnChildClickListener
            ChildListener = (parent, v, groupPosition, childPosition, id) -> {
                Intent intent = new Intent(CategoryActivity.this, MuseumActivity.class);
                intent.putExtra("museum",
                        (Museum) listAdapter.getChild(groupPosition, childPosition));
                startActivityForResult(intent, 103);
                return false;
            };
}