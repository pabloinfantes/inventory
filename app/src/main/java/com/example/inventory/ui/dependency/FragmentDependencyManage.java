package com.example.inventory.ui.dependency;


import static android.content.ContentValues.TAG;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventory.InventoryAplication;
import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyManageBinding;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.splash.SplashActivity;

import java.util.Random;


public class FragmentDependencyManage extends Fragment implements DependencyManageContract.View{

    private FragmentDependencyManageBinding binding;
    private DependencyManageContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DependencyManagePresenter(this);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDependencyManageBinding.inflate(inflater, container, false);
        Log.d(TAG, "FragmentDependencyManage -> onCreateView()");
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (FragmentDependencyManageArgs.fromBundle(getArguments()).getDependency()!=null){
            //1.-Cambiar el titulo
            getActivity().setTitle(getString(R.string.titleEditDependency));
            //2.- Rellenar los campos 
            initView(FragmentDependencyManageArgs.fromBundle(getArguments()).getDependency());
            
            //3.- modificar el icono action bar
            initFabEdit();
        }else {
            initFabAdd();
        }
        
    }

    private void initFabAdd() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.add(getDependency());
                //NavHostFragment.findNavController(FragmentDependencyManage.this).navigateUp();
            }
        });
    }

    private void initFabEdit() {
        binding.fab.setImageResource(R.drawable.ic_edit);
        binding.tieShortName.setEnabled(false);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.edit(getDependency());
            }
        });
    }

    /**
     * Da una dependencia inicializa la dependencia
     * @param dependency
     */
    private void initView(Dependency dependency) {
        binding.tieShortName.setText(dependency.getShortname());
        binding.tieName.setText(dependency.getName());
        binding.tieDescription.setText(dependency.getDescription());
        binding.tieImage.setText(dependency.getImage());
    }

    private Dependency getDependency() {
        Dependency dependency = new Dependency();
        if (FragmentDependencyManageArgs.fromBundle(getArguments()).getDependency() !=null)
            dependency.setId(FragmentDependencyManageArgs.fromBundle(getArguments()).getDependency().getId());

        dependency.setShortname(binding.tieShortName.getText().toString());
        dependency.setName(binding.tieName.getText().toString());
        dependency.setDescription(binding.tieDescription.getText().toString());
        dependency.setImage(binding.tieImage.getText().toString());
        return  dependency;
    }




    @Override
    public void onSuccess(String message) {
        //1.-Crear la notificacion ,pero antes se tiene que crear un bundle que almacene la dependencia
        Bundle bundle = new Bundle();
        bundle.putSerializable(Dependency.TAG,getDependency());

        //FORMA ANTIGUA
        //2.-Crear un Intent en el caso de trabajar con actividades
        //Intent intent = new Intent(getActivity(), SplashActivity.class);
        //intent.putExtras(bundle);
        //3.- Crear un pending Intent que contiene el INTENT
        //PendingIntent pendingIntent =  PendingIntent.getActivity(getActivity(),new Random().nextInt(1000),intent,PendingIntent.FLAG_UPDATE_CURRENT);


        //FORMA NUEVA
        //ERROR DIFICIL DE DETECTAR Y ES, EL TAL DEL BUNDLE SE DEBE LLAMAR IGUAL QUE EL ARGUMENTO
        //QUE SE HA ESTABLECIDO EN SAFE ARGS -> que crea automaticamente segun el nombre del argumento.
        //Dependency.TAG = dependency
        //Y el metodo de SAFE ARGS es getDependency()
        //4.- Si se utiliza el componente Navigation se crea el grafo de navegacion
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.fragmentDependencyManage)
                .setArguments(bundle)
                .createPendingIntent();

        //5.- Crear la notificacion
        Notification.Builder builder = new Notification.Builder(getActivity(), InventoryAplication.IDCHANEL)
                .setSmallIcon(R.drawable.ic_notification_logo)
                .setAutoCancel(true)
                .setContentTitle(getResources().getString(R.string.notification_title_add_dependency))
                .setContentText(message)
                .setContentIntent(pendingIntent);
        //6.- Añadir notificacion al manager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(),builder.build());
        NavHostFragment.findNavController(this).navigateUp();

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }




    @Override
    public void setShortNameEmpty() {

    }

    @Override
    public void setNameEmpty() {

    }

    @Override
    public void setDescriptionEmpty() {

    }

    @Override
    public void setImageEmpty() {

    }

    /**
     * Da una vusta se recoge la dependencia que el usuario ha introducido
     */
}