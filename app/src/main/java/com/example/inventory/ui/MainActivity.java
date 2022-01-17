package com.example.inventory.ui;

import android.os.Bundle;

import com.example.inventory.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;


import com.example.inventory.R;

public class MainActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private ActivityMainBinding binding;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.include.toolbar);
        setContentView(binding.getRoot());


        //Personalizar navigation drawer
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Inicializar el controlador de navegacion en la aplicacion
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        //Metodo que configura el componente NavigationView
        setUpNavigationVIew();
    }

    private void setUpNavigationVIew() {

        binding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_inventory:
                        showAddInventory();
                        break;
                    case R.id.action_aboutus:
                        showAboutUs();
                        // OOOOOJOOOO QUE ESTO DA PROBLEMAS PREGUNTAR EN CLASE
                        binding.navigationview.getCheckedItem().setChecked(false);
                        break;
                    case R.id.action_settings:
                        showSettings();
                        // OOOOOJOOOO QUE ESTO DA PROBLEMAS PREGUNTAR EN CLASE
                        binding.navigationview.getCheckedItem().setChecked(false);
                        break;
                    case R.id.action_sections:
                        break;
                    case R.id.action_dependency:
                        showDependencyList();
                        break;

                }
                binding.drawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.content_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_search:
                Toast.makeText(this,"Se ha pulsado buscar",Toast.LENGTH_SHORT).show();
                return true;

            case android.R.id.home:
                binding.drawerlayout.openDrawer(GravityCompat.START);
                return true;


            default:
                //Si los fragment modifican el menu de la activity se devuelve false
                return false;
        }

    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        if (pref.getKey().equals(getString(R.string.key_account))){
            navController.navigate(R.id.action_settingsFragment_to_accountFragment);
        }
        return true;
    }


    private void showSettings() {
        navController.navigate(R.id.settingsFragment);
    }

    private void showDependencyList() {
        navController.navigate(R.id.dependencyListFragment);
    }

    /**
     * Mostrar el fragment que da de alta un inventory
     */
    private void showAddInventory() {
        navController.navigate(R.id.addInventoryFragment);
    }

    /**
     * Mostrar el fragment About Us
     */
    private void showAboutUs() {
        navController.navigate(R.id.aboutUsFragment);
    }

}