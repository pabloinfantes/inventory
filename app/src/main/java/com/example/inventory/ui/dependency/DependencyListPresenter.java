package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.signup.SignUpContract;
import com.example.inventory.ui.signup.SignUpInteractor;

import java.util.ArrayList;
import java.util.List;

public class DependencyListPresenter implements DependencyListContract.Presenter, DependencyListContract.OnInteractorListener {

    private DependencyListContract.View view;
    private DependencyListInteractor interactor;
    private Boolean order=false;

    public DependencyListPresenter(DependencyListContract.View view) {
        this.view = view;
        this.interactor = new DependencyListInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor =null;
    }




    @Override
    public void load() {
        view.showProgress();
        interactor.load();

    }

    /**
     * Este metodo elimina una dependencia de la bd/servidor
     * @param dependency
     */
    @Override
    public void delete(Dependency dependency) {
        interactor.delete(dependency);
    }

    @Override
    public void undo(Dependency dependency) {
        interactor.undo(dependency);
    }

    @Override
    public void order() {
        if (order==true){
            order=false;
            view.showDataInverseOrder();
        }else {
            order=true;
            view.showDataOrder();
        }

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0){
            view.showNoData();
        }else
            view.showData((ArrayList<Dependency>) list);


        view.hideProgress();
    }



    @Override
    public void onDeleteSucces(String message) {
        view.onDeleteSucces(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        view.onUndoSuccess(message);
    }
}
