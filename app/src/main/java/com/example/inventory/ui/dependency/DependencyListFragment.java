package com.example.inventory.ui.dependency;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyListBinding;
import com.example.inventory.ui.base.BaseDialogFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class DependencyListFragment extends Fragment implements DependencyListContract.View, DependencyAdapter.OnManageDependencyListener {

    private FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;
    private DependencyListContract.Presenter presenter;

    // Una vez que el repositorio elimina la dependencia, el adapter debe eliminar la dependencia
    private Dependency deleted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.- Se debe indicar a la activity que se quiere modificar el menu
        setHasOptionsMenu(true);
        //2.- Se inicializa el presenter
        presenter = new DependencyListPresenter(this);


    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDependencyListBinding.inflate(inflater, container, false);
        Log.d(TAG, "DependencyListFragment -> onCreateView()");
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvDependency();
        initFab();

    }

    //2.- Sobreescribir el metodo onCreateOptionsMenu para añadir el menu del fragment
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragmentlist_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //3.- Implementar las acciones especificas (item) del menu del fragment
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order_dependencis:
                //Toast.makeText(getActivity(),"Se ha pulsado ordenar dependencias",Toast.LENGTH_SHORT).show();
                presenter.order();
                return true;
            case R.id.action_order_bydescription:
                adapter.orderByDescription();
                return true;
            default:
                //Si los fragment modifican el menu de la activity se devuelve false
                return super.onOptionsItemSelected(item);
        }
    }



    private void initFab() {
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DependencyListFragmentDirections.ActionDependencyListFragmentToFragmentDependencyManage action = DependencyListFragmentDirections.actionDependencyListFragmentToFragmentDependencyManage(null);
                NavHostFragment.findNavController(DependencyListFragment.this).navigate(action);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //Pido y solicito los datos
        presenter.load();
    }

    /**
     * Este metodo inicializa el componente recycler view
     */
    private void initRvDependency() {
        //1.- Sera inicializar dicho adapter
        adapter = new DependencyAdapter(new ArrayList<Dependency>(), this);
        //2.- OBLIGATORIOMENTE se debe indicae que diseño (layout) tendra el recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //3.- Asgino el layout al recyclerView
        binding.rvDependency.setLayoutManager(linearLayoutManager);
        //4.- Asigno a la vista sus datos (modelo)
        binding.rvDependency.setAdapter(adapter);
    }


    //region Metodos que vienen de la interfaz del adapter

    @Override
    public void onEditDependency(Dependency dependency) {
        DependencyListFragmentDirections.ActionDependencyListFragmentToFragmentDependencyManage action = DependencyListFragmentDirections.actionDependencyListFragmentToFragmentDependencyManage(dependency);
        NavHostFragment.findNavController(DependencyListFragment.this).navigate(action);
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {
        //Toast.makeText(getActivity(), "Se quiere eliminar la dependencia: " + dependency.getName(),Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.title_delete_dependency));
        bundle.putString(BaseDialogFragment.MESSAGE, String.format(getString(R.string.message_delete_dependency), dependency.getShortname()));

        //Registrar el listener del BaseDialog. Este codigo sirve para comunicar dos frament en el cual el padre necesita un resultado del hijo
        //MUY IMPORTANTE SI SE USA LA LIBRERIA DE SOPORTE SE DEBE LLAMAR A  GETSUPPORTFRAGMENTEMANFAGER
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Si la respuesta del usuario es true, se llama al presentador
                if (result.getBoolean(BaseDialogFragment.KEY_BUNDLE)) {
                    deleted = dependency;
                    presenter.delete(dependency);

                }
            }
        });
        //
        NavHostFragment.findNavController(this).navigate(R.id.action_dependencyListFragment_to_baseDialogFragment, bundle);

    }

    //endregion

    //region Metodos que vienen de la respuesta del repositorio

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
    }

    /**
     * Metodo que muestra un snackbar con la opcion UNDO
     *
     * @param message
     */
    @Override
    public void onDeleteSucces(String message) {
        Log.d(TAG,"onDeleteSucces");
        Snackbar.make(getView(), message, BaseTransientBottomBar.LENGTH_SHORT).setAction(getString(R.string.undo), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.undo(deleted);
            }
        }).show();
        adapter.delete(deleted);
        if (adapter.getItemCount() ==0 )
            showNoData();
    }

    @Override
    public void onUndoSuccess(String message) {
        if (binding.dependencyListFragmentImgNoData.getVisibility() == View.VISIBLE)
            binding.dependencyListFragmentImgNoData.setVisibility(View.GONE);

        adapter.undo(deleted);
    }

    //endregion

    //region Metodos que vienen del requisito que  se debe mostrar una barra de progreso
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    //endregion

    //region Metodos del contrato VISTA-PRESENTER

    @Override
    public void showNoData() {
        binding.dependencyListFragmentImgNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(ArrayList<Dependency> list) {
        if (binding.dependencyListFragmentImgNoData.getVisibility() == View.VISIBLE)
            binding.dependencyListFragmentImgNoData.setVisibility(View.GONE);


        adapter.update(list);
    }

    @Override
    public void showDataOrder() {
        adapter.order();
    }

    @Override
    public void showDataInverseOrder() {
        adapter.inverseOrder();
    }

    //endregion
}