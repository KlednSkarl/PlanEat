package com.example.ui_presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Mainmenu extends AppCompatActivity {
    BottomNavigationView botNav;
    Home home_frag = new Home();
    Plans plans_frag = new Plans();
    Settings set_frag = new Settings();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
//        getSupportActionBar().hide();


        botNav = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,home_frag).commit();

        botNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,home_frag).commit();
                        return true;
                    case R.id.plans:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,plans_frag).commit();
                            return true;
                    case R.id.setting:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,set_frag).commit();
                            return true;
                }
                return false;
            }
        });
    }
}