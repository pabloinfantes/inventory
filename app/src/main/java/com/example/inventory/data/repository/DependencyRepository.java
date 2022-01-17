package com.example.inventory.data.repository;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.example.inventory.ui.dependency.DependencyListContract;
import com.example.inventory.ui.dependency.DependencyManageContract;

import java.util.ArrayList;
import java.util.Collections;

public class DependencyRepository implements DependencyListContract.Repository , DependencyManageContract.Repository {

    private static DependencyRepository instance;
    private ArrayList<Dependency> list;


    private DependencyRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new Dependency("Aula de 1 CFGS","1CFGS","2",null));
        list.add(new Dependency("Aula de 1 CFGM","1CFGM","5",null));
        list.add(new Dependency("Aula de 2 CFGS","2CFGS","1",null));
        list.add(new Dependency("Aula de 2 CFGM","2CFGM","3",null));
        list.add(new Dependency("BigData","BIG","4",null));
    }

    public static DependencyRepository getInstance(){
        if (instance == null){
            instance = new DependencyRepository();
        }
        return instance;
    }


    @Override
    public void getList(OnRepositoryListCallback callback) {
        Collections.sort(list);
        callback.onSuccess(list);

    }

    @Override
    public void delete(Dependency dependency ,OnRepositoryListCallback callback) {
        list.remove(dependency);
        callback.onDeleteSucces("Se ha eliminado la dependencia" +dependency.getName());
    }

    @Override
    public void undo(Dependency dependency , OnRepositoryListCallback callback) {
        list.add(dependency);
        callback.onUndoSuccess("Operacion");
    }




    @Override
    public void add(Dependency dependency ,OnRepositoryCallback callback) {
        for(Dependency dependency1 : list){
            if (dependency.getShortname().equals(dependency1.getShortname())){
                callback.onFailure("Error en la funcion de añadir");
                return;

            }

        }
        list.add(dependency);
        callback.onSuccess("Se ha añadido correctamente");

    }

    @Override
    public void edit(Dependency dependency, OnRepositoryCallback callback) {
        for(Dependency dependency1 : list){
            if (dependency.getShortname().toString().equals(dependency1.getShortname().toString())){
                dependency1.setName(dependency.getName().toString());
                dependency1.setDescription(dependency.getDescription().toString());
                dependency1.setImage(dependency.getImage().toString());
                callback.onSuccess("Se ha editado correctamente");
                return;
            }
        }
        callback.onFailure("Error en la funcion de editar");
    }
}
