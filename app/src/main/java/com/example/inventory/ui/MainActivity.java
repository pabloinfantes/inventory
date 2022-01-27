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

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.include.toolbar);
        setContentView(binding.getRoot());


        //Personalizar navigation drawer
        //En la opcion 2 hay que comentar esos dos lineas
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Inicializar el controlador de navegacion en la aplicacion
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        //Opcion 1 Mostrar siempre el icono hamburguesa
        //Metodo que configura el componente NavigationView
        //setUpNavigationVIew();

        //Opcion 2 Mostrar los niveles de fragments mediante la flecha
        Set<Integer> topLevelDestination = new HashSet<>();
        topLevelDestination.add(R.id.dependencyListFragment);
        topLevelDestination.add(R.id.addInventoryFragment);
        topLevelDestination.add(R.id.aboutUsFragment);
        topLevelDestination.add(R.id.settingsFragment);
        topLevelDestination.add(R.id.productFragment);
        topLevelDestination.add(R.id.sectionsFragment);

        //Este metodo gestiona el evento click del navigation y se mostrara el id del fragment de navController solo si el id del menu es igual al id del fragment
        NavigationUI.setupWithNavController(binding.navigationview,navController);

        //Configurar la barra de Action para que funcione con NavigationUI
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestination)
                .setOpenableLayout(binding.drawerlayout)
                .build();


        //Con este metodo gestionamos la Barra de accion cuando hay varios niveles de navegacion
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
    }

//    private void setUpNavigationVIew() {
//
//        binding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_inventory:
//                        showAddInventory();
//                        break;
//                    case R.id.action_aboutus:
//                        showAboutUs();
//                        // OOOOOJOOOO QUE ESTO DA PROBLEMAS PREGUNTAR EN CLASE
//                        binding.navigationview.getCheckedItem().setChecked(false);
//                        break;
//                    case R.id.action_settings:
//                        showSettings();
//                        // OOOOOJOOOO QUE ESTO DA PROBLEMAS PREGUNTAR EN CLASE
//                        binding.navigationview.getCheckedItem().setChecked(false);
//                        break;
//                    case R.id.action_sections:
//                        break;
//                    case R.id.action_dependency:
//                        showDependencyList();
//                        break;
//
//                }
//                binding.drawerlayout.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.content_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    /**
     * Cuando se pulsa sobre el icono de la fecha debe ser el componente NavigationUi quien gestiones la navegacion hacia arriba
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_search:
                Toast.makeText(this,"Se ha pulsado buscar",Toast.LENGTH_SHORT).show();
                return true;

                /* Esto es de la opcion 1
            case android.R.id.home:
                binding.drawerlayout.openDrawer(GravityCompat.START);
                return true;
                */

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

    @Override
    public void onBackPressed() {
        if (binding.drawerlayout.isDrawerOpen(GravityCompat.START))
            binding.drawerlayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }



    //Estos metodos son para la opcion 1

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