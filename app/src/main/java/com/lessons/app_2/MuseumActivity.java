package com.lessons.app_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.lessons.app_2.models.Museum;

import java.io.IOException;
import java.io.InputStream;

public class MuseumActivity extends AppCompatActivity {

    private Museum museum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
        try {
            museum = (Museum) getIntent().getSerializableExtra("museum");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageView image = findViewById(R.id.imageMuseum);
        TextView title = findViewById(R.id.textTitleMuseum);
        TextView description = findViewById(R.id.textDescriptionMuseum);
        TextView location = findViewById(R.id.textLocationMuseum);

        try (InputStream imageStream = this.getAssets().open(museum.getImage())) {
            Drawable drawable = Drawable.createFromStream(imageStream, null);
            image.setImageDrawable(drawable);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
        } catch (IOException e) {
            image.setImageResource(R.drawable.missing);
        }

        title.setText(museum.getTitle());
        description.setText(museum.getDescription());
        location.setText(museum.location);
    }
}