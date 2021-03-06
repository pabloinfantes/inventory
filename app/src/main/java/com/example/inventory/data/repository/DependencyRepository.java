package com.example.inventory.data.repository;

import com.example.inventory.data.InventoryDatabase;
import com.example.inventory.data.dao.DependencyDAO;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.example.inventory.ui.dependency.DependencyListContract;
import com.example.inventory.ui.dependency.DependencyManageContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class DependencyRepository implements DependencyListContract.Repository , DependencyManageContract.Repository {

    private static DependencyRepository instance;
    private ArrayList<Dependency> list;
    private DependencyDAO dependencyDAO;


    private DependencyRepository(){
        list = new ArrayList<>();
        //initialice();
        dependencyDAO = InventoryDatabase.getDatabase().dependencyDAO();
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
        try {
            list = (ArrayList<Dependency>) InventoryDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.select()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);

    }

    @Override
    public void delete(Dependency dependency ,OnRepositoryListCallback callback) {
        InventoryDatabase.databaseWriteExecutor.submit(()-> dependencyDAO.delete(dependency));
        callback.onDeleteSucces("Se ha eliminado la dependencia" +dependency.getName());
    }

    @Override
    public void undo(Dependency dependency , OnRepositoryListCallback callback) {
        InventoryDatabase.databaseWriteExecutor.submit(()-> dependencyDAO.insert(dependency));
        callback.onUndoSuccess("Operacion");
    }




    @Override
    public void add(Dependency dependency ,OnRepositoryCallback callback) {
        InventoryDatabase.databaseWriteExecutor.submit(()-> dependencyDAO.insert(dependency));
        callback.onSuccess("Se ha a??adido correctamente");

    }

    @Override
    public void edit(Dependency dependency, OnRepositoryCallback callback) {
        InventoryDatabase.databaseWriteExecutor.submit(()-> dependencyDAO.update(dependency));
        callback.onSuccess("Se ha editado correctamente");
    }
}
