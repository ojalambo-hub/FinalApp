package com.example.finalapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.nav_drawer);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            // إغلاق الـ Drawer لأي عنصر
            if (id != R.id.settingsFragment) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }

            // التعامل مع كل عنصر
            if (id == R.id.dashboardFragment) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new DashboardFragment())
                        .commit();
            }
            else if (id == R.id.assignmentFragment) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AssignmentFragment())
                        .commit();
            }
            else if (id == R.id.profileFragment) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .commit();
            }
            else if (id == R.id.settingsFragment) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SettingsFragment())
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
            }

            return true;
        });

        // عرض الـ Dashboard أول مرة
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DashboardFragment())
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}