package com.example.app;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.Close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        bottomNavigationView.setBackground(null);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();

        }
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Gestion des clics sur les éléments de menu
                int itemId = item.getItemId();
                if (itemId == R.id.nav_arduino) {
                    showToast("You clicked on Arduino");
                    device();
                    // Ajoutez votre logique ici pour l'élément Arduino
                } else if (itemId == R.id.nav_help) {
                    showToast("You clicked on Help");
                    // Ajoutez votre logique ici pour l'élément Help
                } else if (itemId == R.id.nav_settinge) {
                    showToast("You clicked on Settings");
                    // Ajoutez votre logique ici pour l'élément Settings
                } else if (itemId == R.id.nav_about) {
                    showToast("You clicked on About Us");
                    about();
                    // Ajoutez votre logique ici pour l'élément About Us
                } else if (itemId == R.id.nav_logout) {
                    showToast("You clicked on Logout");
                    logaut();
                    // Ajoutez votre logique ici pour l'élément Logout
                } else if (itemId == R.id.app_bar_switch) {
                    showToast("You clicked on Bluetooth switch");
                    // Obtenez une référence à votre switch2
                    Switch switch2 = findViewById(R.id.bt_switch);
                    // Basculez l'état du switch2
                    switch2.setChecked(!switch2.isChecked());
                }
                drawerLayout.closeDrawers();
                // Retourne true pour indiquer que l'élément a été traité
                return true;
            }
        });
    }


    public void logaut() {
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intent);
    }
public void device(){
   deviceFragment fragment = new deviceFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frame_layout, fragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
    bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
}
    public void about(){
        aboutFragment fragment = new aboutFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
    }
    private void showToast(String message) {
        Toast.makeText(MainActivity3.this, message, Toast.LENGTH_SHORT).show();
    }

    public void ok1(View view) {
        Fragmentliberary fragment = new Fragmentliberary();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        bottomNavigationView.getMenu().findItem(R.id.profile).setChecked(true);
    }

    public void home1(View view) {
        Fragment_home fragment = new Fragment_home();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
    }

    public void carte1(View view) {
        ShareFragment fragment = new ShareFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        bottomNavigationView.getMenu().findItem(R.id.carte).setChecked(true);
    }

    public void gps1(View view) {
        SettingsFragment fragment = new SettingsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        bottomNavigationView.getMenu().findItem(R.id.GPS).setChecked(true);
    }


    public void ok(View view) {
        // Obtenir les références aux TextViews
        TextView aInput = findViewById(R.id.a_input);
        TextView bInpute = findViewById(R.id.b_input);
        TextView cInput = findViewById(R.id.c_input);

        // Obtenir les contenus des TextViews
        String aContent = aInput.getText().toString().trim();
        String bContent = bInpute.getText().toString().trim();
        String cContent = cInput.getText().toString().trim();

        // Vérifier si a_inpute est vide
        if (aContent.isEmpty()) {
            // Ne rien faire si a_inpute est vide
            return;
        }

        // Vérifier si tous les champs sont remplis
        if (!aContent.isEmpty() && !bContent.isEmpty() && !cContent.isEmpty()) {
            // Construire le message complet
            String message = "de :" + aContent +
                    "/ a :" + bContent +
                    "/ camion :" + cContent +
                    "/ date et temps  :" + getDateTime();

            // Afficher le Toast avec le message complet
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            // Vider les champs a_inpute, b_inpute et c_inpute après affichage du Toast
            aInput.setText("");
            bInpute.setText("");
            cInput.setText("");
        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd  - HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


}
