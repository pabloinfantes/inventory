package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.OnRepositoryCallback;

public class DependencyManagePresenter implements DependencyManageContract.Presenter , DependencyManageContract.OnInteractorManageListener{

     private DependencyManageContract.View view;
     private DependencyManageInteractor interactor;


    public DependencyManagePresenter(DependencyManageContract.View view) {
        this.view = view;
        this.interactor = new DependencyManageInteractor(this);
    }


    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void add(Dependency dependency) {
        interactor.add(dependency);
    }

    @Override
    public void edit(Dependency dependency) {
        interactor.edit(dependency);
    }

    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }




    @Override
    public void onShortNameEmpty() {
        view.setShortNameEmpty();
    }

    @Override
    public void onNameEmpty() {
        view.setNameEmpty();
    }

    @Override
    public void onDescriptionEmpty() {
        view.setDescriptionEmpty();
    }

    @Override
    public void onImageEmpty() {
        view.setImageEmpty();
    }
}
