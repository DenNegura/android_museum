package com.lessons.app_2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.android.material.navigation.NavigationView;
import com.lessons.app_2.adapter.ItemListAdapter;
import com.lessons.app_2.clicker.CountryClickListener;
import com.lessons.app_2.clicker.ItemClickListener;
import com.lessons.app_2.dao.CountryDao;
import com.lessons.app_2.dao.MuseumDao;
import com.lessons.app_2.database.AppDatabase;
import com.lessons.app_2.models.Country;
import com.lessons.app_2.models.Item;
import com.lessons.app_2.models.Museum;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // получаем выдвижное меню
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // указываем кнопку, которая будет воткрывать меню
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getApplicationContext().deleteDatabase("database.sql.db");
        setQuote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Слушатель события на нажатия кнопки в меню
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // получаем id кнопки
        int id = item.getItemId();
        // в зависмости от нажатой кнопки исполняем код
        if (id == R.id.nav_country) {
            // Создаём привязку с другим представлением
            Intent intent = new Intent(MainActivity.this, CountryActivity.class);
            // Запускаем представление
            startActivityForResult(intent, 101);
        }
        // закрываем меню
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("SetTextI18n")
    public void setQuote() {
        String[] quotes = getResources().getStringArray(R.array.quote);
        String[] authors = getResources().getStringArray(R.array.author);
        int randQuote = (int) (Math.random() * quotes.length);

        TextView quote = findViewById(R.id.textQuote);
        TextView author = findViewById(R.id.textAuthor);

        quote.setText("\"" + quotes[randQuote] + "\"");
        author.setText(authors[randQuote]);
    }
}