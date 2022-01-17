package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;

public class DependencyListInteractor implements OnRepositoryListCallback {

    private DependencyListContract.OnInteractorListener listener;
    private OnRepositoryListCallback callback = this;

    public DependencyListInteractor(DependencyListContract.OnInteractorListener listener){
        this.listener = listener;
    }


    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        listener.onSuccess((ArrayList<Dependency>)list);
    }

    @Override
    public void onDeleteSucces(String message) {
        listener.onDeleteSucces(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        listener.onUndoSuccess(message);
    }



    public void load(){
        DependencyRepository.getInstance().getList(callback);
    }
    /**
     * Elimina la dependencia del repositorio
     * @param dependency
     */
    public void delete(Dependency dependency) {
        DependencyRepository.getInstance().delete(dependency,callback);
    }



    public void undo(Dependency dependency) {
        DependencyRepository.getInstance().undo(dependency,callback);
    }


}
